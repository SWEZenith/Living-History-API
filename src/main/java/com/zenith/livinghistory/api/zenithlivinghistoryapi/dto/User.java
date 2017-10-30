package com.zenith.livinghistory.api.zenithlivinghistoryapi.dto;


public class User {

    //region Private Members

    private String username;

    private String password;

    //endregion

    //region Constructor

    /**
     * Ctor.
     */
    public User() { }

    /**
     * Ctor.
     * @param username
     * @param password
     */
    public User(String username, String password) {

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
