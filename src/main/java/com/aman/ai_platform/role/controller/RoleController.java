package com.aman.ai_platform.role.controller;

import com.aman.ai_platform.role.dto.request.CreateRoleDTO;
import com.aman.ai_platform.role.dto.response.RoleResponseDTO;
import com.aman.ai_platform.role.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Roles", description = "Role management APIs")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/admin/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create role")
    public RoleResponseDTO createRole(@Valid @RequestBody CreateRoleDTO createRoleDTO){
        return roleService.createRole(createRoleDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List roles", description = "Optionally filter by status (ACTIVE/INACTIVE/DELETED)")
    public List<RoleResponseDTO> getRole(@RequestParam(required = false) String status) {
        return roleService.getRoles(status);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update role")
    public RoleResponseDTO updateRole(@PathVariable UUID id,
                                      @Valid @RequestBody CreateRoleDTO updateRoleDTO) {
        return roleService.updateRole(id, updateRoleDTO);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update role status", description = "Set role status to ACTIVE, INACTIVE, or DELETED")
    public RoleResponseDTO updateRoleStatus(@PathVariable UUID id,
                                            @RequestParam String status) {
        return roleService.updateRoleStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Soft delete role", description = "Does not remove the role; sets status to DELETED")
    public void deleteRole(@PathVariable UUID id) {
        roleService.softDeleteRole(id);
    }
}
