package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth;

import java.util.ArrayList;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.RawAccessJwtToken;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.UserContext;
import org.springframework.security.authentication.AbstractAuthenticationToken;


/**
 * Authentication token class.
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    //region Private Members

    private static final long serialVersionUID = 2877954820905567501L;

    private RawAccessJwtToken rawAccessToken;

    private UserContext userContext;

    //endregion

    //region Constructors

    /**
     * Ctor.
     * @param unsafeToken
     */
    public JwtAuthenticationToken(RawAccessJwtToken unsafeToken) {

        super(null);
        this.rawAccessToken = unsafeToken;
        this.setAuthenticated(false);
    }

    /**
     * Ctor.
     * @param userContext
     */
    public JwtAuthenticationToken(UserContext userContext) {

        super(new ArrayList<>());
        this.eraseCredentials();
        this.userContext = userContext;
        super.setAuthenticated(true);
    }
    //endregion

    //region Public Methods

    @Override
    public void setAuthenticated(boolean authenticated) {

        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {

        return rawAccessToken;
    }

    @Override
    public Object getPrincipal() {

        return this.userContext;
    }

    @Override
    public void eraseCredentials() {

        super.eraseCredentials();
        this.rawAccessToken = null;
    }
    //endregion
}
