package com.softwareengineering.planai.web.dto.response;

import com.softwareengineering.planai.domain.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class FriendListDto {

    private List<UserResponseDto> friendList = new ArrayList<>();

    public FriendListDto(User user) {
        user.getFriendList() .stream()
                .forEach((element) -> {
                    friendList.add(new UserResponseDto(element.getFriend()));
                });
    }
}
