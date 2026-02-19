package com.aman.ai_platform.role.controller;

import com.aman.ai_platform.role.dto.request.CreateRoleDTO;
import com.aman.ai_platform.role.dto.response.RoleResponseDTO;
import com.aman.ai_platform.role.service.RoleService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
