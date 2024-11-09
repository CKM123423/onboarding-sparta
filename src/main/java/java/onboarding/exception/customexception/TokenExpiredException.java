package java.onboarding.exception.customexception;

import java.onboarding.exception.customexception.global.GlobalTokenException;
import java.onboarding.exception.errorcode.ErrorCode;

public class TokenExpiredException extends GlobalTokenException {

    public TokenExpiredException(ErrorCode errorCode) {
        super(errorCode);
    }
}
