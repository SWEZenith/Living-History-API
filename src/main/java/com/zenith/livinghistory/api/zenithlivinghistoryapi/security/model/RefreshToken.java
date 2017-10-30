package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.exception.JwtExpiredTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Class of refresh token.
 */
@SuppressWarnings("unchecked")
public class RefreshToken implements JwtToken {

    //region Private Members

    private Jws<Claims> claims;

    //endregion

    //region Constructor

    /**
     * Ctor.
     * @param claims
     */
    private RefreshToken(Jws<Claims> claims) {

        this.claims = claims;
    }

    //endregion

    //region Public Methods

    /**
     * Creates and validates refresh token.
     * @param token - Token.
     * @param signingKey - Key to sign token.
     *
     * @throws BadCredentialsException
     * @throws JwtExpiredTokenException
     * @return
     */
    public static Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) {

        Jws<Claims> claims = token.parseClaims(signingKey);

        return Optional.of(new RefreshToken(claims));
    }

    @Override
    public String getToken() {

        return null;
    }

    public Jws<Claims> getClaims() {

        return claims;
    }

    public String getJti() {

        return claims.getBody().getId();
    }

    public String getSubject() {

        return claims.getBody().getSubject();
    }
    //endregion
}
