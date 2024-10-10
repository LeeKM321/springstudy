package com.study.springstudy.springmvc.chap04.dto.request;

import com.study.springstudy.springmvc.chap04.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter @Setter @ToString
@Builder
public class SignUpRequestDto {

    @NotBlank
    @Size(min = 4, max = 14)
    private String account;

    @NotBlank
    private String password;

    @NotBlank
    @Size(min = 2, max = 6)
    private String name;

    @NotBlank
    @Email // 이메일 형식인지를 검사.
    private String email;

    // dto를 엔터티로 변환하는 유틸메서드
    public Member toEntity(PasswordEncoder encoder) {
        return Member.builder()
                .account(account)
                // 날것의 비밀번호를 그대로 저장하지 않고, encoder를 이용해서 암호화 한 후 세팅.
                .password(encoder.encode(password))
                .email(email)
                .name(name)
                .build();
    }

}








