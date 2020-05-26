package com.geetol3.feedback.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 反馈内容详细数据回调
 */
public class FeedbackDetailBean implements Serializable {
    /**
     * 反馈id
     */
    private int id;
    /**
     * 添加时间
     */
    private String addtime;
    /**
     * 状态
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
    /**
     * 结束时间
     */
    private String endtime;
    private String evalevel;
    private String evadesc;
    /**
     * 设备id
     */
    private String device_id;
    private String staff;
    /**
     * 图片
     */
    private List<ImageBean> img;
    /**
     * 回复
     */
    private List<ReplyBean> reply;

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

    public String getEndtime() {
        return this.endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getEvalevel() {
        return this.evalevel;
    }

    public void setEvalevel(String evalevel) {
        this.evalevel = evalevel;
    }

    public String getEvadesc() {
        return this.evadesc;
    }

    public void setEvadesc(String evadesc) {
        this.evadesc = evadesc;
    }

    public String getDevice_id() {
        return this.device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getStaff() {
        return this.staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public List<ImageBean> getImg() {
        return this.img;
    }

    public void setImg(List<ImageBean> img) {
        this.img = img;
    }

    public List<ReplyBean> getReply() {
        return this.reply;
    }

    public void setReply(List<ReplyBean> reply) {
        this.reply = reply;
    }
}
