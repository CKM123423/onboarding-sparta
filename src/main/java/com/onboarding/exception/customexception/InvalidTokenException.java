package com.onboarding.exception.customexception;

import com.onboarding.exception.customexception.global.GlobalTokenException;
import com.onboarding.exception.errorcode.ErrorCode;

public class InvalidTokenException extends GlobalTokenException {

    public InvalidTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
