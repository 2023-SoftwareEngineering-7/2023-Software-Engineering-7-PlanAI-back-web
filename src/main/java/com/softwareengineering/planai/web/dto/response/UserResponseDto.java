package com.softwareengineering.planai.web.dto.response;

import com.softwareengineering.planai.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private String name;
    private String email;
    private String phoneNumber;

    public UserResponseDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }
}
