package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.request;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.exception.AuthMethodNotSupportedException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    //region Private Members

    private static Logger logger = LoggerFactory.getLogger(LoginProcessingFilter.class);

    private final AuthenticationSuccessHandler successHandler;

    private final AuthenticationFailureHandler failureHandler;

    private final ObjectMapper objectMapper;

    //endregion

    //region Constructor

    /**
     * Ctor.
     * @param defaultProcessUrl
     * @param successHandler
     * @param failureHandler
     * @param mapper
     */
    public LoginProcessingFilter(String defaultProcessUrl,
                                 AuthenticationSuccessHandler successHandler,
                                 AuthenticationFailureHandler failureHandler,
                                 ObjectMapper mapper) {

        super(defaultProcessUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.objectMapper = mapper;
    }
    //endregion

    //region Public Methods

    /**
     * Attemps to authenticate.
     * @param request - Request.
     * @param response - Response.
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        if(!HttpMethod.POST.name().equals(request.getMethod())) {

            if(logger.isDebugEnabled())
                logger.debug("Authentication method not supported. Request method: " + request.getMethod());

            throw new AuthMethodNotSupportedException("Authentication method not supported");
        }

        LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);

        if (StringUtils.isBlank(loginRequest.getUsername()) || StringUtils.isBlank(loginRequest.getPassword()))
            throw new AuthenticationServiceException("Username or Password not provided");


        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        return this.getAuthenticationManager().authenticate(token);
    }

    /**
     * Triggered if authentication is successfull.
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    /**
     * Triggered if authentication fails.
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
    //endregion
}
