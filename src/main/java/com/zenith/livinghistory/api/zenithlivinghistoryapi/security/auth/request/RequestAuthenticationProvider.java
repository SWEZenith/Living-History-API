package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.request;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.User;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.UserContext;


@Component
public class RequestAuthenticationProvider implements AuthenticationProvider {

    //region Private Members

    private final BCryptPasswordEncoder encoder;

    @Autowired
    private final UserService userService;

    //endregion

    //region Constructor

    /**
     * Ctor.
     * @param userService
     */

    public RequestAuthenticationProvider(final UserService userService) {

        this.userService = userService;
        this.encoder = new BCryptPasswordEncoder();
    }
    //endregion

    //region Public Methods

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = userService.findByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("User not found: " + username);

        if (!encoder.matches(password, user.getPassword()))
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");


        UserContext userContext = UserContext.create(user.getUsername());

        return new UsernamePasswordAuthenticationToken(userContext, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
    //endregion
}
