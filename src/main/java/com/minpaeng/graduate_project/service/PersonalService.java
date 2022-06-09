package com.minpaeng.graduate_project.service;

import com.minpaeng.graduate_project.domain.PersonalRepository;
import com.minpaeng.graduate_project.dto.AppointmentResponseDto;
import com.minpaeng.graduate_project.dto.PersonalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PersonalService {
    private final PersonalRepository personalRepository;

    public List<PersonalResponseDto> findSpeakersTime(Long groupId, String date, String time) {
        LocalDateTime dateTime = makeDateTime(date, time);
        //repository 조회하기
        return personalRepository
                .findPersonalsByGroupIdAndTimestamp(groupId, dateTime)
                .stream()
                .map(PersonalResponseDto::new)
                .collect(Collectors.toList());
    }

    private LocalDateTime makeDateTime(String date, String time) {
        String input = date + " " + time;
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(input, inputFormat);
    }

}
