package com.spring.student.model;



public class AuthenticationBean {

    private String token;

    public AuthenticationBean(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
