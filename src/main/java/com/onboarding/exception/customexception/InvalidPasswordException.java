package com.onboarding.exception.customexception;

import com.onboarding.exception.errorcode.ErrorCode;
import com.onboarding.exception.customexception.global.GlobalInvalidException;

public class InvalidPasswordException extends GlobalInvalidException {

    public InvalidPasswordException(ErrorCode errorCode) {
        super(errorCode);
    }
}
