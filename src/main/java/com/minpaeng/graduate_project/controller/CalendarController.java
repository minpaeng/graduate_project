package com.minpaeng.graduate_project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.minpaeng.graduate_project.dto.AppointmentResponseDto;
import com.minpaeng.graduate_project.service.AppointmentService;
import com.minpaeng.graduate_project.service.CalendarService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class CalendarController {
    private final CalendarService calendarService;
    private final AppointmentService appointmentService;

    // 일정 데이터 삽입
    @GetMapping("/insert")
    public String insert(@RequestParam(name = "date")String date) throws GeneralSecurityException, IOException {
        System.out.println("전달받은 날짜: " + date);
        String calendarId = calendarService.getCalendarList();
        String url = calendarService.insertEvent(calendarId, date);
        return "redirect:" + url;
    }

    @GetMapping("/calendar")
    public String showIndex(@RequestParam(name = "group_id") Long groupId,
                            @RequestParam(name = "date") String date,
                            @RequestParam(name = "time") String time,
                            Model model) throws JsonProcessingException {

        List<AppointmentResponseDto> responseDto =
                appointmentService.findAppointments(groupId, date, time);

        //resultDate 값만 뽑아서 List로 만들기
        List<String> resultDate = new ArrayList<>();
        for(AppointmentResponseDto x : responseDto) {
            resultDate.add(x.getResultDate());
        }

        model.addAttribute("appointments", resultDate);
        return "calendar";
    }
}
