package net.skideo.controller;

import net.skideo.exception.ClubAlreadyExistsException;
import net.skideo.exception.PostNotFoundException;
import net.skideo.exception.ScoutNotFoundException;
import net.skideo.exception.WrongLoginOrPasswordException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ClubAlreadyExistsException.class)
    public String clubAlreadyExists() {
        return "Club already exists";
    }

    @ExceptionHandler(value = ClassNotFoundException.class)
    public String clubNotFound() {
        return "Club not found";
    }

    @ExceptionHandler(value = PostNotFoundException.class)
    public String postNotFound() {
        return "Post not found";
    }

    @ExceptionHandler(value = WrongLoginOrPasswordException.class)
    public String wrongLoginOrPassword() {
        return "Wrong login or password";
    }

    @ExceptionHandler(value = ScoutNotFoundException.class)
    public String scoutNotFound() {
        return "Scout not found";
    }

}
