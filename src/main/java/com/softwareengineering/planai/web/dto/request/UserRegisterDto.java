package com.softwareengineering.planai.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRegisterDto {
    private String name;
    private String email;
    private String phoneNumber;
}
