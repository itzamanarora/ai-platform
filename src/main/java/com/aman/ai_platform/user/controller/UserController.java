package com.aman.ai_platform.user.controller;

import com.aman.ai_platform.user.dto.request.CreateUserDTO;
import com.aman.ai_platform.user.dto.response.UserResponseDTO;
import com.aman.ai_platform.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "User management APIs")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create user")
    public UserResponseDTO createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        return userService.createUser(createUserDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
