package com.softwareengineering.planai.web.repository;

import com.softwareengineering.planai.domain.entity.Post;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.domain.mapping.UserFriend;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFriendRepository extends JpaRepository<UserFriend, Long> {
    public List<UserFriend> findByUser(User user);
}
