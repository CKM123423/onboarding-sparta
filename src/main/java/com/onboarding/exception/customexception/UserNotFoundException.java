package com.onboarding.exception.customexception;

import com.onboarding.exception.customexception.global.GlobalNotFoundException;
import com.onboarding.exception.errorcode.ErrorCode;

public class UserNotFoundException extends GlobalNotFoundException {

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
