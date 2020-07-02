package com.task.test.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Image implements Serializable {
    private String id;
    private String author;
    private String camera;
    private String tags;
    private String cropped_picture;
    private String full_picture;
}
