package com.softwareengineering.planai.domain.mapping;

import com.softwareengineering.planai.domain.common.BaseEntity;
import com.softwareengineering.planai.domain.entity.Schedule;
import com.softwareengineering.planai.domain.entity.Tag;
import com.softwareengineering.planai.domain.entity.Task;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class TaskTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
