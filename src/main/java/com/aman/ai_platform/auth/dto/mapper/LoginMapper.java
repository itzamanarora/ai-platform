package com.aman.ai_platform.auth.dto.mapper;

import com.aman.ai_platform.auth.dto.request.LoginRequestDTO;
import com.aman.ai_platform.auth.dto.response.LoginResponseDTO;

public class LoginMapper {
    public static LoginResponseDTO toLoginResponseDTO(String accessToken) {
        if(accessToken == null) return null;

        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .build();
    }
}