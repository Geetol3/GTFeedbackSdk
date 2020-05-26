package com.geetol3.feedback.adapters;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.geetol3.feedback.R;
import com.geetol3.feedback.beans.ImageBean;
import com.geetol3.feedback.utils.GTUtils;

import java.util.List;

/**
 * 反馈图片显示适配器
 */
public class GTImageShowAdapter extends BaseQuickAdapter<ImageBean, BaseViewHolder> {

    public GTImageShowAdapter(@Nullable List<ImageBean> data) {
        super(R.layout.gt_item_suggest_image, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageBean item) {
        helper.setVisible(R.id.iv_del, false);
        ImageView imageView = helper.getView(R.id.iv_pic);
        try {
            Glide.with(mContext).load(GTUtils.showAliOssPic(item.getPath())).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
