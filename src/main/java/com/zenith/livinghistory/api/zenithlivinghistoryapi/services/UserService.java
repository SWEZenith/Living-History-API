package com.zenith.livinghistory.api.zenithlivinghistoryapi.services;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.enums.UserStatus;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.enums.UserType;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.UserRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.User;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.request.SignUpRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service for user.
 */
@Service
public class UserService {

    private UserRepository repository;
    private BCryptPasswordEncoder encoder;

    public UserService(UserRepository repository) {
        this.repository = repository;
        this.encoder = new BCryptPasswordEncoder();
    }

    public User findByUsername(String username) {
        return repository.findFirstByUsername(username);
    }

    public User findByEmail(String email) {
        return repository.findFirstByEmail(email);
    }

    public User createUser(SignUpRequest request) {
        User user = new User(request.getUsername(), encoder.encode(request.getPassword1()), request.getEmail(), UserStatus.ACTIVE, UserType.STANDARD);
        return repository.insert(user);
    }
}
