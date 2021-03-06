package com.geetol3.feedback.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.geetol3.feedback.beans.ImageBean;
import com.geetol3.feedback.utils.GTUtils;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

/**
 * 图片查看大图适配器
 */
public class GTImagePagerAdapter extends PagerAdapter {
    private Context context;
    private List<ImageBean> datas;
    private OnItemClickListener listener;

    public GTImagePagerAdapter(Context context, List<ImageBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(context);
        if (listener != null) {
            photoView.setOnClickListener(view -> listener.onItemClick());
        }
        try {
            Glide.with(context).load(GTUtils.showAliOssPic(datas.get(position).getPath())).into(photoView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        container.addView(photoView);
        return photoView;
    }

    /**
     * 获取当前数据的总页数
     */
    public int getSize() {
        return datas != null ? datas.size() : 0;
    }

    public interface OnItemClickListener {
        void onItemClick();
    }
}
