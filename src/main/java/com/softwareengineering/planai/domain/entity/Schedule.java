package com.softwareengineering.planai.domain.entity;

import com.softwareengineering.planai.domain.common.BaseEntity;
import com.softwareengineering.planai.domain.mapping.ScheduleTag;
import com.softwareengineering.planai.web.dto.ScheduleUpdateDto;
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
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "schedule")
    private List<ScheduleTag> tagList = new ArrayList<>();

    public void updateScheduleInfo(ScheduleUpdateDto dto) {
        title = dto.getTitle();
        description = dto.getDescription();
        startDate = dto.getStartDate();
        endDate = dto.getEndDate();
    }
}
