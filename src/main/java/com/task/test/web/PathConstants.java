package com.task.test.web;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathConstants {
    public static final String ROOT_PATH = "/test";
    public static final String IMAGE_PATH = ROOT_PATH + "/images";
    public static final String SEARCH_FULL_PATH = "/searchFullWord/{searchTerm}";
    public static final String SEARCH_PART_PATH = "/searchPart/{searchTerm}";
}
