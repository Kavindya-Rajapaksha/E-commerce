package com.ecommerce.ecommerce_multivendor.service;


import com.ecommerce.ecommerce_multivendor.entity.Order;
import com.ecommerce.ecommerce_multivendor.entity.Seller;
import com.ecommerce.ecommerce_multivendor.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Order order);
    List<Transaction> getTransactionsBySellerId(Seller seller);
    List<Transaction>getAllTransactions();
}
