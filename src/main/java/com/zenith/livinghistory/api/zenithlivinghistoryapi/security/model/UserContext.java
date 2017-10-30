package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model;

import org.apache.commons.lang3.StringUtils;

/**
 * User context.
 */
public class UserContext {

    //region Private Members

    private final String username;

    //endregion

    //region Constructor

    /**
     * Ctor.
     * @param username - User Name.
     */
    private UserContext(String username) {

        this.username = username;
    }

    /**
     * Ctor.
     * @param username - User Name.
     * @return
     */
    public static UserContext create(String username) {

        if (StringUtils.isBlank(username))
            throw new IllegalArgumentException("Username is blank: " + username);

        return new UserContext(username);
    }

    //endregion

    //region Public Properties

    /**
     * Gets user name.
     * @return
     */
    public String getUsername() {

        return username;
    }

    //endregion

}
