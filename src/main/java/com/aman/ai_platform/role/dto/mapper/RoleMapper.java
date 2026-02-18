package com.aman.ai_platform.role.dto.mapper;

import com.aman.ai_platform.role.dto.request.CreateRoleDTO;
import com.aman.ai_platform.role.dto.response.RoleResponseDTO;
import com.aman.ai_platform.role.entity.Role;

public class RoleMapper {

    public static Role toEntity(CreateRoleDTO createRoleDTO){
        if(createRoleDTO == null) return null;

        return Role.builder()
                .name(createRoleDTO.getName())
                .build();
    }

    public static RoleResponseDTO roleDTO(Role role) {
        if(role == null) return null;

        return RoleResponseDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .status(role.getStatus())
                .createdAt(role.getCreatedAt())
                .build();
    }
}
