package com.example.mindmeetapp.controller;

import com.example.mindmeetapp.model.UserModel;
import com.example.mindmeetapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserModel> getUsersList() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserModel registerUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable("id") Long id, @RequestBody UserModel user) {
        user.setId(id);
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public UserModel loginUser(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password);
    }

}

