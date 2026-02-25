package com.aman.ai_platform.auth.service;

import com.aman.ai_platform.security.util.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.aman.ai_platform.security.service.CustomUserDetailsService;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;


    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.customUserDetailsService = customUserDetailsService;
    }

    public String authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        return jwtUtils.generateToken(userDetails.getUsername());
    }

}
