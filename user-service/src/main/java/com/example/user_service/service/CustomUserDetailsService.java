package com.example.user_service.service;


import com.example.user_service.model.Role;
import com.example.user_service.model.User;
import com.example.user_service.repository.RoleRepository;
import com.example.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String roleWithPrefix = user.getRole().getName();
        if (!roleWithPrefix.startsWith("ROLE_")) {
            roleWithPrefix = "ROLE_" + roleWithPrefix;
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(roleWithPrefix))
        );
    }


    @Bean
    CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        return args -> {
            // Provera da li rola postoji, ako ne postoji — kreiraj je
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role(null, "ADMIN", null)));

            // Provera da li korisnik postoji, ako ne postoji — kreiraj ga
            if (userRepository.findByUsername("admin").isEmpty()) {
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(encoder.encode("1234")); // obavezno enkoduj lozinku!
                adminUser.setEmail("admin@example.com");
                adminUser.setRole(adminRole);
                userRepository.save(adminUser);
                System.out.println("Admin korisnik je uspešno dodat u bazu.");
            } else {
                System.out.println("Admin korisnik već postoji u bazi.");
            }
        };
    }
}
