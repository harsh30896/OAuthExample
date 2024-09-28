package com.securityTest.controller;

import com.securityTest.entity.User;
import com.securityTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // This endpoint requires OAuth2 authentication
    @GetMapping("/profile")
    public Map<String, Object> getUserProfile(@AuthenticationPrincipal OAuth2User principal) {
        Map<String, Object> response = new HashMap<>();
        if (principal != null) {
            response.put("name", principal.getAttribute("name"));
            response.put("email", principal.getAttribute("email"));
        } else {
            response.put("message", "User not authenticated");
        }
        return response;
    }

    @GetMapping("/details")
    public User getUserDetails(@AuthenticationPrincipal OAuth2User principal) {
        String userName = principal.getAttribute("name");
        String userEmail = principal.getAttribute("email");

        User user = new User();
        user.setUserName(userName);
        user.setEmail(userEmail);
        userService.saveUser(user);

        return user;
    }
}
