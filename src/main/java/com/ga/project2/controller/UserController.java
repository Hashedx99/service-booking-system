package com.ga.project2.controller;

import com.ga.project2.exception.UserNotAuthorizedException;
import com.ga.project2.model.User;
import com.ga.project2.model.UserProfile;
import com.ga.project2.model.request.ChangePasswordRequest;
import com.ga.project2.model.request.ImageModel;
import com.ga.project2.model.request.LoginRequest;
import com.ga.project2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public void changePassword(@RequestBody ChangePasswordRequest request){
        System.out.println("calling change password in controller ========>");
        userService.changePassword(request.getOldPass(), request.getNewPass() );
        //userService.resetPassword(user.getEmailAddress());
    }
    @GetMapping("/register/verify")
    public void validate(@RequestParam String token){
        System.out.println("calling change password in controller ========>");
        userService.validate(token);
        System.out.println("blahblah");
        //userService.resetPassword(user.getEmailAddress());
    }
    @DeleteMapping("/delete")
    public void softDelete(){
        System.out.println("calling soft delete user in user controller ========>");
        userService.softDelete();
    }

    @DeleteMapping("/delete-user")
    public void softDeleteUser(@RequestParam Long userId) throws UserNotAuthorizedException {
        userService.deleteUserById(userId);
    }

    @PutMapping("/set-image")
    public User setImage(ImageModel image){
        System.out.println("calling set image in controller ========>");
        return userService.setUserImage(image);
    }

    @PatchMapping("/update")
    public User updateUserDetails(@RequestBody UserProfile userProfile) {
        System.out.println("calling update user details in controller ========>");
        return userService.updateUserDetails(userProfile);
    }
}