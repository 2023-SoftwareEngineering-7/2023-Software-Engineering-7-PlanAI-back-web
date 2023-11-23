package com.softwareengineering.planai.web.dto.request;

import com.softwareengineering.planai.domain.entity.Schedule;
import com.softwareengineering.planai.domain.mapping.ScheduleTag;
import com.softwareengineering.planai.web.dto.response.UserResponseDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRegisterDto {

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String description;

    private Long ownerId;

    private List<String> tagList = new ArrayList<>();
}
