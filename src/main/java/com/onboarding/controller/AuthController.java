package com.onboarding.controller;

import com.onboarding.dto.request.LoginRequestDto;
import com.onboarding.dto.request.SignUpRequestDto;
import com.onboarding.dto.response.LoginResponseDto;
import com.onboarding.dto.response.RestApiResponseDto;
import com.onboarding.dto.response.SignUpResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import com.onboarding.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // 일반 유저 회원가입
    @PostMapping("/signup")
    public ResponseEntity<RestApiResponseDto<SignUpResponseDto>> userSignUp(
            @Valid @RequestBody SignUpRequestDto requestDto
    ) {
        SignUpResponseDto response = authService.userSignUp(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(RestApiResponseDto.of(HttpStatus.OK.value(), "회원가입 완료", response));
    }

    // 로그인
    @PostMapping("/sign")
    public ResponseEntity<RestApiResponseDto<LoginResponseDto>> userSignIn(
            @Valid @RequestBody LoginRequestDto requestDto,
            HttpServletResponse response
    ) {
        LoginResponseDto responseDto = authService.userLogin(requestDto, response);

        return ResponseEntity.status(HttpStatus.OK)
                .body(RestApiResponseDto.of(HttpStatus.OK.value(), "로그인 완료", responseDto));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("성공");
    }
}
