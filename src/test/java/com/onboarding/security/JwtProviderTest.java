package com.onboarding.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.onboarding.enums.UserRole;
import com.onboarding.exception.customexception.InvalidTokenException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JwtProviderTest {

    private JwtProvider jwtProvider;

    private String secretKey = "6rK966+87Jio67O065Sp6rO87KCcb25ib2FyZGluZ+qyveuvvOyYqOuztOuUqeqzvOygnG9uYm9hcmRpbmc=";

    @BeforeAll
    void setUp() throws Exception {
        jwtProvider = new JwtProvider();
        setPrivateField(jwtProvider, "secretKey", secretKey);
        jwtProvider.init();
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    @DisplayName("Created Access Token Test")
    public void generateAccessTokenTest() {
        String username = "testUser1";
        UserRole userRole = UserRole.USER;

        String accessToken = jwtProvider.createAccessToken(username, userRole);

        assertThat(accessToken).isNotNull();
        assertThat(accessToken).startsWith("Bearer ");
    }

    @Test
    @DisplayName("Created Refresh Token Test")
    public void generateRefreshTokenTest() {
        String username = "testUser1";

        String refreshToken = jwtProvider.createRefreshToken(username);

        assertThat(refreshToken).isNotNull();
        assertThat(refreshToken).startsWith("Bearer ");
    }

    @Test
    @DisplayName("HttpServletRequest Get Header Test")
    public void getJwtFromHeaderTest() {
        String testToken = "Bearer sample.token.here";
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader(JwtProvider.ACCESS_HEADER)).thenReturn(testToken);

        String token = jwtProvider.getJwtFromHeader(request, JwtProvider.ACCESS_HEADER);

        assertThat(token).isNotNull();
        assertThat("Bearer " + token).isEqualTo(testToken);
    }

    @Test
    @DisplayName("Empty HttpServletRequest Get Header Test")
    public void getJwtFromEmptyHeaderTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();

        String extractedToken = jwtProvider.getJwtFromHeader(request, JwtProvider.ACCESS_HEADER);

        assertThat(extractedToken).isNull();
    }

    @Test
    @DisplayName("Validate Invalid Signature Token Test")
    public void validateInvalidSignatureTokenTest() {
        String invalidToken = "invalid.token.string";

        Exception exception = assertThrows(InvalidTokenException.class, () -> {
            jwtProvider.validateToken(invalidToken);
        });

        assertThat(exception.getMessage()).contains("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
    }

    @Test
    @DisplayName("Validate Empty Token Test")
    public void validateTokenEmptyTest() {
        String emptyToken = "";

        Throwable thrown = catchThrowable(() -> jwtProvider.validateToken(emptyToken));

        assertThat(thrown).isInstanceOf(InvalidTokenException.class);
    }
}
