package com.softwareengineering.planai.web.conroller;

import com.softwareengineering.planai.domain.entity.Comment;
import com.softwareengineering.planai.domain.entity.Post;
import com.softwareengineering.planai.web.dto.request.CommentRegisterDto;
import com.softwareengineering.planai.web.dto.request.PostRegisterDto;
import com.softwareengineering.planai.web.dto.response.CommentResponseDto;
import com.softwareengineering.planai.web.dto.update.PostUpdateDto;
import com.softwareengineering.planai.web.service.CommentService;
import com.softwareengineering.planai.web.service.PostService;
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
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class CommentController {

    private UserService userService;
    private PostService postService;
    private CommentService commentService;

    @Autowired
    public CommentController(PostService postService, CommentService commentService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/post/{postId}/comment/{commentId}")
    @Operation(summary="특정 포스트의 특정 ID를 갖는 댓글 가져오기", description="테스트 중입니다.")
    public CommentResponseDto getAllComment(@PathVariable("postId") Long postId,
                                            @PathVariable("commentId") Long commentId) {

        return new CommentResponseDto(commentService.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID")));
    }

    @GetMapping("/post/{postId}/comment")
    @Operation(summary="특정 포스트의 모든 댓글 가져오기", description="테스트 중입니다.")
    public List<CommentResponseDto> getAllCommentOfPost(@PathVariable("postId") Long postId) {
        Post post = postService.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 POST id"));

        return commentService.findByPost(post).stream()
                .map(element -> new CommentResponseDto(element))
                .collect(Collectors.toList());
    }

    @PostMapping("/post/{postId}/comment")
    @Operation(summary="특정 포스트에 댓글 등록", description="테스트 중입니다.")
    public CommentResponseDto registerComment(@PathVariable("postId") Long postId, CommentRegisterDto dto) {
        Comment comment = Comment.builder()
                .content(dto.getContent())
                .author(userService.findById(dto.getAuthor_id())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID")))
                .post(postService.findById(postId)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 POST ID")))
                .build();

        return new CommentResponseDto(commentService.addComment(comment));
    }

    @PutMapping("/post/{postId}/comment/{commentId}")
    @Operation(summary="특정 포스트의 특정 댓글 수정", description="테스트 중입니다.")
    public CommentResponseDto updateComment(@PathVariable("postId") Long postId,
                           @PathVariable("commentId") Long commentId,
                           @RequestBody String newContent) {
        return new CommentResponseDto(commentService.updateComment(commentId, newContent));
    }

    @DeleteMapping("/post/{postId}/comment/{commentId}")
    @Operation(summary="특정 포스트의 특정 댓글 삭제", description="테스트 중입니다.")
    public Long deleteComment(@PathVariable("postId") Long postId,
                           @PathVariable("commentId") Long commentId) {
        return commentService.deleteComment(commentId);
    }
}
