package com.onboarding.exception.exceptionhandler;

import com.onboarding.exception.customexception.global.GlobalNotFoundException;
import com.onboarding.exception.errorcode.ErrorCode;
import com.onboarding.dto.response.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j(topic = "GlobalExceptionHandler")
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalNotFoundException.class)
    protected ResponseEntity<ErrorResponseDto> globalNotFoundException(
            GlobalNotFoundException e) {
        log.error("GlobalNotFoundException 발생");
        return sendErrorResponse(e.getErrorCode());
    }

    private ResponseEntity<ErrorResponseDto> sendErrorResponse(ErrorCode e) {
        return ResponseEntity.status(e.getHttpStatusCode())
                .body(ErrorResponseDto.of(e));
    }
}
