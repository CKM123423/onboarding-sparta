package java.onboarding.exception.customexception;

import java.onboarding.exception.customexception.global.GlobalInvalidException;
import java.onboarding.exception.errorcode.ErrorCode;

public class InvalidPasswordException extends GlobalInvalidException {

    public InvalidPasswordException(ErrorCode errorCode) {
        super(errorCode);
    }
}
