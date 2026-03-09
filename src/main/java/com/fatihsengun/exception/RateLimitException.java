package com.fatihsengun.exception;

public class RateLimitException extends BaseException {
    public RateLimitException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
