package co.empathy.academy.searchAPI.services;

import co.empathy.academy.searchAPI.exceptions.DuplicatedUserException;
import co.empathy.academy.searchAPI.exceptions.UserNotFoundException;
import co.empathy.academy.searchAPI.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserServiceImpl implements UserService {

    private final ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();


    public Collection<User> findAllUsers() {
        return users.values();
    }

    public User findUserById(long id) throws UserNotFoundException {
        if (this.users.containsKey(id)) {
            return this.users.get(id);
        }
        else{
            throw new UserNotFoundException(id);
        }
    }

    public User saveUser(User user) throws DuplicatedUserException {
        if (this.users.containsKey(user.getId())) {
            throw new DuplicatedUserException(user.getId());
        }
        this.users.put(user.getId(), user);
        return user;
    }

    public User updateUser(long id,User user) throws UserNotFoundException {
        if (this.users.containsKey(id)) {
            this.users.replace(id, user);
            return user;
        }
        else{
            throw new UserNotFoundException(user.getId());
        }
    }

    @Override
    public void saveAll(MultipartFile file) throws IOException {
        List<User> usersList = new ObjectMapper().readValue(file.getBytes(), new TypeReference<List<User>>() {});
        usersList.forEach(user -> this.users.put(user.getId(), user));
    }


    @Async
    public CompletableFuture<String> saveAllAsync(MultipartFile file) throws IOException {
        List<User> usersList = new ObjectMapper().readValue(file.getBytes(), new TypeReference<List<User>>() {});
        usersList.forEach(user -> this.users.put(user.getId(), user));
        return CompletableFuture.completedFuture("Usuarios creados con éxito");
    }

    public void deleteUser(long id) throws UserNotFoundException {
        if (this.users.containsKey(id)) {
            this.users.remove(id);
        }
        else{
            throw new UserNotFoundException(id);
        }
    }

}
