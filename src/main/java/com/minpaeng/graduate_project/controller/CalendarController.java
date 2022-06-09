package com.minpaeng.graduate_project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.minpaeng.graduate_project.dto.AppointmentResponseDto;
import com.minpaeng.graduate_project.dto.PersonalResponseDto;
import com.minpaeng.graduate_project.service.AppointmentService;
import com.minpaeng.graduate_project.service.CalendarService;
import com.minpaeng.graduate_project.service.PersonalService;
import com.minpaeng.graduate_project.service.UsersService;
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
    private final PersonalService personalService;
    private final UsersService usersService;

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
        System.out.println(time);

        List<PersonalResponseDto> responseDto2 =
                personalService.findSpeakersTime(groupId, date, time);

        System.out.println(responseDto2.get(0).getUserId() + ", " + responseDto2.get(0).getResult());

        //resultDate 값만 뽑아서 List로 만들기
        List<String> resultDate = new ArrayList<>();
        List<String> resultTime = new ArrayList<>();
        for(AppointmentResponseDto x : responseDto) {
            resultDate.add(x.getResultDate().substring(0, 10));
            resultTime.add(x.getResultDate().substring(11));
        }

        //화자별 가능 날짜 및 시간 파싱
        List<String> speakersDate = new ArrayList<>();
        List<String> speakersTime = new ArrayList<>();
        List<String> speakers = new ArrayList<>();

        for(PersonalResponseDto x : responseDto2) {
            String name = usersService.userName(x.getUserId());
            speakers.add(name);
            speakersDate.add(x.getResult().substring(0, 10));
            speakersTime.add(x.getResult().substring(11));
        }

        System.out.println(speakers);
        System.out.println(speakersDate);
        System.out.println(speakersTime);
        model.addAttribute("speakers", speakers);
        model.addAttribute("speakersDate", speakersDate);
        model.addAttribute("speakersTime", speakersTime);
        model.addAttribute("appointmentDate", resultDate);
        model.addAttribute("appointmentTime", resultTime);
        return "calendar";
    }
}
