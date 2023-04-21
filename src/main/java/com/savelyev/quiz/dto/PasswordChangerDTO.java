package com.savelyev.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class PasswordChangerDTO {

    private final int passwordLength = 8;

    private String oldPassword;
    private String password;
    private String confirmPassword;

    public boolean newPasswordMatch() {
        return password.equals(confirmPassword);
    }

    public boolean checkValidPasswordLength() {
        return password.length() >= passwordLength;
    }
}
