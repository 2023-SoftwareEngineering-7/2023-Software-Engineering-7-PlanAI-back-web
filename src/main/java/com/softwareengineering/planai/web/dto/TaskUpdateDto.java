package com.softwareengineering.planai.web.dto;

import com.softwareengineering.planai.domain.enums.Priority;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateDto {
    private String title;
    private String description;
    private LocalDateTime deadline;
    private Priority priority;
}
