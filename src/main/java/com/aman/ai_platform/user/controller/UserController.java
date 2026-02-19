package com.aman.ai_platform.user.controller;

import com.aman.ai_platform.user.dto.request.CreateUserDTO;
import com.aman.ai_platform.user.dto.response.UserResponseDTO;
import com.aman.ai_platform.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        return userService.createUser(createUserDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
