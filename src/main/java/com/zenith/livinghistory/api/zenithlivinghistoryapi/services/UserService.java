package com.zenith.livinghistory.api.zenithlivinghistoryapi.services;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository.UserRepository;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.User;
import org.springframework.stereotype.Service;

/**
 * Service for user.
 */
@Service
public class UserService implements UserRepository {

    /**
     * Gets user by user name.
     * @param username - User Name.
     * @return - User.
     */
    @Override
    public User findByUsername(String username) {

        return new User(
                "bulent",
                "$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G"
        );
    }
}
