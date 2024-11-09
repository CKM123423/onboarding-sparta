package com.onboarding.service;

import com.onboarding.dto.request.LoginRequestDto;
import com.onboarding.dto.request.SignUpRequestDto;
import com.onboarding.dto.response.LoginResponseDto;
import com.onboarding.dto.response.SignUpResponseDto;
import com.onboarding.entity.User;
import com.onboarding.enums.UserRole;
import com.onboarding.exception.customexception.InvalidPasswordException;
import com.onboarding.exception.customexception.UserNicknameDuplicatedException;
import com.onboarding.exception.customexception.UserNotFoundException;
import com.onboarding.exception.errorcode.UserErrorCode;
import com.onboarding.repository.UserRepository;
import com.onboarding.security.JwtProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public SignUpResponseDto userSignUp(SignUpRequestDto requestDto) {

        if (userRepository.existsByUsername(requestDto.getUsername())){
            throw new UserNicknameDuplicatedException(UserErrorCode.USER_NICKNAME_ALREADY_EXISTS);
        }

        User user = new User(
                requestDto.getUsername(),
                passwordEncoder.encode(requestDto.getPassword()),
                requestDto.getNickname(),
                UserRole.USER
        );

        return new SignUpResponseDto(user);
    }

    @Transactional
    public LoginResponseDto userLogin(LoginRequestDto requestDto, HttpServletResponse response) {

        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException(UserErrorCode.USER_PASSWORD_MISMATCH);
        }

        String accessToken = jwtProvider.createAccessToken(user.getUsername(),
                user.getUserRole());
        String refreshToken = jwtProvider.createRefreshToken(user.getUsername());

        user.saveRefreshToken(refreshToken);

        response.addHeader(JwtProvider.ACCESS_HEADER, accessToken);
        response.addHeader(JwtProvider.REFRESH_HEADER, refreshToken);

        return new LoginResponseDto(accessToken);
    }
}
