package com.task.test.service;

import com.task.test.model.Image;
import com.task.test.model.ImageList;

import java.util.List;

public interface ImageService {
    ImageList loadImages(Integer page);

    Image loadImage(String id);
}
