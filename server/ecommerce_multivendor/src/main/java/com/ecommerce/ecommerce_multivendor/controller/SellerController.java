package com.ecommerce.ecommerce_multivendor.controller;

import com.ecommerce.ecommerce_multivendor.config.JwtProvider;
import com.ecommerce.ecommerce_multivendor.domain.AccountStatus;
import com.ecommerce.ecommerce_multivendor.entity.Seller;
import com.ecommerce.ecommerce_multivendor.entity.SellerReport;
import com.ecommerce.ecommerce_multivendor.entity.VerificationCode;
import com.ecommerce.ecommerce_multivendor.exceptions.SellerException;
import com.ecommerce.ecommerce_multivendor.repository.VerificationCodeRepository;
import com.ecommerce.ecommerce_multivendor.request.LoginRequest;
import com.ecommerce.ecommerce_multivendor.response.ApiResponse;
import com.ecommerce.ecommerce_multivendor.response.AuthResponse;
import com.ecommerce.ecommerce_multivendor.service.AuthService;
import com.ecommerce.ecommerce_multivendor.service.EmailService;
import com.ecommerce.ecommerce_multivendor.service.SellerReportService;
import com.ecommerce.ecommerce_multivendor.service.SellerService;
import com.ecommerce.ecommerce_multivendor.utills.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller") //all the endpoint start with seller
public class SellerController {
    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;

    private final SellerReportService sellerReportService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse>loginSeller(
            @RequestBody LoginRequest req
            ) throws Exception {
        String otp = req.getOtp();
        String email = req.getEmail();

        req.setEmail("seller_"+email);
        AuthResponse authResponse =  authService.signing(req);
        return ResponseEntity.ok(authResponse);
    }
    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller>verifySellerEmail(
            @PathVariable String otp)throws Exception{
        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);

        if(verificationCode == null || !verificationCode.getOtp().equals(otp)){

            throw new Exception("Wrong otp...");
        }
        Seller seller = sellerService.verifyEmail(verificationCode.getEmail(),otp);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller>createSeller(@RequestBody Seller seller) throws Exception, MessagingException {
        Seller savedSeller = sellerService.createSeller(seller);

        System.out.println("saved seller:" +savedSeller);
        String otp= OtpUtil.generateOtp();

        System.out.println("otp:"+otp);
        VerificationCode verificationCode=new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(seller.getEmail());
        verificationCodeRepository.save(verificationCode);

        String subject = "Email Verification Code";
        String text = "Welcome ..... Verify your account using this otp";
        String frontend_url = "http://localhost/verify-seller";
        emailService.sendVerificationOtpEmail(seller.getEmail(),verificationCode.getOtp(),subject,text+frontend_url);
        return new ResponseEntity<>(savedSeller,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller>getSellerById(@PathVariable Long id) throws SellerException {
        Seller seller = sellerService.getSellerById(id);
        return new ResponseEntity<>(seller,HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Seller>getSellerByJwt(
            @RequestHeader("Authorization")String jwt) throws Exception{
        Seller seller = sellerService.getSellerProfile(jwt);
        return new ResponseEntity<>(seller,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seller>>getAllSellers(
            @RequestParam(required = false)AccountStatus status){
        List<Seller>sellers = sellerService.getAllSellers(status);
        return ResponseEntity.ok(sellers);
    }

    @PatchMapping()
    public ResponseEntity<Seller>updateSeller(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Seller seller
    )throws Exception{
        Seller profile = sellerService.getSellerProfile(jwt);
        Seller updatedSeller = sellerService.updateSeller(profile.getId(),seller);
        return ResponseEntity.ok(updatedSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id)throws Exception{
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/report")
    public ResponseEntity<SellerReport>getSellerReport(
            @RequestHeader("Authorization")String jwt)throws Exception{
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        Seller seller = sellerService.getSellerByEmail(email);
        SellerReport sellerReport = sellerReportService.getSellerReport(seller);
        return new ResponseEntity<>(sellerReport,HttpStatus.OK);
    }

}
