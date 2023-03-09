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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/users")
@EnableAsync
@Tag(name = "users", description = "The users API")
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
            parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "User Id", example = "1")},
            responses = { @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid User ID supplied", content = @Content),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
        public ResponseEntity<User> findUserById(@PathVariable(value = "id") long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "createUser", summary = "Create a new user", tags = { "users" },
            responses = { @ApiResponse(responseCode = "201", description = "User created", content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid User supplied", content = @Content),
                    @ApiResponse(responseCode = "409", description = "User already exists", content = @Content) })
    public User saveUser(@Validated @RequestBody User user) throws DuplicatedUserException {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(operationId = "deleteUser", summary = "Delete a user by ID", tags = { "users" },
            parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "User Id")},
            responses = { @ApiResponse(responseCode = "204", description = "User deleted", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid User ID supplied", content = @Content),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
    public void deleteUser(@PathVariable(value = "id") long id) throws UserNotFoundException {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @Operation(operationId = "updateUser", summary = "Update a user by ID", tags = { "users" },
            parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "User Id")},
            responses = { @ApiResponse(responseCode = "200", description = "User updated", content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid User ID supplied", content = @Content),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") long id, @Validated @RequestBody User user) throws UserNotFoundException {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @PostMapping("/upload")
    @Operation(operationId = "uploadUsers", summary = "Upload a list of new users", tags = { "users" },
            responses = { @ApiResponse(responseCode = "200", description = "Users saved", content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid users supplied", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Some user already exists", content = @Content) })
    public ResponseEntity<String> uploadUsers(@RequestParam("file") MultipartFile file){
        try {
            userService.saveAll(file);
            return ResponseEntity.ok("Users saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving users");
        }
    }


    @PostMapping("/upload-async")
    @Operation(operationId = "uploadUsers-async", summary = "Upload a list of new users", tags = { "users" },
            responses = { @ApiResponse(responseCode = "200", description = "Users saved", content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid users supplied", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Some user already exists", content = @Content) })
    public CompletableFuture<String> uploadUsersAsync(@RequestParam("file") MultipartFile file) throws IOException {
            return userService.saveAllAsync(file);
    }
}
