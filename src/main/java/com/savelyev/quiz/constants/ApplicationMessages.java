package com.savelyev.quiz.constants;


import com.savelyev.quiz.services.TranslatorService;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMessages {
    private static TranslatorService translatorService;

    public ApplicationMessages(TranslatorService translatorService) {
        ApplicationMessages.translatorService = translatorService;
    }

    public static String getAuthenticationProblemMessage(){
        return translatorService.getTranslation("login_authentication_problem");
    }

    public static String getSuccessfulRegistrationMessage() {
        return translatorService.getTranslation("successful_registration");
    }

    public static String getPasswordsNotMatchMessage() {
        return translatorService.getTranslation("passwords_dont_match");
    }

    public static String getSuccessfulVerificationMessage() {
        return translatorService.getTranslation("successful_verification");
    }

    public static String accessDeniedMessage() {
        return translatorService.getTranslation("access_denied");
    }

    public static String getTokenHasExpiredMessage() {
        return translatorService.getTranslation("token_has_expired");
    }

    public static String getResetPasswordMessage() {
        return translatorService.getTranslation("reset_password");
    }

    public static String getAccountNotFoundMessage() {
        return translatorService.getTranslation("account_not_found");
    }
    public static String getPasswordChangedMessage() {
        return translatorService.getTranslation("password_changed");
    }

    public static String getPasswordValidationMessage() {return translatorService.getTranslation("password_validation");}

    public static String getUsernameValidationMessage() {return translatorService.getTranslation("username_validation");}

    public static String getEmailValidationMessage() {return translatorService.getTranslation("email_validation");}

    public static String getEmailOccupiedValidationMessage() {return translatorService.getTranslation("email_occupied_validation");}


    public static String getEmailVerificationEmailMessage () {return translatorService.getTranslation("email_verification_message");}

    public static String getEmailThemeMessage() {return translatorService.getTranslation("email_verification_theme");}

    public static String getResetPasswordThemeMessage() {return translatorService.getTranslation("reset_password_theme");}



}
