package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository;


import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.User;

public interface UserRepository  {

    public User findByUsername(String username);
}
