package com.savelyev.quiz.controllers;


import com.savelyev.quiz.constants.ApplicationMessages;
import com.savelyev.quiz.dto.PasswordChangerDTO;
import com.savelyev.quiz.exception.IncorrectTokenTypeException;
import com.savelyev.quiz.exception.TokenHasExpiredException;
import com.savelyev.quiz.model.security.SecureToken;
import com.savelyev.quiz.model.security.User;
import com.savelyev.quiz.services.security.SecureTokenService;
import com.savelyev.quiz.services.security.UserService;
import com.savelyev.quiz.validators.PasswordsValidator;
import com.savelyev.quiz.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class SecurityController {

    private final UserService userService;
    private final UserValidator validator;
    private final PasswordsValidator passwordsValidator;

    private final BCryptPasswordEncoder passwordEncoder;

    private final SecureTokenService tokenService;

    @Value("${default_success_url}")
    private String defaultSuccessUrl;

    @Value("${login_url}")
    private String loginUrl;


    @Value("${profile-page_url}")
    private String profile;

    @GetMapping("${registration_url}")
    public String registration(Model model) {
        if (userService.isAuthenticated()) {
            return "redirect:/" + defaultSuccessUrl;
        }
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("${registration_url}")
    public String reg(@ModelAttribute("userForm") @Valid User user, BindingResult bindingResult,
                      RedirectAttributes attributes) {
        validator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            attributes.addFlashAttribute("infoMessage",
                    ApplicationMessages.getSuccessfulRegistrationMessage());
            userService.createUser(user);
            userService.sendRegistrationConfirmationEmail(user);
            return "redirect:" + loginUrl;
        }
    }

    @GetMapping("${login_failure_url}")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                model.addAttribute("errorMessage", ApplicationMessages.accessDeniedMessage());
            }
        }
        return "login";
    }

    @RequestMapping(value = {"${login_url}", "/"})
    public String login() {
        if (userService.isAuthenticated()) {
            return "redirect:" + defaultSuccessUrl;
        }
        return "login";
    }

    @RequestMapping("${verify_account_token_url}/{token}")
    public String verifyUser(@PathVariable String token, RedirectAttributes attributes) {
        try {
            SecureToken secureToken = tokenService.findByToken(token);
            userService.verifyUser(secureToken);
            tokenService.deleteToken(secureToken);
            attributes.addFlashAttribute("infoMessage",
                    ApplicationMessages.getSuccessfulVerificationMessage());
        } catch (TokenHasExpiredException | IncorrectTokenTypeException e) {
            attributes.addFlashAttribute("errorMessage", ApplicationMessages.getTokenHasExpiredMessage());
        }
        return "redirect:" + loginUrl;
    }

    @GetMapping("${reset_password_url}")
    public String resetPassword() {
        return "forgot-password";
    }

    @PostMapping("${reset_password_url}")
    public String resetPassword(@RequestParam String email, RedirectAttributes attributes, Model model) {
        try {
            User user = userService.findUserByEmail(email);
            userService.sendResetPasswordConfirmationEmail(user);
            attributes.addFlashAttribute("infoMessage", ApplicationMessages.getResetPasswordMessage());
            return "redirect:" + loginUrl;
        } catch (UsernameNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "forgot-password";
        }
    }

    @GetMapping("${reset_password_token_url}/{token}")
    public String resetUserPassword(Model model, @PathVariable String token) {
        model.addAttribute("token", token);
        model.addAttribute("passwordChanger", new PasswordChangerDTO());
        return "password";
    }

    @PostMapping("${reset_password_token_url}/{token}")
    public String resetUsersPassword(@PathVariable String token,
                                     @ModelAttribute("passwordChanger") PasswordChangerDTO passwordChangerDTO,
                                     BindingResult bindingResult, RedirectAttributes attributes) {
        passwordsValidator.validate(passwordChangerDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "password";
        } else {
            try {
                SecureToken secureToken = tokenService.findByToken(token);
                userService.changePassword(secureToken, passwordChangerDTO.getPassword());
                attributes.addFlashAttribute("infoMessage", ApplicationMessages.getPasswordChangedMessage());
            } catch (TokenHasExpiredException | IncorrectTokenTypeException e) {
                attributes.addFlashAttribute("errorMessage", ApplicationMessages.getTokenHasExpiredMessage());
            }
            return "redirect:" + loginUrl;
        }
    }

    @GetMapping("${profile-page_url}")
    public String profile(Model model, Principal principal) {
        User user = userService.findUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("passwordChanger", new PasswordChangerDTO());
        return "profile";
    }

    @PostMapping("profile")
    public String changePasswordFromProfile(@ModelAttribute("passwordChanger") PasswordChangerDTO passwordChangerDTO,
                                            Principal principal, RedirectAttributes attributes) {
        User user = userService.findUserByPrincipal(principal);
        System.out.println(passwordChangerDTO);
        System.out.println(passwordEncoder.matches(passwordChangerDTO.getOldPassword(), user.getPassword()));
        if (!passwordEncoder.matches(passwordChangerDTO.getOldPassword(), user.getPassword())) {
            attributes.addFlashAttribute("errorMessage",
                    "Пароль не совпадает со старым паролем!");
            return "redirect:" + profile;
        }
        if (!passwordChangerDTO.newPasswordMatch()) {
            attributes.addFlashAttribute("errorMessage", "Пароли должны совпадать!");
            return "redirect:" + profile;
        }
        if (!passwordChangerDTO.checkValidPasswordLength()) {
            attributes.addFlashAttribute("errorMessage",
                    "Пароли должен состоять из 8 и более символов!");
            return "redirect:" + profile;
        }
        user.setPassword(passwordChangerDTO.getPassword());
        userService.updateUserPassword(user);
        return "redirect:" + profile;
    }



}
