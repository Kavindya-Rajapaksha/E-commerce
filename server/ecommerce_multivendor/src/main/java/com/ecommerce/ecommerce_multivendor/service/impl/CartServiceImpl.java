package com.ecommerce.ecommerce_multivendor.service.impl;

import com.ecommerce.ecommerce_multivendor.entity.Cart;
import com.ecommerce.ecommerce_multivendor.entity.CartItems;
import com.ecommerce.ecommerce_multivendor.entity.Product;
import com.ecommerce.ecommerce_multivendor.entity.User;
import com.ecommerce.ecommerce_multivendor.repository.CartItemRepository;
import com.ecommerce.ecommerce_multivendor.repository.CartRepository;
import com.ecommerce.ecommerce_multivendor.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItems addCartItems(User user, Product product, String size, int quantity) {
        Cart cart = findUserCart(user);
        CartItems isPresent = cartItemRepository.findByCartAndProductAndSize(cart,product,size);

        if(isPresent == null){
            CartItems cartItems = new CartItems();
            cartItems.setProduct(product);
            cartItems.setQuantity(quantity);
            cartItems.setUserId(user.getId());
            cartItems.setSize(size);
            cartItems.setCart(cart);

            int totalPrice =quantity*product.getSellingPrice();
            cartItems.setSellingPrice(totalPrice);
            cartItems.setMrpPrice(quantity*product.getMrpPrice());

            cart.getCartItems().add(cartItems);

            return cartItemRepository.save(cartItems);
        }
        return isPresent;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart=cartRepository.findByUserId(user.getId());

        int totalPrice =0;
        int totalDiscountedPrice = 0;
        int totalItem =0;

        for(CartItems cartItems:cart.getCartItems()){
            totalPrice+=cartItems.getMrpPrice();
            totalDiscountedPrice+=cartItems.getSellingPrice();
            totalItem+=cartItems.getQuantity();
        }
        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice,totalDiscountedPrice));
        return cart;
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if(mrpPrice<=0){
            return 0;
        }
        double discount = mrpPrice-sellingPrice;
        double discountPercentage = (discount/mrpPrice)*100;
        return (int)discountPercentage;
    }

}
