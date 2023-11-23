package com.softwareengineering.planai.web.service;

import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.domain.mapping.UserFriend;
import com.softwareengineering.planai.web.dto.update.UserUpdateDto;
import com.softwareengineering.planai.web.repository.UserFriendRepository;
import com.softwareengineering.planai.web.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserFriendRepository userFriendRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserFriendRepository userFriendRepository) {
        this.userRepository = userRepository;
        this.userFriendRepository = userFriendRepository;
    }

    @Transactional
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Transactional
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public Optional<User> findByNameAndPhoneNumber(String name, String phoneNumber) {
        List<User> userList = userRepository.findByNameAndPhoneNumber(name, phoneNumber);
        if(userList.size() == 0) {
            throw new IllegalArgumentException("유저를 찾을 수 없음");
        }
        if(userList.size() > 1) {
            throw new IllegalStateException("중복된 유저가 DB에 등록되어 있음");
        }
        return Optional.ofNullable(userList.get(0));
    }

    @Transactional
    public Optional<User> findByNameAndEmail(String name, String email) {
        List<User> userList = userRepository.findByNameAndEmail(name, email);
        if(userList.size() == 0) {
            throw new IllegalArgumentException("유저를 찾을 수 없음");
        }
        if(userList.size() > 1) {
            throw new IllegalStateException("중복된 유저가 DB에 등록되어 있음");
        }
        return Optional.ofNullable(userList.get(0));
    }

    @Transactional
    public Optional<User> findByEmail(String email) {
        List<User> userList = userRepository.findByEmail(email);
        if(userList.size() == 0) {
            throw new IllegalArgumentException("유저를 찾을 수 없음");
        }
        if(userList.size() > 1) {
            throw new IllegalStateException("중복된 유저가 DB에 등록되어 있음");
        }
        return Optional.ofNullable(userList.get(0));
    }

    @Transactional
    public User updateUserInfo(Long id, UserUpdateDto dto) {
        User targetUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id에 대응되는 유저를 찾을 수 없음"));
        targetUser.updateUserInfo(dto);
        return targetUser;
    }

    @Transactional
    public User addFriend(Long userId, Long friendId) {
        User targetUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("id에 대응되는 유저를 찾을 수 없음"));
        User targetFriendUser = userRepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("id에 대응되는 유저를 찾을 수 없음"));

        UserFriend friendMapping = UserFriend.builder()
                .user(targetUser)
                .friend(targetFriendUser)
                .build();

        userFriendRepository.save(friendMapping);
        targetUser.addFriend(friendMapping);
        return targetUser;
    }

    @Transactional
    public User addFriend(Long userId, String friendEmail) {
        User targetUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("id에 대응되는 유저를 찾을 수 없음"));
        User targetFriendUser = findByEmail(friendEmail)
                .orElseThrow(() -> new IllegalArgumentException("id에 대응되는 유저를 찾을 수 없음"));

        UserFriend friendMapping = UserFriend.builder()
                .user(targetUser)
                .friend(targetFriendUser)
                .build();

        userFriendRepository.save(friendMapping);
        targetUser.addFriend(friendMapping);
        return targetUser;
    }

    @Transactional
    public User deleteFriend(Long userId, Long targetId) {
        User targetUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("id에 대응되는 유저를 찾을 수 없음"));
        User targetFriendUser = userRepository.findById(targetId)
                .orElseThrow(() -> new IllegalArgumentException("id에 대응되는 유저를 찾을 수 없음"));

        targetUser.deleteFriend(targetFriendUser);
        return targetUser;
    }

    @Transactional
    public Long deleteUser(Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
