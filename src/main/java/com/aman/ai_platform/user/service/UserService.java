package com.aman.ai_platform.user.service;

import com.aman.ai_platform.user.entity.User;
import com.aman.ai_platform.user.entity.UserStatus;
import com.aman.ai_platform.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        user.setStatus(UserStatus.ACTIVE);
        return userRepository.save(user);
    }
}
