package com.ecommerce.ecommerce_multivendor.controller;

import com.ecommerce.ecommerce_multivendor.entity.Seller;
import com.ecommerce.ecommerce_multivendor.entity.Transaction;
import com.ecommerce.ecommerce_multivendor.service.SellerService;
import com.ecommerce.ecommerce_multivendor.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final SellerService sellerService;

    @GetMapping("/seller")
    public ResponseEntity<List<Transaction>>getTransactionBySeller(
            @RequestHeader("Authorization")String jwt) throws Exception{
        Seller seller = sellerService.getSellerProfile(jwt);

        List<Transaction> transactions= transactionService.getTransactionsBySellerId(seller);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>>getAllTransactions(){
        List<Transaction> transactions=transactionService.getAllTransactions();

        return ResponseEntity.ok(transactions);
    }
}
