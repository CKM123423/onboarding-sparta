package com.onboarding.dto.response;

import com.onboarding.entity.User;
import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private String username;
    private String nickname;
    private String authorityName;

    public SignUpResponseDto(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.authorityName = user.getUserRole().getAuthorityName();
    }
}
