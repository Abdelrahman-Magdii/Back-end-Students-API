package com.spring.student.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Read the Authorization header, where the JWT token should be
        String header = request.getHeader(JwtProperties.HEADER_STRING);

        // If header does not contain Bearer or is null, delegate to Spring implementation and exit
        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // Try to authenticate the user based on the token
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue filter execution
        chain.doFilter(request, response);
    }

    // Extract and validate the JWT token
    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");

        if (token != null) {
            try {
                // Parse the token and validate it
                String userName = JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
                        .build()
                        .verify(token)
                        .getSubject();

                if (userName != null) {
                    // Return an authentication object with the username and roles (if available)
                    return new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
                }
            } catch (JWTVerificationException e) {
                // Handle token verification exception
                e.printStackTrace(); // Consider logging this exception instead
            }
        }
        return null;
    }
}
