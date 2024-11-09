package java.onboarding.exception.customexception;

import java.onboarding.exception.customexception.global.GlobalNotFoundException;
import java.onboarding.exception.errorcode.ErrorCode;

public class UserNotFoundException extends GlobalNotFoundException {

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
