package com.ecommerce.ecommerce_multivendor.service.impl;

import com.ecommerce.ecommerce_multivendor.entity.Order;
import com.ecommerce.ecommerce_multivendor.entity.Seller;
import com.ecommerce.ecommerce_multivendor.entity.Transaction;
import com.ecommerce.ecommerce_multivendor.repository.SellerRepository;
import com.ecommerce.ecommerce_multivendor.repository.TransactionRepository;
import com.ecommerce.ecommerce_multivendor.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final SellerRepository sellerRepository;

    @Override
    public Transaction createTransaction(Order order) {
        Seller seller = sellerRepository.findById(order.getSellerId()).get();

        Transaction transaction = new Transaction();
        transaction.setSeller(seller);
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsBySellerId(Seller seller) {
        return transactionRepository.findBySellerId(seller.getId());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
