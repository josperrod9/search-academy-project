package co.empathy.academy.searchAPI.controllers;

import co.empathy.academy.searchAPI.exceptions.DuplicatedUserException;
import co.empathy.academy.searchAPI.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URI;
import java.net.URISyntaxException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { UserNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleUserNotFoundException(UserNotFoundException ex) throws URISyntaxException {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.NOT_FOUND,ex.getMessage());
        errorResponse.getBody().setType(new URI("https://httpstatuses.com/404"));
        return errorResponse;
    }

    @ExceptionHandler(value = { DuplicatedUserException.class })
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleDuplicatedUserException(DuplicatedUserException ex) {
        return ErrorResponse.create(ex, HttpStatus.CONFLICT,ex.getMessage());
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMethodArgumentNotValidException(Exception ex) {
        String message = ex.getMessage();
        return ErrorResponse.create(ex, HttpStatus.BAD_REQUEST,message);
    }
}

