package com.geetol3.feedback.http;

import java.util.HashMap;
import java.util.Map;

import static com.gtdev5.geetolsdk.mylibrary.util.MapUtils.getCurrencyMap;

/**
 * 请求参数帮助
 */
public class MapHelper {
    /**
     * 获取反馈详情（结束反馈）
     */
    public static Map<String, String> getFeedbackDetail(int service_id) {
        Map<String, String> map = new HashMap<>();
        map.putAll(getCurrencyMap());
        map.put("id", String.valueOf(service_id));
        return map;
    }

    /**
     * 获取反馈列表
     *
     * @param page  页码
     * @param limit 单页条数
     */
    public static Map<String, String> getFeedbackList(int page, int limit) {
        Map<String, String> map = new HashMap<>();
        map.putAll(getCurrencyMap());
        map.put("page", String.valueOf(page));
        map.put("limit", String.valueOf(limit));
        return map;
    }

    /**
     * 添加反馈
     *
     * @param title    标题  不能为空
     * @param describe 简介
     * @param type    类型
     * @param img_url oss图片  多个用，分割
     */
    public static Map<String, String> addFeedback(String title, String describe, String type, String img_url) {
        Map<String, String> map = new HashMap<>();
        map.putAll(getCurrencyMap());
        map.put("title", title);
        map.put("describe", describe);
        map.put("type", type);
        map.put("img_url", img_url);
        return map;
    }

    /**
     * 添加反馈回复
     */
    public static Map<String, String> addReply(int service_id, String reply, String img_url) {
        Map<String, String> map = new HashMap<>();
        map.putAll(getCurrencyMap());
        map.put("service_id", String.valueOf(service_id));
        map.put("desc", reply);
        map.put("img_url", img_url);
        return map;
    }
}
