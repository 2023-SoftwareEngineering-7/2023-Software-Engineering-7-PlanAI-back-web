package com.softwareengineering.planai.web.dto.response;

import com.softwareengineering.planai.domain.entity.Post;
import com.softwareengineering.planai.domain.entity.Schedule;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.domain.mapping.ScheduleTag;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponseDto {

    private Long id;

    private String title;

    private String content;

    private UserResponseDto author;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = new UserResponseDto(post.getAuthor());
    }
}
