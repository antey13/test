package com.task.test.service;

import com.task.test.model.Image;

import java.util.List;

public interface SearchService {
    List<Image> search(String searchTerm, boolean fullWord);
}
