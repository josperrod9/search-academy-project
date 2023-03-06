package co.empathy.academy.searchAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{
    public UserNotFoundException(long id) {
        super("User not found with id " + id);
    }
}
