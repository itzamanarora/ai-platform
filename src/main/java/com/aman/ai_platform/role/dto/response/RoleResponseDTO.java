package com.aman.ai_platform.role.dto.response;

import com.aman.ai_platform.role.entity.Role;
import com.aman.ai_platform.role.entity.RoleStatus;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponseDTO {
    private UUID id;
    private String name;
    private RoleStatus status;
    private Instant createdAt;
}
