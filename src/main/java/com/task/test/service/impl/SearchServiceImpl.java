package com.task.test.service.impl;

import com.task.test.cache.ImageCache;
import com.task.test.model.Image;
import com.task.test.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final ImageCache imageCache;

    @Override
    public List<Image> search(String searchTerm, boolean fullWord) {
        Map<String, List<Image>> cache = this.imageCache.getCache();
        if (fullWord) {
            return cache.get(searchTerm);
        }

        return cache.keySet().stream().filter(key -> key.contains(searchTerm)).map(cache::get).flatMap(List::stream)
                .distinct().collect(Collectors.toList());
    }
}
