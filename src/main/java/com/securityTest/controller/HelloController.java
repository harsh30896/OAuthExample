package com.securityTest.controller;


import com.securityTest.entity.User;
import com.securityTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
    public class HelloController {

        @Autowired
        UserService userService;

        @GetMapping("/")
        public Map<String, Object> home(@AuthenticationPrincipal OAuth2User principal) {
            Map<String, Object> response = new HashMap<>();
            if (principal != null) {
                response.put("name", principal.getAttribute("name"));
                response.put("email", principal.getAttribute("email"));
            } else {
                response.put("message", "User not authenticated");
            }

            String userName = principal.getAttribute("name");
            String userEmail= principal.getAttribute("email");

            User user=new User();
            user.setUserName(userName);
            user.setEmail(userEmail);
//  check

            userService.saveUser(user);

            return response;

        }


        @GetMapping("/login")
        public String getMessage(){
            return "Welcome to Oauth2";
        }

    }
