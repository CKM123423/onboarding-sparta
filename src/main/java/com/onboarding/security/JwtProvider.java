package com.onboarding.security;

import com.onboarding.enums.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import com.onboarding.exception.customexception.InvalidTokenException;
import com.onboarding.exception.customexception.TokenExpiredException;
import com.onboarding.exception.errorcode.TokenErrorCode;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j(topic = "JwtProvider")
public class JwtProvider {

    public static final String ACCESS_HEADER = "Authorization";
    public static final String REFRESH_HEADER = "X-Refresh-Token";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_KEY = "auth";
    // 억세스 토큰 만료시간
    private final long ACCESS_TOKEN_EXPIRATION_TIME = 30 * 60 * 1000L; // 30분
    // 리프레쉬 토큰 만료시간
    private final long REFRESH_TOKEN_EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000L; // 7일

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String createAccessToken(String username, UserRole role) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(username)
                        .claim(AUTHORIZATION_KEY, role)
                        .setExpiration(new Date(date.getTime() + ACCESS_TOKEN_EXPIRATION_TIME))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    public String createRefreshToken(String username) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(username)
                        .setExpiration(new Date(date.getTime() + REFRESH_TOKEN_EXPIRATION_TIME))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    public String getJwtFromHeader(HttpServletRequest request, String headerName) {
        String token = request.getHeader(headerName);
        if (StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX)) {
            return token.substring(7);
        }
        return null;
    }

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
            throw new InvalidTokenException(TokenErrorCode.INVALID_SIGNATURE);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
            throw new TokenExpiredException(TokenErrorCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
            throw new InvalidTokenException(TokenErrorCode.UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
            throw new InvalidTokenException(TokenErrorCode.ILLEGAL_ARGUMENT);
        }
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}