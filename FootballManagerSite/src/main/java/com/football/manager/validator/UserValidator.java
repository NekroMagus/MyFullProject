package com.football.manager.validator;

import com.football.manager.model.User;
import com.football.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for {@link com.football.manager.model.User} class,
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
        if(user.getEmail().length() < 6 || user.getEmail().length() > 32){
            errors.rejectValue("username", "size.loginForm.username");
        }
        // If user exists
        if(userService.findUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("username", "duplicate.userForm.username");
        }

        // if password less 6 characters
        if(user.getPassword().length() < 6) {
            errors.rejectValue("password", "size.loginForm.password");
        }

    }
}
