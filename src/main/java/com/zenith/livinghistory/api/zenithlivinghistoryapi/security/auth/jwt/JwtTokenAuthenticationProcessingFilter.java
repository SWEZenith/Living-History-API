package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.JwtAuthenticationToken;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.jwt.extractor.TokenExtractor;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.config.WebSecurityConfig;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.RawAccessJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * Validates required fields of authentication presented on header.
 */
public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    //region Private Members

    private final AuthenticationFailureHandler failureHandler;

    private final TokenExtractor tokenExtractor;

    //endregion

    //region Constructor

    /**
     * Ctor.
     * @param failureHandler
     * @param tokenExtractor
     * @param matcher
     */
    @Autowired
    public JwtTokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler,
                                                  TokenExtractor tokenExtractor,
                                                  RequestMatcher matcher) {

        super(matcher);
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
    }
    //endregion

    //region Public Methods

    /**
     * Attempts to authenticate.
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

        String tokenPayload = request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM);
        RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));

        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
    }

    /**
     * Triggered if authentication is successfull.
     * @param request - Request.
     * @param response - Response.
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

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    /**
     * Triggered if authentication is failed.
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