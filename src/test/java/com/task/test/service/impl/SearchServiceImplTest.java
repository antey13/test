package com.task.test.service.impl;

import com.task.test.service.ImageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SearchServiceImplTest {
    @Autowired
    private SearchServiceImpl searchService;

    @Test
    void searchTest(){
        assertNotNull(searchService.search("48f343496a9e6bdeb611", true));
        assertEquals(1,searchService.search("48f343496a", false).size());
    }
}