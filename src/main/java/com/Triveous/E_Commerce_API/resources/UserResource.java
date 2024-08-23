package com.Triveous.E_Commerce_API.resources;

import com.Triveous.E_Commerce_API.models.UserModel;
import com.Triveous.E_Commerce_API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserResource {
    @Autowired
    private UserService userService;

    @PostMapping("/saveUserDetails")
    public ResponseEntity<?> saveUserDetails(@RequestBody UserModel userModel){
        return userService.saveUserDetails(userModel);
    }

}
