package co.empathy.academy.searchAPI.controllers;

import co.empathy.academy.searchAPI.exceptions.DuplicatedUserException;
import co.empathy.academy.searchAPI.exceptions.UserNotFoundException;
import co.empathy.academy.searchAPI.models.User;
import co.empathy.academy.searchAPI.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService usersService) {
        this.userService = usersService;
    }

    @GetMapping
    @Operation(operationId = "findAllUsers", summary = "Find all users", tags = { "users" },
            responses = { @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = User.class))) })
    public Collection<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(operationId = "findUserById", summary = "Find user by ID", tags = { "users" },
            parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "User Id"),
                    @Parameter(in = ParameterIn.QUERY, name = "name", description = "User name"),
                    @Parameter(in = ParameterIn.QUERY, name = "email", description = "User email")
            },
            responses = { @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid User ID supplied"),
                    @ApiResponse(responseCode = "404", description = "User not found") })
        public ResponseEntity<User> findUserById(@PathVariable(value = "id") long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "createUser", summary = "Create a new user", tags = { "users" },
            responses = { @ApiResponse(responseCode = "201", description = "User created", content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid User supplied"),
                    @ApiResponse(responseCode = "409", description = "User already exists") })
    public User saveUser(@Validated @RequestBody User user) throws DuplicatedUserException {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(operationId = "deleteUser", summary = "Delete a user by ID", tags = { "users" },
            responses = { @ApiResponse(responseCode = "204", description = "User deleted"),
                    @ApiResponse(responseCode = "400", description = "Invalid User ID supplied"),
                    @ApiResponse(responseCode = "404", description = "User not found") })
    public void deleteUser(@PathVariable(value = "id") long id) throws UserNotFoundException {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @Operation(operationId = "updateUser", summary = "Update a user by ID", tags = { "users" },
            responses = { @ApiResponse(responseCode = "200", description = "User updated", content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid User ID supplied"),
                    @ApiResponse(responseCode = "404", description = "User not found") })
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") long id, @Validated @RequestBody User user) throws UserNotFoundException {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }
}
