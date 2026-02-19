package com.aman.ai_platform.user.repository;

import com.aman.ai_platform.user.entity.User;
import com.aman.ai_platform.user.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Boolean existsByEmailIgnoreCase(String email);
    Optional<User> findByEmail(String email);
    Optional<List<User>> findByStatus(UserStatus status);
}
