package com.task.test.service.impl;

import com.task.test.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RestTemplate restTemplate;
    private final String AUTH_PATH = "/auth";
    @Value("${spring.agileengine.url}")
    private String baseUrl;
    @Value("${spring.apiKey}")
    private String apiKey;

    @Override
    public String getToken() {
        HttpEntity<AuthRequest> request = new HttpEntity<>(new AuthRequest(apiKey));
        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(baseUrl + AUTH_PATH, request, AuthResponse.class);
        if(response.getStatusCode() != HttpStatus.OK){
            throw new RuntimeException("Authorization failed with status "+response.getStatusCode());
        }
        if(response.getBody() == null){
            throw new RuntimeException("Authorization failed! Received empty response.");
        }
        if(Strings.isBlank(response.getBody().getToken())){
            throw new RuntimeException("Authorization failed! Invalid api-key.");
        }
        return response.getBody().getToken();
    }

    @Data
    @AllArgsConstructor
    private static class AuthRequest implements Serializable {
        private String apiKey;
    }

    @Data
    private static class AuthResponse implements Serializable {
        private Boolean auth;
        private String token;
    }

}
