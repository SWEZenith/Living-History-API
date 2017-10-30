package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * Exception thrown for not supported method types.
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {


    //region Private Members

    private static final long serialVersionUID = 3705043083010304496L;

    //endregion

    //region Constructor

    /**
     * Ctor.
     * @param message - Message.
     */
    public AuthMethodNotSupportedException(String message) {

        super(message);
    }
    //endregion

}

