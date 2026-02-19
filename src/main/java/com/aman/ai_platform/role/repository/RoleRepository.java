package com.aman.ai_platform.role.repository;

import com.aman.ai_platform.role.entity.Role;
import com.aman.ai_platform.role.entity.RoleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    boolean existsByNameIgnoreCase(String name);
    Optional<Role> findByNameIgnoreCase(String name);
    List<Role> findByStatus(RoleStatus status);
}
