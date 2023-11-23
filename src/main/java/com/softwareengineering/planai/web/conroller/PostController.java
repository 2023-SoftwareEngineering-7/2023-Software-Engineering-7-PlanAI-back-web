package com.softwareengineering.planai.web.conroller;

import com.softwareengineering.planai.domain.entity.Post;
import com.softwareengineering.planai.domain.entity.Task;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.web.dto.request.PostRegisterDto;
import com.softwareengineering.planai.web.dto.request.TaskRegisterDto;
import com.softwareengineering.planai.web.dto.response.PostResponseDto;
import com.softwareengineering.planai.web.dto.response.TaskResponseDto;
import com.softwareengineering.planai.web.dto.update.PostUpdateDto;
import com.softwareengineering.planai.web.dto.update.TaskUpdateDto;
import com.softwareengineering.planai.web.service.PostService;
import com.softwareengineering.planai.web.service.TaskService;
import com.softwareengineering.planai.web.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary="모든 포스트 가져오기", description="테스트 중입니다.")
    public List<PostResponseDto> getAllPosts() {
        return postService.findByAll().stream()
                .map(element -> new PostResponseDto(element))
                .collect(Collectors.toList());
    }

    @GetMapping("/post/{id}")
    @Operation(summary="특정 ID의 포스트 가져오기", description="테스트 중입니다.")
    public PostResponseDto getPostById(@PathVariable("id") Long id) {
        return new PostResponseDto(postService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 POST id")));
    }

    @PostMapping("/post")
    @Operation(summary="포스트 등록하기", description="테스트 중입니다.")
    public PostResponseDto registerPost(@RequestBody PostRegisterDto dto) {
        Post newPost = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(userService.findById(dto.getAuthor_id())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID")))
                .build();

        return new PostResponseDto(postService.addPost(newPost));
    }

    @PutMapping("/post/{id}")
    @Operation(summary="특정 ID의 포스트 내용 수정하기", description="테스트 중입니다.")
    public PostResponseDto updatePost(@PathVariable("id") Long id, @RequestBody PostUpdateDto dto) {
        return new PostResponseDto(postService.updatePost(id, dto));
    }

    @DeleteMapping("/post/{id}")
    @Operation(summary="특정 ID의 포스트 삭제", description="테스트 중입니다.")
    public Long deletePost(@PathVariable("id") Long id) {
        return postService.deletePost(id);
    }
}
