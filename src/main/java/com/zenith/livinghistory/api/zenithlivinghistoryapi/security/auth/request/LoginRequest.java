package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {

    //region Private Members

    private String username;

    private String password;

    //endregion

    //region Constructor

    /**
     * Ctor.
     * @param username
     * @param password
     */
    @JsonCreator
    public LoginRequest(@JsonProperty("username") String username, @JsonProperty("password") String password) {

        this.username = username;
        this.password = password;
    }
    //endregion

    //region Public Methods

    public String getUsername() {

        return username;
    }

    public String getPassword() {

        return password;
    }
    //endregion
}
