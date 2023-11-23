package com.softwareengineering.planai.web.conroller;

import com.softwareengineering.planai.domain.entity.Post;
import com.softwareengineering.planai.domain.entity.Task;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.web.dto.request.PostRegisterDto;
import com.softwareengineering.planai.web.dto.request.TaskRegisterDto;
import com.softwareengineering.planai.web.dto.response.TaskResponseDto;
import com.softwareengineering.planai.web.dto.update.PostUpdateDto;
import com.softwareengineering.planai.web.dto.update.TaskUpdateDto;
import com.softwareengineering.planai.web.service.PostService;
import com.softwareengineering.planai.web.service.TaskService;
import com.softwareengineering.planai.web.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private PostService postService;
    private UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/post")
    public List<Post> getAllPosts() {
        return postService.findByAll();
    }

    @GetMapping("/post/{id}")
    public Post getAllPosts(@PathVariable("id") Long id) {
        return postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 POST id"));
    }

    @PostMapping("/post")
    public Post registerPost(PostRegisterDto dto) {
        Post newPost = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(userService.findById(dto.getAuthor_id())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID")))
                .build();

        return postService.addPost(newPost);
    }

    @PutMapping("/post/{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody PostUpdateDto dto) {
        return postService.updatePost(id, dto);
    }

    @DeleteMapping("/post/{id}")
    public Long deletePost(@PathVariable("id") Long id) {
        return postService.deletePost(id);
    }
}
