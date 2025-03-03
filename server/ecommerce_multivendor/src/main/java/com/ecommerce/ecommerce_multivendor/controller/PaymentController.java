package com.ecommerce.ecommerce_multivendor.controller;

import com.ecommerce.ecommerce_multivendor.entity.*;
import com.ecommerce.ecommerce_multivendor.response.ApiResponse;
import com.ecommerce.ecommerce_multivendor.response.PaymentLinkResponse;
import com.ecommerce.ecommerce_multivendor.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService userService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final TransactionService transactionService;


    @GetMapping("/{paymentId}")
    public ResponseEntity<ApiResponse> paymentSuccessHandler(
            @PathVariable String paymemntId,
            @RequestParam String paymentLinkId,
            @RequestHeader ("Authorization") String jwt)throws Exception{

        User user=userService.findUserByJwtToken(jwt);
        PaymentLinkResponse paymentLinkResponse;

        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);

        boolean paymentSuccess = paymentService.ProceedPaymentOrder(
                paymentOrder,
                paymemntId,
                paymentLinkId
        );
        if(paymentSuccess){
            for (Order order:paymentOrder.getOrders()){
                //create new transaction
                transactionService.createTransaction(order);
                Seller seller = sellerService.getSellerById(order.getSellerId());
                SellerReport sellerReport=sellerReportService.getSellerReport(seller);
                sellerReport.setTotalOrders(sellerReport.getTotalOrders()+1);
                sellerReport.setTotalEarnings(sellerReport.getTotalEarnings()+order.getTotalSellingPrice());
                sellerReport.setTotalSales(sellerReport.getTotalSales()+order.getOrderItems().size());
                sellerReportService.updateSellerReport(sellerReport);
            }
        }
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Payment Success");

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
