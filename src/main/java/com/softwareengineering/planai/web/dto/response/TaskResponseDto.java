package com.softwareengineering.planai.web.dto.response;

import com.softwareengineering.planai.domain.entity.Schedule;
import com.softwareengineering.planai.domain.entity.Task;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.domain.enums.Priority;
import com.softwareengineering.planai.domain.mapping.ScheduleTag;
import com.softwareengineering.planai.domain.mapping.TaskTag;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
public class TaskResponseDto {

    private Long id;

    private String title;

    private LocalDateTime deadline;

    private String description;

    private UserResponseDto owner;

    private Priority priority;

    private List<String> tagList = new ArrayList<>();

    public TaskResponseDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.owner = new UserResponseDto(task.getOwner());
        for (TaskTag st : task.getTagList()) {
            tagList.add(st.getTag().getTagName());
        }
    }
}
