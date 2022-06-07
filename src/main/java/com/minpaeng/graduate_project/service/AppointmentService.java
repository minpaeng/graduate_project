package com.minpaeng.graduate_project.service;

import com.minpaeng.graduate_project.domain.AppointmentRepository;
import com.minpaeng.graduate_project.dto.AppointmentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public  List<AppointmentResponseDto> findAppointments(Long groupId, String date, String time) {
        // date와 time을 합쳐서 datetime 포멧으로 바꾸기
        LocalDateTime dateTime = makeDateTime(date, time);
        System.out.println(dateTime);

        //repository 조회하기
        return appointmentRepository
                .findAppointmentsByGroupIdAndTimestamp(groupId, dateTime)
                .stream()
                .map(AppointmentResponseDto::new)
                .collect(Collectors.toList());
    }

    private LocalDateTime makeDateTime(String date, String time) {
        String input = date + " " + time;
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(input, inputFormat);
    }
}
