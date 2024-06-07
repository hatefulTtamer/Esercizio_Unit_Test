package co.develhope.esercizio_unit_test.controller;

import co.develhope.esercizio_unit_test.entity.User;
import co.develhope.esercizio_unit_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public User createUser (@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> findAllUsers () {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById (@PathVariable long id) {
        return userService.findUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser (@PathVariable long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById (@PathVariable long id) {
        userService.deleteUserById(id);
    }

}
