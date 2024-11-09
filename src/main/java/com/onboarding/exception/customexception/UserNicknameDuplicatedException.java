package com.onboarding.exception.customexception;

import com.onboarding.exception.errorcode.ErrorCode;
import com.onboarding.exception.customexception.global.GlobalDuplicatedException;

public class UserNicknameDuplicatedException extends GlobalDuplicatedException {

    public UserNicknameDuplicatedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
