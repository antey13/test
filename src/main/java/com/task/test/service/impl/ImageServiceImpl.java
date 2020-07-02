package com.task.test.service.impl;

import com.task.test.model.Image;
import com.task.test.model.ImageList;
import com.task.test.service.AuthService;
import com.task.test.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final RestTemplate restTemplate;
    private final AuthService authService;

    private final String IMAGE_PATH = "/images";
    private String token;
    @Value("${spring.agileengine.url}")
    private String baseUrl;

    @PostConstruct
    public void login() {
        token = authService.getToken();
    }

    @Override
    public ImageList loadImages(Integer page) {
        HttpEntity<ImageList> response = null;
        try {
            response = getImgsResponse(page);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                login();
                try {
                    response = getImgsResponse(page);
                } catch (HttpClientErrorException ex) {
                    throw new RuntimeException("Something wrong with loading images...");
                }
            }
        }
        if (response == null) {
            throw new RuntimeException("Received empty response on loading images, on page " + page);
        }
        return response.getBody();
    }

    @Override
    public Image loadImage(String id) {
        HttpEntity<Image> response = null;
        try {
            response = getImageDetails(id);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                login();
                try {
                    response = getImageDetails(id);
                } catch (HttpClientErrorException ex) {
                    throw new RuntimeException("Something wrong with loading images...");
                }
            }
        }
        if (response == null || response.getBody() == null) {
            throw new RuntimeException("Received empty response on loading image with id " + id);
        }
        return response.getBody();
    }

    private HttpEntity<ImageList> getImgsResponse(Integer page) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + token);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + IMAGE_PATH)
                .queryParam("page", page
                );

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                ImageList.class);
    }

    private HttpEntity<Image> getImageDetails(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                baseUrl + IMAGE_PATH + "/" + id,
                HttpMethod.GET,
                entity,
                Image.class);
    }

}
