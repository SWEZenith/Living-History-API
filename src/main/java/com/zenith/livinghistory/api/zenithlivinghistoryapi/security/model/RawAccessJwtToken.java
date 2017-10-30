package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model;


import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.model.exception.JwtExpiredTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Raw access token class.
 */
public class RawAccessJwtToken implements JwtToken {

    //region Private Members

    private static Logger logger = LoggerFactory.getLogger(RawAccessJwtToken.class);

    private String token;

    //endregion

    //region Constructor

    /**
     * Ctor.
     * @param token - Token.
     */
    public RawAccessJwtToken(String token) {

        this.token = token;
    }

    //endregion

    //region Public Methods

    /**
     * Parses and validates JWT Token signature.
     * @throws BadCredentialsException
     * @throws JwtExpiredTokenException
     */
    public Jws<Claims> parseClaims(String signingKey) {

        try {

            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);

        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {

            logger.error("Invalid JWT Token", ex);
            throw new BadCredentialsException("Invalid JWT token: ", ex);

        } catch (ExpiredJwtException expiredEx) {

            logger.info("JWT Token is expired", expiredEx);
            throw new JwtExpiredTokenException(this, "JWT Token expired", expiredEx);
        }
    }

    /**
     * Gets token.
     * @return - Token.
     */
    @Override
    public String getToken() {

        return token;
    }
    //endregion
}