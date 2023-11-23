package com.softwareengineering.planai.web.conroller;

import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable(name = "id") Long id) {
        return userService.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID"));
    }


}
