package com.spring.student.controller;


import com.spring.student.model.AuthenticationBean;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class BasicAuthenticationController {

//    http://localhost:8080/auth
    @PostMapping("auth")
    public AuthenticationBean BasicAuthentication() {
        return new AuthenticationBean("is Authentication Controller ");
    }
}
