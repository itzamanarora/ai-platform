package com.aman.ai_platform.role.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRoleDTO {

    @NotBlank(message = "Role name is required")
    private String name;
}
