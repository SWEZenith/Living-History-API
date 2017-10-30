package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.jwt;


import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.JwtAuthenticationToken;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.config.JwtSettings;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.RawAccessJwtToken;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;


@Component
@SuppressWarnings("unchecked")
public class JwtAuthenticationProvider implements AuthenticationProvider {

    //region Private Members

    private final JwtSettings jwtSettings;

    //endregion

    //region Constructor.

    /**
     * Ctor.
     * @param jwtSettings - Settings.
     */
    @Autowired
    public JwtAuthenticationProvider(JwtSettings jwtSettings) {

        this.jwtSettings = jwtSettings;
    }
    //endregion

    //region Public Methods

    /**
     * Authenticates user.
     * @param authentication - Authentication.
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        String subject = jwsClaims.getBody().getSubject();

        UserContext context = UserContext.create(subject);

        return new JwtAuthenticationToken(context);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
    //endregion
}
