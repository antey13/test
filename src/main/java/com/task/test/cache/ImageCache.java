package com.task.test.cache;

import com.task.test.model.Image;
import com.task.test.model.ImageList;
import com.task.test.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageCache {
    private final ImageService imageService;
    private final Map<String, List<Image>> cache = new HashMap<>();

    public Map<String, List<Image>> getCache() {
        return cache;
    }

    @PostConstruct
    @Scheduled(fixedRateString = "${spring.cache.update.time}")
    public void cacheUpdate(){
        fillCache();
    }

    public void fillCache(){
        for (int page = 0; page > -1; page++) {
            ImageList imageList = imageService.loadImages(page);
            if(!imageList.getHasMore())
                break;
            imageList.getPictures().stream().map(image -> imageService.loadImage(image.getId())).forEach(image -> {
                for (Field field : image.getClass().getDeclaredFields()) {
                    if(field.getName().contains("picture"))
                        continue;
                    field.setAccessible(true);
                    try {
                        String param = (String) field.get(image);
                        if(param == null)
                            continue;
                        for (String word : param.split(" ")) {
                            addValue(word, image);
                        }
                    } catch (IllegalAccessException e) {
                        log.error(e.getMessage());
                    }
                }
            });
        }
    }

    private void addValue(String param,Image image){
        if(cache.containsKey(param)){
            cache.get(param).add(image);
        } else {
            ArrayList<Image> value = new ArrayList<>();
            value.add(image);
            cache.put(param, value);
        }
    }
}
