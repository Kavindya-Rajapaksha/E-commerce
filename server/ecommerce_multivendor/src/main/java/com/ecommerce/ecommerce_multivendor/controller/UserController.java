package com.ecommerce.ecommerce_multivendor.controller;

import com.ecommerce.ecommerce_multivendor.entity.User;
import com.ecommerce.ecommerce_multivendor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //class act as a controller
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @GetMapping("/users/profile")
    public ResponseEntity<User> createUserHandler(
            @RequestHeader ("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        return ResponseEntity.ok(user);
    }
}