package co.empathy.academy.searchAPI.services;

import co.empathy.academy.searchAPI.exceptions.DuplicatedUserException;
import co.empathy.academy.searchAPI.exceptions.UserNotFoundException;
import co.empathy.academy.searchAPI.models.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService {
    Collection<User> findAllUsers();

    User findUserById(long id) throws UserNotFoundException;

    User saveUser(User user) throws DuplicatedUserException;

    void deleteUser(long id) throws UserNotFoundException;

    User updateUser(long id, User user) throws UserNotFoundException;
}
