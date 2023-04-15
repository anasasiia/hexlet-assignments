package exercise.controller;

import exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import exercise.service.UserService;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    // BEGIN
    @GetMapping(path = "/{id}")
    public Mono<User> getUserById(@PathVariable long id) {
        return userService.findUserById(id);
    }

    @PostMapping(path = "")
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PatchMapping(path = "/{id}")
    public Mono<User> updateUser(@PathVariable long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }


    @DeleteMapping(path = "/{id}")
    public Mono<Void> deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }
    // END
}
