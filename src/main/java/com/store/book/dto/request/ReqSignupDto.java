package com.store.book.dto.request;

import com.store.book.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class ReqSignupDto {
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{5,}$", message = "아이디는 6자 이상 영문, 숫자만 입력이 가능하고 첫번째는 영문이어야 합니다.")
    private String username;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$", message = "비밀번호는 8자 이상 16자 이하고 최소 한개 이상의 영문, 숫자가 포함되어야 합니다.")
    private String password;
    @NotBlank
    private String checkPassword;
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .name(name)
                .phone(phone)
                .build();
    }
}
