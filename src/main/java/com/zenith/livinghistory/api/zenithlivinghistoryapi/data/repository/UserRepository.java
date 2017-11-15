package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.UserModel;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findFirstByUsername(String username);
    UserModel findFirstByEmail(String email);
}
