package co.empathy.academy.searchAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedUserException extends Exception{
    public DuplicatedUserException(long id) {
        super("User already exists with id " + id);
    }
}
