package erik.soekov.svenska_fest_back.service;


import erik.soekov.svenska_fest_back.dto.CreateUserRequest;
import erik.soekov.svenska_fest_back.entity.Role;
import erik.soekov.svenska_fest_back.entity.User;
import erik.soekov.svenska_fest_back.repositories.RoleRepository;
import erik.soekov.svenska_fest_back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(1);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEstonianIdCode(request.getEstonianIdCode());

        Role standardRole = roleRepository.findByRole("ADMIN")
                .orElseThrow(() -> new RuntimeException("Admin role not found"));

        Set<Role> roles = new HashSet<>();
        roles.add(standardRole);
        user.setRoles(roles);

        return userRepository.save(user);
    }
}
