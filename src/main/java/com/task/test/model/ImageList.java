package com.task.test.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ImageList implements Serializable {
    private List<Image> pictures;
    private Boolean hasMore;

}
