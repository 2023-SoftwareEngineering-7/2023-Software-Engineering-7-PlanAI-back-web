package com.softwareengineering.planai.domain.entity;

import com.softwareengineering.planai.domain.common.BaseEntity;
import com.softwareengineering.planai.domain.enums.Priority;
import com.softwareengineering.planai.domain.mapping.TaskTag;
import com.softwareengineering.planai.web.dto.TaskUpdateDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Task extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "task")
    private List<TaskTag> tagList = new ArrayList<>();

    public void updateTask(TaskUpdateDto dto) {
        title = dto.getTitle();
        description = dto.getDescription();
        deadline = dto.getDeadline();
        priority = dto.getPriority();
    }
}
