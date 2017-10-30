package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.jwt.extractor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

/**
 * Utilizer to extract token from given text.
 */
@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {

    //region Public Properties

    public static String HEADER_PREFIX = "Bearer ";

    //endregion

    //region Pubic Methods

    /**
     * Extracts token from header.
     * @param header - Header
     * @return
     */
    @Override
    public String extract(String header) {

        if (StringUtils.isBlank(header))
            throw new AuthenticationServiceException("Authorization header cannot be blank!");

        if (header.length() < HEADER_PREFIX.length())
            throw new AuthenticationServiceException("Invalid authorization header size.");

        return header.substring(HEADER_PREFIX.length(), header.length());
    }

    //endregion
}
