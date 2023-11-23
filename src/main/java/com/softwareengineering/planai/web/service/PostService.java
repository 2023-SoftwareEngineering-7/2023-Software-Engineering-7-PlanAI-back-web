package com.softwareengineering.planai.web.service;

import com.softwareengineering.planai.domain.entity.Post;
import com.softwareengineering.planai.web.dto.update.PostUpdateDto;
import com.softwareengineering.planai.web.repository.PostRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post addSchedule(Post task) {
        postRepository.save(task);
        return task;
    }

    @Transactional
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Transactional
    public Post updatePost(Long id, PostUpdateDto dto) {
        Post targetPost = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id에 대응되는 포스트를 찾을 수 없음"));
        targetPost.updatePost(dto);
        return targetPost;
    }

    @Transactional
    public Long deletePost(Long id) {
        postRepository.deleteById(id);
        return id;
    }
}
