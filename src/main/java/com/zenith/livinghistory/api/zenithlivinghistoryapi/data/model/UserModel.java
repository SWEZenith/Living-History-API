package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model;


import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.enums.UserStatus;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.enums.UserType;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class UserModel {
    private String username;
    private String password;
    private String email;
    private UserStatus status;
    private UserType type;

    public UserModel() {
    }

    public UserModel(String username, String password, String email, UserStatus status, UserType type) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
