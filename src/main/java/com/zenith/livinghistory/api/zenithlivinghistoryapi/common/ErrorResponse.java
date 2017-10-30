package com.zenith.livinghistory.api.zenithlivinghistoryapi.common;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.common.enums.ErrorCodes;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorResponse {


    //region Private Members

    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;

    // Error code
    private final ErrorCodes errorCode;

    private final Date timestamp;
    //endregion


    //region Constructor

    /**
     * Ctor.
     * @param message
     * @param errorCode
     * @param status
     */
    protected ErrorResponse(final String message, final ErrorCodes errorCode, HttpStatus status) {

        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
        this.timestamp = new java.util.Date();
    }
    //endregion

    //region Public Methods

    /**
     * Creates new error response.
     * @param message - Message.
     * @param errorCode - Code.
     * @param status - Status.
     * @return - ErrorReponse.
     */
    public static ErrorResponse of(final String message, final ErrorCodes errorCode, HttpStatus status) {

        return new ErrorResponse(message, errorCode, status);
    }

    public Integer getStatus() {

        return status.value();
    }

    public String getMessage() {

        return message;
    }

    public ErrorCodes getErrorCode() {

        return errorCode;
    }

    public Date getTimestamp() {

        return timestamp;
    }
    //endregion
}
