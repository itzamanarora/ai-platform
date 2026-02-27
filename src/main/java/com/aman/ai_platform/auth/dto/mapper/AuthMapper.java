package com.aman.ai_platform.auth.dto.mapper;

import com.aman.ai_platform.auth.dto.request.RegisterRequestDTO;
import com.aman.ai_platform.auth.dto.response.LoginResponseDTO;
import com.aman.ai_platform.user.entity.User;

public class AuthMapper {

    public static LoginResponseDTO toLoginResponseDTO(String accessToken) {
        if(accessToken == null) return null;

        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .build();
    }

    public static User toRegisterEntityDTO(RegisterRequestDTO registerRequestDTO){
        return User.builder()
                .email(registerRequestDTO.getEmail())
                .password(registerRequestDTO.getPassword())
                .build();
    }
}