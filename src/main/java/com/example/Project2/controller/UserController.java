package com.example.Project2.controller;

import com.example.Project2.model.User;
import com.example.Project2.model.request.LoginRequest;
import com.example.Project2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/register")
    public User createUser(@RequestBody User userObj){
        System.out.println("calling create user ==========>");
        return userService.createUser(userObj);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        System.out.println("calling login request in service ==========>");
        return userService.loginUser(loginRequest);
    }
    @GetMapping("/reset-password")
    public void passwordReset(@RequestBody User user){
        System.out.println("calling reset in controller ========>");
        userService.resetPassword(user.getEmailAddress());
    }
    @PostMapping("/reset-password")
    public void passwordResetActivator(@RequestBody User user ,@RequestParam String token){
        System.out.println("calling reset activator in controller ========>");
        userService.resetPasswordActivator(token,user);
        //userService.resetPassword(user.getEmailAddress());
    }
    @PutMapping("/change-password")
    public void changePassword(@RequestBody String oldPass ,@RequestBody String newPass){
        System.out.println("calling change password in controller ========>");
        userService.changePassword(oldPass,newPass );
        //userService.resetPassword(user.getEmailAddress());
    }
    @GetMapping("/register/verify")
    public void validate(@RequestParam String token){
        System.out.println("calling change password in controller ========>");
        userService.validate(token);
        //userService.resetPassword(user.getEmailAddress());
    }
//
//    @GetMapping("/tester")
//    public void tester(HttpServletRequest http){
//        userService.test( http);
//    }
}