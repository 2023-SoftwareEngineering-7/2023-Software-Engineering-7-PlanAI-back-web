package com.softwareengineering.planai.web.conroller;

import com.softwareengineering.planai.domain.entity.Comment;
import com.softwareengineering.planai.domain.entity.Post;
import com.softwareengineering.planai.web.dto.request.CommentRegisterDto;
import com.softwareengineering.planai.web.dto.request.PostRegisterDto;
import com.softwareengineering.planai.web.dto.update.PostUpdateDto;
import com.softwareengineering.planai.web.service.CommentService;
import com.softwareengineering.planai.web.service.PostService;
import com.softwareengineering.planai.web.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Comment getAllComment(@PathVariable("postId") Long postId,
                                 @PathVariable("commentId") Long commentId) {

        return commentService.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID"));
    }

    @GetMapping("/post/{postId}/comment")
    public List<Comment> getAllCommentOfPost(@PathVariable("postId") Long postId) {
        Post post = postService.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 POST id"));

        return commentService.findByPost(post);
    }

    @PostMapping("/post/{postId}/comment")
    public Comment registerComment(@PathVariable("postId") Long postId, CommentRegisterDto dto) {
        Comment comment = Comment.builder()
                .content(dto.getContent())
                .author(userService.findById(dto.getAuthor_id())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 ID")))
                .post(postService.findById(postId)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 POST ID")))
                .build();

        return commentService.addComment(comment);
    }

    @PutMapping("/post/{postId}/comment/{commentId}")
    public Comment updatePost(@PathVariable("postId") Long postId,
                           @PathVariable("commentId") Long commentId,
                           @RequestBody String newContent) {
        return commentService.updateComment(commentId, newContent);
    }

    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public Long deletePost(@PathVariable("postId") Long postId,
                           @PathVariable("commentId") Long commentId) {
        return commentService.deleteComment(commentId);
    }
}
