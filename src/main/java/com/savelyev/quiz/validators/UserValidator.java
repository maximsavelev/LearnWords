package com.savelyev.quiz.validators;

import com.savelyev.quiz.constants.ApplicationMessages;
import com.savelyev.quiz.model.security.User;
import com.savelyev.quiz.services.security.UserService;
import lombok.AllArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
public class UserValidator implements Validator {

    private final String emailPattern ="^(.+)@(\\S+)$";
    private final UserService userService;
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target,Errors errors) {
        User user = (User) target;
        if(userService.isUserExistByUsername(user.getUsername())){
            errors.rejectValue("username","validation", ApplicationMessages.getUsernameValidationMessage());
        }

        if(userService.isUserExistByEmail(user.getEmail())) {
            errors.rejectValue("email","validation",ApplicationMessages.getEmailOccupiedValidationMessage());
        }

        if(!user.getEmail().matches(emailPattern)) {
            errors.rejectValue("email","validation",ApplicationMessages.getEmailValidationMessage());
        }

        if(!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("password","validation",ApplicationMessages.getPasswordsNotMatchMessage());
        }

        if(user.getPassword().length()<8) {
            errors.rejectValue("password","validation",ApplicationMessages.getPasswordValidationMessage());
        }

    }
}
