package com.assigment.miniproject.Service;

import com.assigment.miniproject.Dto.UserCredential;
import com.assigment.miniproject.Entity.User;
import com.assigment.miniproject.Repository.UserCredentialRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AuthService {
    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(credential.getName());
        user.setPassword(passwordEncoder.encode(credential.getPassword()));
        user.setEmail(credential.getEmail());
        repository.save(user);
        return "user added to the system";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
