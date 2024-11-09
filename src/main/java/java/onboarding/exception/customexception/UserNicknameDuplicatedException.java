package java.onboarding.exception.customexception;

import java.onboarding.exception.customexception.global.GlobalDuplicatedException;
import java.onboarding.exception.errorcode.ErrorCode;

public class UserNicknameDuplicatedException extends GlobalDuplicatedException {

    public UserNicknameDuplicatedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
