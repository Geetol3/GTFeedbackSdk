package com.geetol3.feedback.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.geetol3.feedback.R;
import com.geetol3.feedback.beans.FeedbackBean;

import java.util.List;

/**
 * 反馈列表适配器
 */
public class GTSuggestListAdapter extends BaseQuickAdapter<FeedbackBean, BaseViewHolder> {

    public GTSuggestListAdapter(@Nullable List<FeedbackBean> data) {
        super(R.layout.gt_item_suggest, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedbackBean item) {
        helper.setText(R.id.tv_time, item.getAddtime());
        String temp = "待回复";
        switch (item.getStatus()) {
            case 0:
            case 1:
                temp = "待回复";
                break;
            case 2:
                temp = "已回复";
                break;
            case 99:
                temp = "已解决";
                break;
        }
        helper.setText(R.id.tv_state, "状态：" + temp);
        helper.setText(R.id.tv_type, "类型：" + item.getType());
        helper.setText(R.id.tv_title, "标题：" + item.getTitle());
        helper.setText(R.id.tv_content, item.getDescribe());
    }
}
