package com.onboarding.exception.customexception.global;

import com.onboarding.exception.errorcode.ErrorCode;
import lombok.Getter;

@Getter
public abstract class GlobalTokenException extends RuntimeException {

    private final ErrorCode errorCode;

    public GlobalTokenException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }
}
