package com.softwareengineering.planai.web.repository;

import com.softwareengineering.planai.domain.entity.Post;
import com.softwareengineering.planai.domain.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findByTitle(String title);
    public List<Post> findByAuthor(User author);
}
