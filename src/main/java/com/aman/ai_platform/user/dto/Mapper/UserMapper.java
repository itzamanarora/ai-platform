package com.aman.ai_platform.user.dto.Mapper;

import com.aman.ai_platform.role.dto.mapper.RoleMapper;
import com.aman.ai_platform.user.dto.request.CreateUserDTO;
import com.aman.ai_platform.user.dto.response.UserResponseDTO;
import com.aman.ai_platform.user.entity.User;

public class UserMapper {

    public static User toEntity(CreateUserDTO createUserDTO) {
        if(createUserDTO == null) return null;

        return User.builder()
                .email(createUserDTO.getEmail())
                .password(createUserDTO.getPassword())
                .build();
    }

    public static UserResponseDTO userResponseDTO(User user) {
        if (user == null) return null;

        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
