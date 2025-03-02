package com.ecommerce.ecommerce_multivendor.controller;

import com.ecommerce.ecommerce_multivendor.entity.Product;
import com.ecommerce.ecommerce_multivendor.entity.Seller;
import com.ecommerce.ecommerce_multivendor.exceptions.ProductException;
import com.ecommerce.ecommerce_multivendor.request.CreateProductRequest;
import com.ecommerce.ecommerce_multivendor.service.ProductService;
import com.ecommerce.ecommerce_multivendor.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers/products")
public class SellerProductController {
    private final ProductService productService;
    private final SellerService sellerService;

    @GetMapping()
    public ResponseEntity<List<Product>>getProductSellerId(
            @RequestHeader("Authorization")String jwt)throws Exception{
        Seller seller = sellerService.getSellerProfile(jwt);
        List<Product> products = productService.getProductBySellerId(seller.getId());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product>createProduct(
            @RequestBody CreateProductRequest request,
            @RequestHeader("Authorization")String jwt)
        throws Exception{
        Seller seller = sellerService.getSellerProfile(jwt);
        Product product=productService.createProduct(request,seller);
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void>deleteProduct(@PathVariable Long productId){
        try{
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ProductException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product>updateProduct(@PathVariable Long productId,
                                                @RequestBody Product product) throws ProductException {
        try{
            Product updatedProduct = productService.updateProducts(productId,product);
            return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
        }catch (ProductException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
