package co.empathy.academy.searchAPI.controllers;

import co.empathy.academy.searchAPI.exceptions.DuplicatedUserException;
import co.empathy.academy.searchAPI.exceptions.UserNotFoundException;
import co.empathy.academy.searchAPI.models.User;
import co.empathy.academy.searchAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService usersService) {
        this.userService = usersService;
    }

    @GetMapping
    public Collection<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping
    public User saveUser(@Validated @RequestBody User user) throws DuplicatedUserException {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public  Map<String, Boolean> deleteUser(@PathVariable(value = "id") long id) throws UserNotFoundException {
        userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") long id, @Validated @RequestBody User user) throws UserNotFoundException {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }
}
