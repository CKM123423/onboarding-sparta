package com.onboarding.dto.response;

import com.onboarding.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponseDto {

    private final int code;
    private final String message;

    public static ErrorResponseDto of(ErrorCode errorCode) {
        return new ErrorResponseDto(
                errorCode.getHttpStatusCode(),
                errorCode.getDescription());
    }
}