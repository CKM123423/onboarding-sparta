package com.onboarding.exception.customexception;

import com.onboarding.exception.customexception.global.GlobalTokenException;
import com.onboarding.exception.errorcode.ErrorCode;

public class TokenExpiredException extends GlobalTokenException {

    public TokenExpiredException(ErrorCode errorCode) {
        super(errorCode);
    }
}
