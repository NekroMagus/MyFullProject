package com.football.manager.validator;

import com.football.manager.domain.User;
import com.football.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link com.football.manager.domain.User} class,
 * implements {@link Validator} interface.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        //if username size not between 6 and 32
        if(user.getUsername().length() < 6 || user.getUsername().length() > 32){
            errors.rejectValue("username", "size.loginForm.username");
        }
        // If user exists
        if(userService.findUserByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "duplicate.userForm.username");
        }

        // if password less 6 characters
        if(user.getPassword().length() < 6) {
            errors.rejectValue("password", "size.loginForm.password");
        }

    }
}
