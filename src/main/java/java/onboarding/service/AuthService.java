package java.onboarding.service;

import jakarta.servlet.http.HttpServletResponse;
import java.onboarding.dto.request.LoginRequestDto;
import java.onboarding.dto.request.SignUpRequestDto;
import java.onboarding.dto.response.LoginResponseDto;
import java.onboarding.dto.response.SignUpResponseDto;
import java.onboarding.entity.User;
import java.onboarding.enums.UserRole;
import java.onboarding.exception.customexception.InvalidPasswordException;
import java.onboarding.exception.customexception.UserNicknameDuplicatedException;
import java.onboarding.exception.customexception.UserNotFoundException;
import java.onboarding.exception.errorcode.UserErrorCode;
import java.onboarding.repository.UserRepository;
import java.onboarding.security.JwtProvider;
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
