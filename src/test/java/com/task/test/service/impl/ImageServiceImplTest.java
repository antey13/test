package com.task.test;

import com.task.test.service.ImageService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ImageServiceImplTest {
    @Autowired
    private ImageService imageService;

    @Test
    void loadImagesTest(){
        assertNotNull(imageService.loadImages(0));
    }

    @Test
    void loadImageTest(){
        assertNotNull(imageService.loadImage("5d77f13fbc6228a5aa25"));
    }
}