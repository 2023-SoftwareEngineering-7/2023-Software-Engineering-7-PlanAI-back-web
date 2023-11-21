package com.softwareengineering.planai.domain.entity;

import com.softwareengineering.planai.domain.common.BaseEntity;
import com.softwareengineering.planai.web.dto.PostUpdateDto;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;


    public void updatePost(PostUpdateDto dto) {
        title = dto.getTitle();
        content = dto.getContent();
    }
}
