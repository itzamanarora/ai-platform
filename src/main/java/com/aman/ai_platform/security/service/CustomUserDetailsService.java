package com.aman.ai_platform.security.service;

import com.aman.ai_platform.user.entity.User;
import com.aman.ai_platform.user.entity.UserStatus;
import com.aman.ai_platform.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnorecase(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with this email.")
        );

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_"+ user.getRole().getName())
                .accountLocked(user.getStatus() != UserStatus.ACTIVE)
                .build();
    }
}
