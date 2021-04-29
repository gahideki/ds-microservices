package com.hr.oauth.controller;

import com.hr.oauth.model.User;
import com.hr.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<User> getUser(@RequestParam String email) {
        try {
            User user = userService.getUserBy(email);
            return ResponseEntity.ok(user);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

}
