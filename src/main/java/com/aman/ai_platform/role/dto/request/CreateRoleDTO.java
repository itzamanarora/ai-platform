package com.aman.ai_platform.role.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRoleDTO {

    @NotBlank(message = "Role name is required")
    @Size(min = 2, max = 50, message = "Role name must be 2-50 characters")
    private String name;
}
