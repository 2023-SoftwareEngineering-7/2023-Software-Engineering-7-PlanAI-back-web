package com.softwareengineering.planai.web.conroller;

import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.domain.mapping.UserFriend;
import com.softwareengineering.planai.web.dto.request.UserRegisterDto;
import com.softwareengineering.planai.web.dto.response.FriendListDto;
import com.softwareengineering.planai.web.dto.response.UserResponseDto;
import com.softwareengineering.planai.web.dto.update.UserUpdateDto;
import com.softwareengineering.planai.web.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;

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

//    @GetMapping("/")
//    public String hello() {
//        return "Hello";
//    }

    @GetMapping("/user/{id}")
    @Operation(summary="특정 유저 정보 가져오기", description="테스트 중입니다.")
    public UserResponseDto getUser(@PathVariable(name = "id") Long id) {
        return new UserResponseDto(
                userService.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID"))
        );
    }
    @PostMapping("/user/register")
    @Operation(summary="유저 등록하기", description="테스트 중입니다.")
    public UserResponseDto registerUser(@RequestBody UserRegisterDto dto) {
        User newUser = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        return new UserResponseDto(
                userService.findByNameAndEmail(dto.getName(), dto.getEmail())
                    .orElse(userService.addUser(newUser))
        );
    }

    @PutMapping("/user/{id}")
    @Operation(summary="유저 정보 수정하기", description="테스트 중입니다.")
    public UserResponseDto updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDto dto) {
        return new UserResponseDto(userService.updateUserInfo(id, dto));
    }

    @DeleteMapping("/user/{id}")
    @Operation(summary="특정 유저 삭제하기", description="테스트 중입니다.")
    public Long deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/user/{userId}/friend")
    @Operation(summary="특정 유저의 친구목록 가져오기", description="테스트 중입니다.")
    public FriendListDto getFriend(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID"));

        return new FriendListDto(user);
    }

    @PostMapping("/user/{userId}/friend/{friendId}")
    @Operation(summary="특정 유저에게 다른 유저 친구로 추가하기22222", description="테스트 중입니다.")
    public UserResponseDto addFriend(@PathVariable("userId") Long userId, @PathVariable("friendId") Long friendId) {
        return new UserResponseDto(userService.addFriend(userId, friendId));
    }

    @DeleteMapping("/user/{userId}/friend/{friendId}")
    @Operation(summary="특정 유저의 특정 친구 삭제하기", description="테스트 중입니다.")
    public UserResponseDto deleteFriend(@PathVariable("userId") Long userId, @PathVariable("friendId") Long friendId) {
        return new UserResponseDto(userService.deleteFriend(userId, friendId));
    }
}
