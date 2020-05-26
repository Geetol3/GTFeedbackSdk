package com.geetol3.feedback.http;

import com.gtdev5.geetolsdk.mylibrary.callback.BaseCallback;
import com.gtdev5.geetolsdk.mylibrary.contants.API;
import com.gtdev5.geetolsdk.mylibrary.contants.ApiConfig;
import com.gtdev5.geetolsdk.mylibrary.http.HttpUtils;

/**
 * 请求工具类
 */
public class HttpHelper {
    /**
     * 添加服务单
     */
    private static String ADD_FEEDBACK = "sup.add_service_oss";
    /**
     * 获取服务单
     */
    private static String GET_FEEDBACK = "sup.get_service";
    /**
     * 获取服务单详情
     */
    private static String FEEDBACK_DETAIL = "sup.get_service_details_oss";
    /**
     * 添加服务单回复
     */
    private static String ADD_REPLY = "sup.add_reply_oss";
    /**
     * 结束服务单
     */
    private static String END_FEEDBACK = "sup.end_service";

    /**
     * 获取接口参数
     *
     * @param command 接口名称
     */
    public static String getUrl(String command) {
        return API.getUrl(ApiConfig.getInstance().getCommand(command));
    }

    /**
     * 结束反馈
     *
     * @param id 反馈id
     */
    public static void endFeedback(int id, BaseCallback callback) {
        HttpUtils.getInstance().post(getUrl(END_FEEDBACK),
                MapHelper.getFeedbackDetail(id), callback);
    }

    /**
     * 获取反馈详情
     *
     * @param service_id 反馈id
     */
    public static void getFeedbackDetail(int service_id, BaseCallback callback) {
        HttpUtils.getInstance().post(getUrl(FEEDBACK_DETAIL),
                MapHelper.getFeedbackDetail(service_id), callback);
    }

    /**
     * 获取反馈
     */
    public static void getFeedbackList(int page, int limit, BaseCallback callback) {
        HttpUtils.getInstance().post(getUrl(GET_FEEDBACK),
                MapHelper.getFeedbackList(page, limit), callback);
    }

    /**
     * 添加反馈
     *
     * @param title 标题
     * @param describe 内容
     * @param type 反馈类型
     * @param img_url oss图片  多个用，分割
     */
    public static void addFeedback(String title, String describe, String type, String img_url, BaseCallback callback) {
        HttpUtils.getInstance().post(getUrl(ADD_FEEDBACK),
                MapHelper.addFeedback(title, describe, type, img_url), callback);
    }

    /**
     * 添加回复
     *
     * @param service_id 反馈id
     * @param reply 回复内容
     * @param img_url 回复图片
     */
    public static void addReply(int service_id, String reply, String img_url, BaseCallback callback) {
        HttpUtils.getInstance().post(getUrl(ADD_REPLY),
                MapHelper.addReply(service_id, reply, img_url), callback);
    }
}
