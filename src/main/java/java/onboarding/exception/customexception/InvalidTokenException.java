package java.onboarding.exception.customexception;

import java.onboarding.exception.customexception.global.GlobalTokenException;
import java.onboarding.exception.errorcode.ErrorCode;

public class InvalidTokenException extends GlobalTokenException {

    public InvalidTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
