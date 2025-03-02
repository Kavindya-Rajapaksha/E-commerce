package com.ecommerce.ecommerce_multivendor.service.impl;

import com.ecommerce.ecommerce_multivendor.domain.OrderStatus;
import com.ecommerce.ecommerce_multivendor.domain.PaymentStatus;
import com.ecommerce.ecommerce_multivendor.entity.*;
import com.ecommerce.ecommerce_multivendor.repository.AddressRepository;
import com.ecommerce.ecommerce_multivendor.repository.OrderItemRepository;
import com.ecommerce.ecommerce_multivendor.repository.OrderRepository;
import com.ecommerce.ecommerce_multivendor.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {
        if(!user.getAddresses().contains(shippingAddress)){
            user.getAddresses().add(shippingAddress);
        }
        Address address = addressRepository.save(shippingAddress);

        //if there is multiple orders with multiple sellers then need to do separately.
        Map<Long, List<CartItems>> itemsBySeller = cart.getCartItems().stream()
                .collect(Collectors.groupingBy(item ->item.getProduct()
                        .getSeller().getId()));
        Set<Order>orders=new HashSet<>();
        for (Map.Entry<Long,List<CartItems>> entry:itemsBySeller.entrySet()){
            Long sellerId = entry.getKey();
            List<CartItems>items=entry.getValue();

            int totalOrderPrice = items.stream().mapToInt(
                    CartItems::getSellingPrice
            ).sum();

            int totalItem = items.stream().mapToInt(
                    CartItems::getQuantity
            ).sum();

            Order createdOrder = new Order();

            createdOrder.setUser(user);
            createdOrder.setSellerId(sellerId);
            createdOrder.setTotalMrpPrice(totalOrderPrice);
            createdOrder.setTotalSellingPrice(totalOrderPrice);
            createdOrder.setTotalItems(totalItem);
            createdOrder.setShippingAddress(shippingAddress);
            createdOrder.setOrderStatus(OrderStatus.PENDING);
            createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);

            Order savedOrder = orderRepository.save(createdOrder);
            orders.add(savedOrder);

            List<OrderItem> orderItems = new ArrayList<>();
            for (CartItems item:items){
                OrderItem orderItem= new OrderItem();
                orderItem.setOrder(savedOrder);
                orderItem.setMrpPrice(item.getMrpPrice());
                orderItem.setProduct(item.getProduct());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setSize(item.getSize());
                orderItem.setUserId(item.getUserId());
                orderItem.setSellingPrice(item.getSellingPrice());

                savedOrder.getOrderItems().add(orderItem);

                OrderItem savedOrderItem = orderItemRepository.save(orderItem);
                orderItems.add(savedOrderItem);
            }
        }
        return orders;
    }

    @Override
    public Order findOrderById(long id) throws Exception {

        return orderRepository.findById(id).orElseThrow(()->
        new Exception("Order nit found"));
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {

        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> sellersOrder(Long sellerId) {

        return orderRepository.findBySellerId(sellerId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        order.setOrderStatus(orderStatus);

        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId, User user) throws Exception {
        Order order = findOrderById(orderId);

        if(!user.getId().equals(order.getUser().getId())){
            throw new Exception("You do not have access to this order");
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    @Override
    public OrderItem getOrderItemById(Long id) throws Exception {
        return orderItemRepository.findById(id).orElseThrow(() ->
                new Exception("Order item not exist"));
    }

}
