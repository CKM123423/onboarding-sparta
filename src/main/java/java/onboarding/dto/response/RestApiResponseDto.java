package java.onboarding.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiResponseDto<T> {

    private final int code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    public static <T> RestApiResponseDto<T> of(int code, String message) {
        return new RestApiResponseDto<>(code, message, null);
    }

    public static <T> RestApiResponseDto<T> of(int code, String message, T data) {
        return new RestApiResponseDto<>(code, message, data);
    }
}