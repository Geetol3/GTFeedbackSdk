package com.geetol3.feedback.beans;

import java.io.Serializable;

/**
 * 反馈内容回调
 */
public class FeedbackBean implements Serializable {
    /**
     * 反馈id
     */
    private int id;
    /**
     * 添加时间
     */
    private String addtime;
    /**
     * 反馈状态
     */
    private int status;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String describe;
    /**
     * 类型
     */
    private String type;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddtime() {
        return this.addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
