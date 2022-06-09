package com.minpaeng.graduate_project.dto;

import com.minpaeng.graduate_project.domain.Personal;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PersonalResponseDto {
    private final Long id;
    private final Long groupId;
    private final String userId;
    private final LocalDateTime timestamp;
    private final String result;

    public PersonalResponseDto(Personal personal) {
        this.id = personal.getId();
        this.groupId = personal.getGroupId();
        this.userId = personal.getUserId();
        this.timestamp = personal.getTimestamp();
        this.result = personal.getResult();
    }
}
