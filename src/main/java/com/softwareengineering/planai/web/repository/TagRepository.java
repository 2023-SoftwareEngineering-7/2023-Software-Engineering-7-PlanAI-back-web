package com.softwareengineering.planai.web.repository;

import com.softwareengineering.planai.domain.entity.Post;
import com.softwareengineering.planai.domain.entity.Tag;
import com.softwareengineering.planai.domain.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    public List<Tag> findByTagName(String title);
    public List<Tag> findByOwner(User user);
}
