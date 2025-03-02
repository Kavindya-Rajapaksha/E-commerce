package com.ecommerce.ecommerce_multivendor.service.impl;

import com.ecommerce.ecommerce_multivendor.config.JwtProvider;
import com.ecommerce.ecommerce_multivendor.domain.USER_ROLE;
import com.ecommerce.ecommerce_multivendor.entity.Cart;
import com.ecommerce.ecommerce_multivendor.entity.Seller;
import com.ecommerce.ecommerce_multivendor.entity.User;
import com.ecommerce.ecommerce_multivendor.entity.VerificationCode;
import com.ecommerce.ecommerce_multivendor.repository.CartRepository;
import com.ecommerce.ecommerce_multivendor.repository.SellerRepository;
import com.ecommerce.ecommerce_multivendor.repository.UserRepository;
import com.ecommerce.ecommerce_multivendor.repository.VerificationCodeRepository;
import com.ecommerce.ecommerce_multivendor.request.LoginRequest;
import com.ecommerce.ecommerce_multivendor.response.AuthResponse;
import com.ecommerce.ecommerce_multivendor.response.SignupRequest;
import com.ecommerce.ecommerce_multivendor.service.AuthService;
import com.ecommerce.ecommerce_multivendor.service.EmailService;
import com.ecommerce.ecommerce_multivendor.utills.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//Create user and generate jwt tokes
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final CustomUserServiceImpl customUserService;
    private final SellerRepository sellerRepository;

    @Override
    public void sentLoginOtp(String email, USER_ROLE role) throws Exception {
        String SIGNING_PREFIX = "signing_";

        if(email.startsWith(SIGNING_PREFIX)){
            email = email.substring(SIGNING_PREFIX.length());

            if(role.equals(USER_ROLE.ROLE_SELLER)) {
                Seller seller = sellerRepository.findByEmail(email);
                if (seller == null) {
                    throw new Exception("Seller not found.");
                }
            }
            else {
                User user = userRepository.findByEmail(email);
                if(user==null){
                    throw new Exception("User not exist with provided email");
                }
            }
        }
        VerificationCode isExist = verificationCodeRepository.findByEmail(email);
        if (isExist != null){
            verificationCodeRepository.delete(isExist);
//            verificationCodeRepository.flush();
        }
        String otp= OtpUtil.generateOtp();

        VerificationCode verificationCode=new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);
        verificationCodeRepository.save(verificationCode);

        String subject ="Zosh bazar login/signup otp";
        String text = "Your login otp is -  "+otp;

        emailService.sendVerificationOtpEmail(email,otp,subject,text);
    }
    @Override
    public String createUser(SignupRequest req) throws Exception {
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());
        if(verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())){
            throw new Exception("Wrong otp...");
        }

        User user = userRepository.findByEmail(req.getEmail());
        if(user ==null){
            User createdUser = new User();
            createdUser.setEmail(req.getEmail());
            createdUser.setFullName(req.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setMobile("96643422");
            createdUser.setPassword(passwordEncoder.encode(req.getOtp()));

            user=userRepository.save(createdUser);

            //create cart to user
            Cart cart= new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        //generate jwt token
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(),null,authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }
    //To check user has access with email

    @Override
    public AuthResponse signing(LoginRequest req) throws Exception {
        String userName = req.getEmail();
        String otp = req.getOtp();

        Authentication authentication = authenticate(userName,otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login Success");

        // from authentication grab the role
        Collection<?extends GrantedAuthority> authorities=authentication.getAuthorities();
        String roleName = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(roleName));
        return authResponse;
    }

    private Authentication authenticate(String userName, String otp) throws Exception {
        UserDetails userDetails = customUserService.loadUserByUsername(userName);

        String SELLER_PREFIX="seller_";

        if(userName.startsWith(SELLER_PREFIX)){
            userName=userName.substring(SELLER_PREFIX.length());
        }

        if(userDetails==null){
            throw new BadCredentialsException("Invalid username or password.");
        }
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(userName);
        if (verificationCode == null || !verificationCode.getOtp().equals(otp)){
            throw new Exception("Wrong otp");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }


}
