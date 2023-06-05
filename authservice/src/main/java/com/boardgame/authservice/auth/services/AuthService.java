package com.boardgame.authservice.auth.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boardgame.authservice.auth.config.JwtService;
import com.boardgame.authservice.auth.models.Manager;
import com.boardgame.authservice.auth.models.Name;
import com.boardgame.authservice.auth.models.Role;
import com.boardgame.authservice.auth.repositories.ManagerRepository;
import com.boardgame.authservice.auth.requests.AuthenticationRequest;
import com.boardgame.authservice.auth.requests.RegisterRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService { 
    @Autowired
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Object register(RegisterRequest request) {

        Manager manager = Manager.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(new Name(request.getName().getFirstName(), request.getName().getLastName()))
                .gender(request.getGender())
                .phoneNumber(request.getPhoneNumber())
                .birthday(request.getBirthday())
                .address(request.getAddress())
                .role(Role.MANAGER)
                .createAt(Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(null)
                .build();

        managerRepository.save(manager);

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("manager", manager);
        response.put("message", "Registered successfully.");
        response.put("timestamp", Timestamp.valueOf(LocalDateTime.now()));

        return response;
    }

    public Object authenticate(AuthenticationRequest request) {

        Manager manager = managerRepository.findByEmail(request.getEmail()).orElseThrow();

        var authToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticationManager.authenticate(authToken);

        String jwtToken = jwtService.generateToken(manager);

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("manager", manager);
        response.put("accessToken", jwtToken);
        response.put("message", "Authenticated successfully.");
        response.put("timestamp", Timestamp.valueOf(LocalDateTime.now()));

        return response;
    }

    public Object getManagerData(String authorization) {
        String username = jwtService.extractUsername(authorization.substring("Bearer ".length()));
        Manager manager = managerRepository.findByEmail(username).orElseThrow();

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("manager", manager);
        response.put("message", "Get data successfully.");
        response.put("timestamp", Timestamp.valueOf(LocalDateTime.now()));

        return response;
    }

    public Object getAll() {
        List<Manager> managers = managerRepository.findAll();

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("managers", managers);
        response.put("message", "Get all managers successfully.");
        response.put("timestamp", Timestamp.valueOf(LocalDateTime.now()));

        return response;
    }
}
