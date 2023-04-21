package com.savelyev.quiz.validators;

import com.savelyev.quiz.constants.ApplicationMessages;
import com.savelyev.quiz.dto.PasswordChangerDTO;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@AllArgsConstructor
public class PasswordsValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
      return   PasswordChangerDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordChangerDTO passwordChanger = (PasswordChangerDTO) target;
        if(!passwordChanger.getPassword().equals(passwordChanger.getConfirmPassword())) {
            errors.rejectValue("password","validation",ApplicationMessages.getPasswordsNotMatchMessage());
        }
        if(passwordChanger.getPassword().length()<8) {
            errors.rejectValue("password","validation",ApplicationMessages.getPasswordValidationMessage());
        }
    }
}
