package com.aman.ai_platform.auth.service;

import com.aman.ai_platform.auth.dto.mapper.AuthMapper;
import com.aman.ai_platform.auth.dto.request.RegisterRequestDTO;
import com.aman.ai_platform.auth.dto.response.LoginResponseDTO;
import com.aman.ai_platform.role.entity.Role;
import com.aman.ai_platform.role.repository.RoleRepository;
import com.aman.ai_platform.security.util.JwtUtils;
import com.aman.ai_platform.user.entity.User;
import com.aman.ai_platform.user.entity.UserStatus;
import com.aman.ai_platform.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.aman.ai_platform.security.service.CustomUserDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils, CustomUserDetailsService customUserDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.customUserDetailsService = customUserDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public LoginResponseDTO authenticate(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String username = authentication.getName();
        return AuthMapper.toLoginResponseDTO(jwtUtils.generateToken(username));
    }

    @Transactional
    public void register(RegisterRequestDTO registerRequestDTO) {
        String trimmedEmail = registerRequestDTO.getEmail().trim();
        if(userRepository.existsByEmailIgnoreCase(trimmedEmail)) {
            throw new IllegalStateException("Emails Already Exists!");
        }
        Role role = roleRepository.findByNameIgnoreCase("USER").orElseThrow(
                () -> new RuntimeException("Role is not Found.")
        );
        User user = AuthMapper.toRegisterEntityDTO(registerRequestDTO);
        user.setEmail(trimmedEmail);
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.setRole(role);
        userRepository.save(user);
    }

}
