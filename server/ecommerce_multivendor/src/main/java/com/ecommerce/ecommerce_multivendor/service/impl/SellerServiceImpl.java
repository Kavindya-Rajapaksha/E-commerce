package com.ecommerce.ecommerce_multivendor.service.impl;

import com.ecommerce.ecommerce_multivendor.config.JwtProvider;
import com.ecommerce.ecommerce_multivendor.domain.AccountStatus;
import com.ecommerce.ecommerce_multivendor.domain.USER_ROLE;
import com.ecommerce.ecommerce_multivendor.entity.Address;
import com.ecommerce.ecommerce_multivendor.entity.Seller;
import com.ecommerce.ecommerce_multivendor.exceptions.SellerException;
import com.ecommerce.ecommerce_multivendor.repository.AddressRepository;
import com.ecommerce.ecommerce_multivendor.repository.SellerRepository;
import com.ecommerce.ecommerce_multivendor.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    @Override
    public Seller getSellerProfile(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return this.getSellerByEmail(email);
    }

    //create new seller
    @Override
    public Seller createSeller(Seller seller) throws Exception {
        Seller sellerExist = sellerRepository.findByEmail(seller.getEmail());
        if(sellerExist != null){
            throw new Exception("Seller already exist use different Email.");
        }
        Address savedAddress = addressRepository.save(seller.getPickupAddress());

        Seller newSeller = new Seller();
        newSeller.setEmail(seller.getEmail());
        newSeller.setSellerName(seller.getSellerName());
        newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
        newSeller.setPickupAddress(savedAddress);
        newSeller.setGSTIN(seller.getGSTIN());
        newSeller.setRole(USER_ROLE.ROLE_SELLER);
        newSeller.setMobile(seller.getMobile());
        newSeller.setBankdetails(seller.getBankdetails());
        newSeller.setBusinessDetails(seller.getBusinessDetails());
        return sellerRepository.save(newSeller);
    }

    @Override
    public Seller getSellerById(Long id) throws SellerException {
        return sellerRepository.findById(id)
                .orElseThrow(()->new SellerException("Seller not found with id "+id));
    }

    @Override
    public Seller getSellerByEmail(String email) throws Exception {
        Seller seller = sellerRepository.findByEmail(email);
        if(seller == null){
            throw new Exception("Seller not found");
        }
        return seller;
    }

    @Override
    public List<Seller> getAllSellers(AccountStatus status) {
        return sellerRepository.findByAccountStatus(status);
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {
        Seller existingSeller = this.getSellerById(id);

        if(seller.getSellerName() != null){
            existingSeller.setSellerName(seller.getSellerName());
        }
        if(seller.getMobile() != null){
            existingSeller.setMobile(seller.getMobile());
        }
        if(seller.getEmail() != null){
            existingSeller.setEmail(seller.getEmail());
        }
        if(seller.getBusinessDetails()!=null
                && seller.getBusinessDetails().getBusinessName() != null
        ){
            existingSeller.getBusinessDetails().setBusinessName(
                    seller.getBusinessDetails().getBusinessName()
            );
        }
        if(seller.getBankdetails() != null
                && seller.getBankdetails().getAccountName() != null
                && seller.getBankdetails().getIfscCode() != null
                && seller.getBankdetails().getAccountNumber() != null
        ){
            existingSeller.getBankdetails().setAccountName(
                    seller.getBankdetails().getAccountName()
            );
            existingSeller.getBankdetails().setIfscCode(
                    seller.getBankdetails().getIfscCode()
            );
            existingSeller.getBankdetails().setAccountNumber(
                    seller.getBankdetails().getAccountNumber()
            );
        }
        if(seller.getPickupAddress()!= null
                && seller.getPickupAddress().getAddress() !=null
                && seller.getPickupAddress().getMobile() != null
                && seller.getPickupAddress().getCity() != null
                && seller.getPickupAddress().getState() != null
        ){
            existingSeller.getPickupAddress().setAddress(
                    seller.getPickupAddress().getAddress()
            );
            existingSeller.getPickupAddress().setMobile(
                    seller.getPickupAddress().getMobile()
            );
            existingSeller.getPickupAddress().setCity(
                    seller.getPickupAddress().getCity()
            );
            existingSeller.getPickupAddress().setState(
                    seller.getPickupAddress().getState()
            );
        }
        if(seller.getGSTIN() != null){
            existingSeller.setGSTIN(seller.getGSTIN());
        }

        return sellerRepository.save(existingSeller);
    }

    @Override
    public void deleteSeller(Long id) throws Exception {
        Seller seller = getSellerById(id);
        sellerRepository.delete(seller);
    }

    @Override
    public Seller verifyEmail(String email, String otp) throws Exception {
        Seller seller =  getSellerByEmail(email);
        seller.setEmailVerified(true);

        return sellerRepository.save(seller);
    }

    @Override
    public Seller updateSellerAccountStatus(long sellerId, AccountStatus status) throws Exception {
        Seller seller = getSellerById(sellerId);
        seller.setAccountStatus(status);

        return sellerRepository.save(seller);
    }
}
