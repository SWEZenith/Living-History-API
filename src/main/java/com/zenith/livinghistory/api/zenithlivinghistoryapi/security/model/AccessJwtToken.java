package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;

/**
 * JWT Token.
 */
public final class AccessJwtToken implements JwtToken {

    //region Private Members

    private final String rawToken;

    @JsonIgnore
    private Claims claims;

    //endregion

    //region Constructor

    /**
     * Ctor.
     * @param token - Token.
     * @param claims - Claims.
     */
    protected AccessJwtToken(final String token, Claims claims) {

        this.rawToken = token;
        this.claims = claims;
    }
    //endregion

    //region Public Properties

    /**
     * Gets token.
     * @return - Token.
     */
    public String getToken() {

        return this.rawToken;
    }

    /**
     * Gets claims.
     * @return - Claims.
     */
    public Claims getClaims() {

        return claims;
    }

    //endregion
}