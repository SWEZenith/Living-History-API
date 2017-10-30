package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.ErrorResponse;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.enums.ErrorCodes;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.exception.AuthMethodNotSupportedException;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.exception.JwtExpiredTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class AwareAuthenticationFailureHandler implements AuthenticationFailureHandler {

    //region Private Members

    private final ObjectMapper mapper;

    //endregion

    //region Constructor

    /**
     * Ctor.
     * @param mapper
     */
    @Autowired
    public AwareAuthenticationFailureHandler(ObjectMapper mapper) {

        this.mapper = mapper;
    }
    //endregion

    //region Public Methods

    /**
     * Triggered when auth. is failed.
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        if (e instanceof BadCredentialsException) {

            mapper.writeValue(
                    response.getWriter(),
                    ErrorResponse.of(
                            "Invalid username or password",
                            ErrorCodes.AUTHENTICATION,
                            HttpStatus.UNAUTHORIZED
                    )
            );

        } else if (e instanceof JwtExpiredTokenException) {

            mapper.writeValue(
                    response.getWriter(),
                    ErrorResponse.of(
                            "Token has expired",
                            ErrorCodes.JWT_TOKEN_EXPIRED,
                            HttpStatus.UNAUTHORIZED
                    )
            );

        } else if (e instanceof AuthMethodNotSupportedException) {

            mapper.writeValue(
                    response.getWriter(),
                    ErrorResponse.of(
                            e.getMessage(),
                            ErrorCodes.AUTHENTICATION,
                            HttpStatus.UNAUTHORIZED
                    )
            );
        }

        mapper.writeValue(
                response.getWriter(),
                ErrorResponse.of(
                        "Authentication failed",
                        ErrorCodes.AUTHENTICATION,
                        HttpStatus.UNAUTHORIZED
                )
        );
    }
    //endregion
}