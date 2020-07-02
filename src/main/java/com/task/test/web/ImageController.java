package com.task.test.web;

import com.task.test.model.Image;
import com.task.test.model.ImageList;
import com.task.test.service.ImageService;
import com.task.test.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.task.test.web.PathConstants.IMAGE_PATH;

@RestController
@RequestMapping(value = IMAGE_PATH)
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final SearchService searchService;

    @GetMapping("/{page}")
    public ImageList loadImage(@PathVariable(name = "page") Integer page){
        return imageService.loadImages(page);
    }

    @GetMapping("/{searchTerm}/{fullWord}")
    public List<Image> searchImages(@PathVariable(name = "searchTerm") String searchTerm,
                                    @PathVariable(name = "searchTerm") Boolean fullWord){
        return searchService.search(searchTerm, fullWord);
    }
}
