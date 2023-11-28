package com.softwareengineering.planai.web.dto.request;

import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.domain.enums.Priority;
import com.softwareengineering.planai.domain.mapping.TaskTag;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskRegisterDto {

    private String title;

    private String description;

    private LocalDateTime deadline;

    private Priority priority;

    private Long ownerId;

    private List<String> tagList;
}
