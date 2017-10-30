package com.zenith.livinghistory.api.zenithlivinghistoryapi.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCodes {

    GLOBAL(2),
    AUTHENTICATION(10),
    JWT_TOKEN_EXPIRED(11);

    private int errorCode;

    private ErrorCodes(int errorCode) {

        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {

        return errorCode;
    }
}