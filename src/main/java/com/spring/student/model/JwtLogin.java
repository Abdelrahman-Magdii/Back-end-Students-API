package com.spring.student.model;

public class JwtLogin {
    private String username;
    private String password;

    public JwtLogin() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
