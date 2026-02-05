package erik.soekov.svenska_fest_back.config;

import erik.soekov.svenska_fest_back.entity.Role;
import erik.soekov.svenska_fest_back.entity.User;
import erik.soekov.svenska_fest_back.repositories.RoleRepository;
import erik.soekov.svenska_fest_back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String username = System.getenv("ADMIN_USERNAME");
        String password = System.getenv("ADMIN_PASSWORD");
        String email = System.getenv("ADMIN_EMAIL");
        String firstName = System.getenv("ADMIN_FIRST_NAME");
        String lastName = System.getenv("ADMIN_LAST_NAME");
        String idCode = System.getenv("ADMIN_ID_CODE");

        if (username == null || password == null) {
            return;
        }

        if (userRepository.existsByUsername(username)) {
            System.out.println("Admin user '" + username + "' already exists, skipping creation.");
            return;
        }

        User admin = new User();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setEmail(email != null ? email : username + "@admin.local");
        admin.setActive(1);
        admin.setFirstName(firstName != null ? firstName : "Admin");
        admin.setLastName(lastName != null ? lastName : "User");
        admin.setEstonianIdCode(idCode != null ? idCode : "00000000000");

        Role adminRole = roleRepository.findByRole("ADMIN")
                .orElseThrow(() -> new RuntimeException("ADMIN role not found"));

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        admin.setRoles(roles);

        userRepository.save(admin);
        System.out.println("Admin user '" + username + "' created successfully.");
    }
}
