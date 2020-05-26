package com.geetol3.feedback.beans;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 回复回调
 */
public class ReplyBean implements Serializable, MultiItemEntity {
    public static final int RIGHT = 1;
    public static final int LEFT = 2;

    /**
     * 结束时间
     */
    private String addtime;
    /**
     * 描述
     */
    private String describe;
    /**
     * 类型
     */
    private int type;
    private String staff;
    /**
     * 图片
     */
    private List<ImageBean> img;

    public String getAddtime() {
        return this.addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
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

    @Override
    public int getItemType() {
        return type;
    }
}
