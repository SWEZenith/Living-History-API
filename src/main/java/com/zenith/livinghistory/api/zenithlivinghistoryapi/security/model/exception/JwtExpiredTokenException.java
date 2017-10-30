package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.exception;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.JwtToken;
import org.springframework.security.core.AuthenticationException;

/**
 * Exception thrown when token expires.
 */
public class JwtExpiredTokenException extends AuthenticationException {

    //region Private Members

    private static final long serialVersionUID = -5959543783324224864L;

    private JwtToken token;

    //endregion

    //region Constructor

    /**
     * Ctor.
     * @param message - Message
     */
    public JwtExpiredTokenException(String message) {

        super(message);
    }

    /**
     * Ctor.
     * @param token
     * @param msg
     * @param t
     */
    public JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {

        super(msg, t);
        this.token = token;
    }

    //endregion

    //region Public Properties

    /**
     * Gets token.
     * @return - Token.
     */
    public String token() {

        return this.token.getToken();
    }

    //endregion
}
