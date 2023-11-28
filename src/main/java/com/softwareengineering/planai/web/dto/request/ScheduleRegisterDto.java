package com.softwareengineering.planai.web.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softwareengineering.planai.domain.entity.Schedule;
import com.softwareengineering.planai.domain.mapping.ScheduleTag;
import com.softwareengineering.planai.web.dto.response.UserResponseDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@AllArgsConstructor
public class ScheduleRegisterDto {

    private String title;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime startDate;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime endDate;

    private String description;

    private Long ownerId;

    private List<String> tagList = new ArrayList<>();
}
