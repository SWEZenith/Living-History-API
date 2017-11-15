package com.zenith.livinghistory.api.zenithlivinghistoryapi.services;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.enums.UserStatus;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.enums.UserType;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.UserModel;
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

    public UserModel findByUsername(String username) {
        return repository.findFirstByUsername(username);
    }

    public UserModel findByEmail(String email) {
        return repository.findFirstByEmail(email);
    }

    public UserModel createUser(SignUpRequest request) {
        UserModel user = new UserModel();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword1()));
        return repository.save(user);
    }
}
