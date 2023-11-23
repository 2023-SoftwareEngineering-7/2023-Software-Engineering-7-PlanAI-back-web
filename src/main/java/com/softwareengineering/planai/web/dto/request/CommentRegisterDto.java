package com.softwareengineering.planai.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRegisterDto {

    private String content;

    private Long author_id;
}
