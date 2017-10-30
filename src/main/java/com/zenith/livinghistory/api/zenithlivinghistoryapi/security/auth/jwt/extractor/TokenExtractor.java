package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.jwt.extractor;

public interface TokenExtractor {

    public String extract(String payload);
}
