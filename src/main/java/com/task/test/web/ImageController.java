package com.task.test.web;

import com.task.test.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.task.test.web.PathConstants.IMAGE_PATH;

@RestController
@RequestMapping(value = IMAGE_PATH)
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{page}")
    public void loadImage(@PathVariable(name = "page") Integer page){
        imageService.loadImages(page);
    }
}
