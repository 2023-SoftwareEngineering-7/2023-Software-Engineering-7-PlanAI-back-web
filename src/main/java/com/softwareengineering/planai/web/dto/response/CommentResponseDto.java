package com.softwareengineering.planai.web.dto.response;

import com.softwareengineering.planai.domain.entity.Comment;
import com.softwareengineering.planai.domain.entity.Post;
import com.softwareengineering.planai.domain.entity.Schedule;
import com.softwareengineering.planai.domain.mapping.ScheduleTag;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private Long id;

    private String content;

    private UserResponseDto author;

    private PostResponseDto post;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.post = new PostResponseDto(comment.getPost());
        this.author = new UserResponseDto(comment.getAuthor());
    }
}
