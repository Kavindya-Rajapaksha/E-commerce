package com.ecommerce.ecommerce_multivendor.service.impl;

import com.ecommerce.ecommerce_multivendor.entity.Product;
import com.ecommerce.ecommerce_multivendor.entity.User;
import com.ecommerce.ecommerce_multivendor.entity.WishList;
import com.ecommerce.ecommerce_multivendor.repository.WishListRepository;
import com.ecommerce.ecommerce_multivendor.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private WishListRepository wishListRepository;

    @Override
    public WishList createWishList(User user) {
        WishList wishList = new WishList();
        wishList.setUser(user);

        return wishListRepository.save(wishList);
    }

    @Override
    public WishList getWishListByUserId(User user) {
        WishList wishList = wishListRepository.findByUserId(user.getId());
        if(wishList == null){
            wishList=createWishList(user);
        }
        return wishList;
    }

    @Override
    public WishList addProductToWishList(User user, Product product) {
        WishList wishList = getWishListByUserId(user);

        if(wishList.getProducts().contains(product)){
            wishList.getProducts().remove(product);
        }else{
            wishList.getProducts().add(product);
        }
        return wishListRepository.save(wishList);
    }
}
