package com.spring.student.controller;


import com.spring.student.model.AuthenticationBean;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication")
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
