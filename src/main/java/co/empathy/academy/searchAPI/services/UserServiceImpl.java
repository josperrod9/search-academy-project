package co.empathy.academy.searchAPI.services;

import co.empathy.academy.searchAPI.exceptions.DuplicatedUserException;
import co.empathy.academy.searchAPI.exceptions.UserNotFoundException;
import co.empathy.academy.searchAPI.models.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserServiceImpl implements UserService {

    private final ConcurrentHashMap<Long, User> users;

    public UserServiceImpl(ConcurrentHashMap<Long, User> users) {
        this.users = users;
    }

    public Collection<User> findAllUsers() {
        return users.values();
    }

    public User findUserById(long id) throws UserNotFoundException {
        if (users.containsKey(id)) {
            return users.get(id);
        }
        else{
            throw new UserNotFoundException(id);
        }
    }

    public User saveUser(User user) throws DuplicatedUserException {
        if (users.containsKey(user.getId())) {
            throw new DuplicatedUserException(user.getId());
        }
        users.put(user.getId(), user);
        return user;
    }

    public User updateUser(long id,User user) throws UserNotFoundException {
        if (users.containsKey(id)) {
            users.replace(id, user);
            return user;
        }
        else{
            throw new UserNotFoundException(user.getId());
        }
    }

    public void deleteUser(long id) throws UserNotFoundException {
        if (users.containsKey(id)) {
            users.remove(id);
        }
        else{
            throw new UserNotFoundException(id);
        }
    }

}
