package com.geetol3.feedback.beans;

import java.io.Serializable;

/**
 * 图片回调
 */
public class ImageBean implements Serializable {
    private String path;

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
