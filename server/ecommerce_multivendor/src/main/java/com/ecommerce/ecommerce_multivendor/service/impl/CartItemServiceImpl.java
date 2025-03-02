package com.ecommerce.ecommerce_multivendor.service.impl;

import com.ecommerce.ecommerce_multivendor.entity.CartItems;
import com.ecommerce.ecommerce_multivendor.entity.User;
import com.ecommerce.ecommerce_multivendor.repository.CartItemRepository;
import com.ecommerce.ecommerce_multivendor.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItems updateCartItem(Long userId, Long id, CartItems cartItems) throws Exception {
        CartItems item = findCartItemById(id);
        User cartItemUser = item.getCart().getUser();

        if(cartItemUser.getId().equals(userId)){
            item.setQuantity(cartItems.getQuantity());
            item.setMrpPrice(item.getQuantity()*item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity()*item.getProduct().getSellingPrice());
            return cartItemRepository.save(item);
        }
        throw new RuntimeException("You can not update this cart item");
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItems item = findCartItemById(cartItemId);
        User cartItemUser = item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)){
            cartItemRepository.delete(item);
        }else{

            throw new Exception("You can not remove this item.");
        }
    }

    @Override
    public CartItems findCartItemById(Long id) throws Exception {

        return cartItemRepository.findById(id).orElseThrow(()->
                new Exception("Cart item not found with Id - "+id));
    }
}
