package com.spring.student.controller;

import com.spring.student.Services.TokenService;
import com.spring.student.model.AuthenticationBean;
import com.spring.student.model.JwtLogin;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Login")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class loginController {

    public TokenService tokenService;

    public loginController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    //    http://localhost:8080/signin
    @PostMapping("signin")
    public AuthenticationBean signin(@RequestBody JwtLogin loginUser) {
        return new AuthenticationBean(tokenService.login(loginUser));
    }
}
