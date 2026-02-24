package com.aman.ai_platform.role.controller;

import com.aman.ai_platform.role.dto.request.CreateRoleDTO;
import com.aman.ai_platform.role.dto.response.RoleResponseDTO;
import com.aman.ai_platform.role.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponseDTO createRole(@Valid @RequestBody CreateRoleDTO createRoleDTO){
        return roleService.createRole(createRoleDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoleResponseDTO> getRole(@RequestParam(required = false) String status) {
        return roleService.getRoles(status);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponseDTO updateRole(@PathVariable UUID id,
                                      @Valid @RequestBody CreateRoleDTO updateRoleDTO) {
        return roleService.updateRole(id, updateRoleDTO);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponseDTO updateRoleStatus(@PathVariable UUID id,
                                            @RequestParam String status) {
        return roleService.updateRoleStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable UUID id) {
        roleService.softDeleteRole(id);
    }
}
