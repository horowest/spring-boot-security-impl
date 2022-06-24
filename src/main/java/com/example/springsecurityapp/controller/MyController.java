package com.example.springsecurityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurityapp.entity.UserInfo;
import com.example.springsecurityapp.util.JwtUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MyController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String home() {
        return "welcome";
    }

    @GetMapping("/inside")
    public String inside() {
        return "YOURE INSIDE";
    }

    @PostMapping(value = "/hi")
    public String hi(@RequestBody UserInfo userInfo) {
        return "hi " + userInfo.getUsername();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> generateToken(@RequestBody UserInfo user) throws Exception {
        UsernamePasswordAuthenticationToken token
            = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        
        try {
            authenticationManager.authenticate(token);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username/password");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(jwtUtil.generateToke(user.getUsername()));
    }
    
} 
