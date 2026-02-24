package com.aman.ai_platform.role.service;

import com.aman.ai_platform.role.dto.mapper.RoleMapper;
import com.aman.ai_platform.role.dto.request.CreateRoleDTO;
import com.aman.ai_platform.role.dto.response.RoleResponseDTO;
import com.aman.ai_platform.role.entity.Role;
import com.aman.ai_platform.role.entity.RoleStatus;
import com.aman.ai_platform.role.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public RoleResponseDTO createRole(CreateRoleDTO createRoleDTO){
        String trimmedName = createRoleDTO
                .getName()
                .trim();

        if(roleRepository.existsByNameIgnoreCase(trimmedName)) {
            throw new IllegalStateException("Role Already Exists");
        }

        Role role = Role.builder()
                .name(trimmedName.toUpperCase())
                .status(RoleStatus.ACTIVE)
                .build();

        Role savedRole = roleRepository.save(role);

        return RoleMapper.roleDTO(savedRole);
    }

    public List<RoleResponseDTO> getRoles(String status){
        List<Role> roles;

        if(status != null) {
            roles = roleRepository.findByStatus(RoleStatus.valueOf(status.toUpperCase()));
        } else {
            roles = roleRepository.findAll();
        }

        return roles.stream()
                .map(RoleMapper::roleDTO)
                .toList();
    }

    @Transactional
    public RoleResponseDTO updateRole(UUID id, CreateRoleDTO updateRoleDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Role not found"));

        String trimmedName = updateRoleDTO
                .getName()
                .trim();

        String normalizedName = trimmedName.toUpperCase();

        if (!normalizedName.equalsIgnoreCase(role.getName())
                && roleRepository.existsByNameIgnoreCase(trimmedName)) {
            throw new IllegalStateException("Role Already Exists");
        }

        role.setName(normalizedName);

        Role updatedRole = roleRepository.save(role);

        return RoleMapper.roleDTO(updatedRole);
    }

    @Transactional
    public RoleResponseDTO updateRoleStatus(UUID id, String status) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Role not found"));

        RoleStatus newStatus = RoleStatus.valueOf(status.toUpperCase());
        role.setStatus(newStatus);

        Role updatedRole = roleRepository.save(role);

        return RoleMapper.roleDTO(updatedRole);
    }

    @Transactional
    public void softDeleteRole(UUID id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Role not found"));

        role.setStatus(RoleStatus.DELETED);
        roleRepository.save(role);
    }
}
