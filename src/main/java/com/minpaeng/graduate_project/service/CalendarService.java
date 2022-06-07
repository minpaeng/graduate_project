package com.minpaeng.graduate_project.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class CalendarService {
    private final String CLIENT_ID = "967060435626-cs4uf2d3fg0o9odnf4mtvp6q7a3ebe17.apps.googleusercontent.com";
    private final String CLIENT_SECRET= "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";
    private final String REDIRECT_URI= "http://localhost:8080/callback";
    private final String GRANT_TYPE= "authorization_code";
    private final String TOKEN_URL = "https://oauth2.googleapis.com/token";

    public String getAccessTokenJsonData(String code){
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", CLIENT_ID);
        params.put("client_secret", CLIENT_SECRET);
        params.put("redirect_uri", REDIRECT_URI);
        params.put("grant_type", GRANT_TYPE);

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(TOKEN_URL, params, String.class); // POST 방식으로 전송

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody(); // JSON 데이터 반환
        }
        return "error";
    }
}
