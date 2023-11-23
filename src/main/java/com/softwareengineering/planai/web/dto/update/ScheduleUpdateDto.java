package com.softwareengineering.planai.web.dto.update;

import jakarta.persistence.Column;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleUpdateDto {
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
