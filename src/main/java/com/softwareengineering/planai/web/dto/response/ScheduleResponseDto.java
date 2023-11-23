package com.softwareengineering.planai.web.dto.response;

import com.softwareengineering.planai.domain.entity.Schedule;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.domain.mapping.ScheduleTag;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String description;

    private UserResponseDto owner;

    private List<String> tagList = new ArrayList<>();

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.startDate = schedule.getStartDate();
        this.endDate = schedule.getEndDate();
        this.description = schedule.getDescription();
        this.owner = new UserResponseDto(schedule.getOwner());
        for (ScheduleTag st : schedule.getTagList()) {
            tagList.add(st.getTag().getTagName());
        }
    }
}
