package com.ecommerce.ecommerce_multivendor.controller;

import com.ecommerce.ecommerce_multivendor.domain.PaymentMethod;
import com.ecommerce.ecommerce_multivendor.entity.*;
import com.ecommerce.ecommerce_multivendor.repository.PaymentOrderRepository;
import com.ecommerce.ecommerce_multivendor.response.PaymentLinkResponse;
import com.ecommerce.ecommerce_multivendor.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;
    private final CartService cartService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final PaymentService paymentService;
    private final PaymentOrderRepository paymentOrderRepository;


    @PostMapping()
    public ResponseEntity<PaymentLinkResponse>createOrderHandler(
            @RequestBody Address shippingAddress,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization")String jwt)
            throws Exception  {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);

        Set<Order>orders = orderService.createOrder(user,shippingAddress,cart);

        PaymentOrder paymentOrder = paymentService.createOrder(user,orders);
        PaymentLinkResponse res = new PaymentLinkResponse();

        String paymentUrl = paymentService.createStripePaymentLink(
                user,
                paymentOrder.getAmount(),
                paymentOrder.getId());
        res.setPayment_link_url(paymentUrl);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>>usersOrderHistoryHandler(
            @RequestHeader("Authorization")
            String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders= orderService.userOrderHistory(user.getId());
        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<Order>getOrderById(@PathVariable Long orderId,
                                             @RequestHeader("Authorization")String jwt)throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Order order=orderService.findOrderById(orderId);
        return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
    }

    @GetMapping("/item/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(
            @PathVariable Long orderItemId,
            @RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        OrderItem orderItem=orderService.getOrderItemById(orderItemId);
        return new ResponseEntity<>(orderItem,HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order>cancelOrder(
            @PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt)throws Exception{
        User user= userService.findUserByJwtToken(jwt);
        Order order = orderService.cancelOrder(orderId,user);

        Seller seller=sellerService.getSellerById(order.getSellerId());
        SellerReport sellerReport = sellerReportService.getSellerReport(seller);

        sellerReport.setCanceledOrders(sellerReport.getCanceledOrders());
        sellerReport.setCanceledOrders((int) (sellerReport.getTotalRefunds()+order.getTotalSellingPrice()));
        sellerReportService.updateSellerReport(sellerReport);

        return ResponseEntity.ok(order);
    }
}
