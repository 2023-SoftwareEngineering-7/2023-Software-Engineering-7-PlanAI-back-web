package com.softwareengineering.planai.web.conroller;

import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.domain.mapping.UserFriend;
import com.softwareengineering.planai.web.dto.request.UserRegisterDto;
import com.softwareengineering.planai.web.dto.response.FriendListDto;
import com.softwareengineering.planai.web.dto.response.UserResponseDto;
import com.softwareengineering.planai.web.dto.update.UserUpdateDto;
import com.softwareengineering.planai.web.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public UserResponseDto getUser(@PathVariable(name = "id") Long id) {
        return new UserResponseDto(
                userService.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID"))
        );
    }

    @PostMapping("/user/register")
    public UserResponseDto registerUser(@RequestBody UserRegisterDto dto) {
        User newUser = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        return new UserResponseDto(userService.addUser(newUser));
    }

    @PutMapping("/user/{id}")
    public UserResponseDto updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDto dto) {
        return new UserResponseDto(userService.updateUserInfo(id, dto));
    }

    @DeleteMapping("/user/{id}")
    public Long deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/user/{userId}/friend")
    public FriendListDto getFriend(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID"));

        return new FriendListDto(user);
    }

    @PostMapping("/user/{userId}/friend/{friendId}")
    public UserResponseDto addFriend(@PathVariable("userId") Long userId, @PathVariable("friendId") Long friendId) {
        return new UserResponseDto(userService.addFriend(userId, friendId));
    }

    @DeleteMapping("/user/{userId}/friend/{friendId}")
    public UserResponseDto deleteFriend(@PathVariable("userId") Long userId, @PathVariable("friendId") Long friendId) {
        return new UserResponseDto(userService.deleteFriend(userId, friendId));
    }
}
