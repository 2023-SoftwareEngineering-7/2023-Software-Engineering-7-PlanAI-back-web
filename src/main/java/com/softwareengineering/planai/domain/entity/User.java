package com.softwareengineering.planai.domain.entity;

import com.softwareengineering.planai.domain.common.BaseEntity;
import com.softwareengineering.planai.domain.mapping.UserFriend;
import com.softwareengineering.planai.web.dto.update.UserUpdateDto;
import jakarta.persistence.*;
import java.util.stream.Collectors;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = true, length = 256)
    private String profileImagePath;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserFriend> friendList = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    public void updateUserInfo(UserUpdateDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
    }

    public void addFriend(UserFriend mapping) {
        friendList.add(mapping);
    }

    public UserFriend findFriend(User target) {
        for (UserFriend uf : friendList) {
            if (uf.getFriend() == target) {
                return uf;
            }
        }
        return null;
    }

    public void deleteFriend(User target) {
        friendList = friendList.stream()
                .filter((element) -> { return element.getFriend().getId() != target.getId(); })
                .collect(Collectors.toList());
    }
}
