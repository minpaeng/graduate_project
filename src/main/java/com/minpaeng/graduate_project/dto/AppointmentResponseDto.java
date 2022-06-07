package com.minpaeng.graduate_project.dto;

import com.minpaeng.graduate_project.domain.Appointment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AppointmentResponseDto {
    private final Long id;
    private final Long groupId;
    private final LocalDateTime timestamp;
    private final String resultDate;

    public AppointmentResponseDto(Appointment appointment) {
        this.groupId = appointment.getGroupId();
        this.id = appointment.getId();
        this.timestamp = appointment.getTimestamp();
        this.resultDate = appointment.getResultDate();
    }
}
