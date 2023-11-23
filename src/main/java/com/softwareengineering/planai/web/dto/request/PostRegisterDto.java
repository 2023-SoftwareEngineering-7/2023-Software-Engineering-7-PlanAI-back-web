package com.softwareengineering.planai.web.dto.request;

import com.softwareengineering.planai.domain.entity.Comment;
import com.softwareengineering.planai.domain.entity.User;
import jakarta.persistence.Column;
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
public class PostRegisterDto {

    private String title;

    private String content;

    private Long author_id;
}
