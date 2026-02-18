package com.aman.ai_platform.role.service;

import com.aman.ai_platform.role.dto.request.CreateRoleDTO;
import com.aman.ai_platform.role.dto.response.RoleResponseDTO;
import com.aman.ai_platform.role.entity.Role;
import com.aman.ai_platform.role.entity.RoleStatus;
import com.aman.ai_platform.role.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public RoleResponseDTO createRole(CreateRoleDTO createRoleDTO){
        try {
            Role role = Role.builder()
                    .name(createRoleDTO.getName())
                    .status(RoleStatus.ACTIVE)
                    .build();

            Role savedRole = roleRepository.save(role);

            return RoleResponseDTO.builder()
                    .id(savedRole.getId())
                    .name(savedRole.getName())
                    .status(savedRole.getStatus())
                    .createdAt(savedRole.getCreatedAt())
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
