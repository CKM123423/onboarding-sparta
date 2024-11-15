package com.onboarding.exception.customexception.global;

import com.onboarding.exception.errorcode.ErrorCode;
import lombok.Getter;

@Getter
public abstract class GlobalNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public GlobalNotFoundException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }
}
