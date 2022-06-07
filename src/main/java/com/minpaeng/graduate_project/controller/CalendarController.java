package com.minpaeng.graduate_project.controller;

import com.minpaeng.graduate_project.service.CalendarListService;
import com.minpaeng.graduate_project.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.GeneralSecurityException;


@RequiredArgsConstructor
@Controller
public class CalendarController {
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, Model model) throws GeneralSecurityException, IOException {
        CalendarService calendarService = new CalendarService();
        String accessTokenJsonData = calendarService.getAccessTokenJsonData(code);

        JSONObject accessTokenjsonObject = new JSONObject(accessTokenJsonData);

        String accessToken = accessTokenjsonObject.get("access_token").toString();
        System.out.println(accessToken);

        // Get CalendarList
        CalendarListService calendarListService = new CalendarListService();
        String calendarListJsonData = calendarListService.getCalendarList(accessToken);
        if(calendarListJsonData=="error") return "error";
        else System.out.println("CalendarList Json Data : " + calendarListJsonData);

        JSONObject calendarListJsonObject = new JSONObject(calendarListJsonData);
        JSONArray itemArr = (JSONArray) calendarListJsonObject.get("items");

        //결과 출력
        JSONObject item = (JSONObject)itemArr.get(0);
        System.out.println("Calender name"+" ("+item.get("id")+") : "+item.get("summary").toString());

        String url = calendarListService.insertEvent(accessToken, item.get("id").toString());
        System.out.println(url);
        return "redirect:" + url;
    }
}
