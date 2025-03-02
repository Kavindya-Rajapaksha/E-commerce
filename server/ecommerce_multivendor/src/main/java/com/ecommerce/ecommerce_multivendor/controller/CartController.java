package com.ecommerce.ecommerce_multivendor.controller;

import com.ecommerce.ecommerce_multivendor.entity.Cart;
import com.ecommerce.ecommerce_multivendor.entity.CartItems;
import com.ecommerce.ecommerce_multivendor.entity.Product;
import com.ecommerce.ecommerce_multivendor.entity.User;
import com.ecommerce.ecommerce_multivendor.exceptions.ProductException;
import com.ecommerce.ecommerce_multivendor.request.AddItemRequest;
import com.ecommerce.ecommerce_multivendor.response.ApiResponse;
import com.ecommerce.ecommerce_multivendor.service.CartItemService;
import com.ecommerce.ecommerce_multivendor.service.CartService;
import com.ecommerce.ecommerce_multivendor.service.ProductService;
import com.ecommerce.ecommerce_multivendor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Cart>findUserCartHandler(
            @RequestHeader("Authorization")String jwt) throws ProductException,Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);
        System.out.println( "cart:"+cart.getUser().getEmail());
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItems>addItemToCart(@RequestBody AddItemRequest req,
                                                  @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.findProductById(req.getProductId());

        CartItems items = cartService.addCartItems(
                user,
                product,
                req.getSize(),
                req.getQuantity());

        ApiResponse res = new ApiResponse();
        res.setMessage("Item added to cart successfully");

        return new ResponseEntity<>(items, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse>deleteItemHandler(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(),cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Item Remove from Cart");

        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItems>updateCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestBody CartItems cartItems,
            @RequestHeader("Authorization")String jwt) throws Exception {

        User user= userService.findUserByJwtToken(jwt);
        CartItems updatedCartItem = null;
        if(cartItems.getQuantity()>0){
            updatedCartItem=cartItemService.updateCartItem(
                    user.getId(),
                    cartItemId,
                    cartItems);
        }
        return  new ResponseEntity<>(updatedCartItem,HttpStatus.ACCEPTED);
    }

}
