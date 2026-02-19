    package com.aman.ai_platform.user.service;

    import com.aman.ai_platform.role.entity.Role;
    import com.aman.ai_platform.role.repository.RoleRepository;
    import com.aman.ai_platform.user.dto.Mapper.UserMapper;
    import com.aman.ai_platform.user.dto.request.CreateUserDTO;
    import com.aman.ai_platform.user.dto.response.UserResponseDTO;
    import com.aman.ai_platform.user.entity.User;
    import com.aman.ai_platform.user.entity.UserStatus;
    import com.aman.ai_platform.user.repository.UserRepository;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;

    @Service
    public class UserService {

        private final UserRepository userRepository;

        private final RoleRepository roleRepository;

        public UserService(UserRepository userRepository, RoleRepository roleRepository) {
            this.userRepository = userRepository;
            this.roleRepository = roleRepository;
        }

        @Transactional
        public UserResponseDTO createUser(CreateUserDTO createUserDTO){
            String email = createUserDTO.getEmail()
                    .trim()
                    .toLowerCase();

            if (userRepository.existsByEmailIgnoreCase(email)) {
                throw new RuntimeException("Email already Exists");
            }
            Role role = roleRepository.findByNameIgnoreCase("USER").orElseThrow(
                    () -> new RuntimeException("Role is not Found.")
            );
            User user = UserMapper.toEntity(createUserDTO);
            user.setEmail(email);
            user.setStatus(UserStatus.ACTIVE);
            User savedUser = userRepository.save(user);

            return UserMapper.userResponseDTO(savedUser);
        }

        public List<UserResponseDTO> getAllUsers() {
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(UserMapper::userResponseDTO)
                    .toList();
        }
    }
