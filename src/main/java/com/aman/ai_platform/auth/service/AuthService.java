package com.aman.ai_platform.auth.service;

import com.aman.ai_platform.auth.dto.mapper.AuthMapper;
import com.aman.ai_platform.auth.dto.request.RegisterRequestDTO;
import com.aman.ai_platform.auth.dto.response.LoginResponseDTO;
import com.aman.ai_platform.security.util.JwtUtils;
import com.aman.ai_platform.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.aman.ai_platform.security.service.CustomUserDetailsService;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;


    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils, CustomUserDetailsService customUserDetailsService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.customUserDetailsService = customUserDetailsService;
        this.userRepository = userRepository;
    }

    public LoginResponseDTO authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        return AuthMapper.toLoginResponseDTO(jwtUtils.generateToken(userDetails.getUsername()));
    }

    public void register(RegisterRequestDTO registerRequestDTO) {
        String trimmedEmail = registerRequestDTO.getEmail().trim();
        if(userRepository.existsByEmailIgnoreCase(trimmedEmail)) {
            throw new IllegalStateException("Emails Already Exists!");
        }
        registerRequestDTO.setEmail(trimmedEmail);
        User user = AuthMapper.toRegisterEntityDTO(registerRequestDTO);

    }

}
