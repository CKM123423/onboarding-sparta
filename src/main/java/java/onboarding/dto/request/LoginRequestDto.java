package java.onboarding.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    @Size(min = 4, max = 10, message = "아이디는 4자이상 10자 이하여야합니다.")
    @Pattern(regexp = "^[a-z0-9]+$",
            message = "아이디는 알파벳 소문자, 숫자만 사용가능합니다.")
    private String username;

    @NotBlank(message = "패스워드는 공백일 수 없습니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 8자이상 15자 이하여야합니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?]).{8,15}$", message = "비밀번호는 대소문자 영문(a~z, A~Z) + 숫자(0~9) + 특수문자를 최소 1글자씩 포함해야 합니다.")
    private String password;
}
