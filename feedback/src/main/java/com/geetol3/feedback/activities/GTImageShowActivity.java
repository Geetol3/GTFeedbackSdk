package com.geetol3.feedback.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.geetol3.feedback.R;
import com.geetol3.feedback.adapters.GTImagePagerAdapter;
import com.geetol3.feedback.beans.ImageBean;
import com.geetol3.feedback.utils.GTUtils;
import com.geetol3.feedback.widget.MultiViewpager;
import com.gtdev5.geetolsdk.mylibrary.base.BaseGTActivity;
import com.gtdev5.geetolsdk.mylibrary.util.GsonUtils;

import java.util.List;

/**
 * 图片展示
 */
public class GTImageShowActivity extends BaseGTActivity {
    public static final String IMG_DATAS = "datas";
    public static final String IMG_POS = "pos";
    private TextView mTitleText;
    private MultiViewpager mViewPager;
    private GTImagePagerAdapter mAdapter;
    private int mPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gt_activity_image_show);
        GTUtils.setImmersionStatusBar(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        ImageView back = findViewById(R.id.iv_back);
        mTitleText = findViewById(R.id.tv_title);
        mViewPager = findViewById(R.id.viewPager);
        back.setOnClickListener(v -> onBackPressed());
        initViewPager();
    }

    /**
     * 初始化viewPager
     */
    private void initViewPager() {
        mPosition = getIntent().getIntExtra(IMG_POS, 0);
        List<ImageBean> mPics = GsonUtils.getFromList(getIntent().getStringExtra(IMG_DATAS), ImageBean.class);
        int index = mPosition + 1;
        mTitleText.setText("图片展示(" + index + "/" + mPics.size() + ")");
        mAdapter = new GTImagePagerAdapter(this, mPics);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mPosition < mPics.size() ? mPosition : 0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int index = position + 1;
                mTitleText.setText("图片展示(" + index + "/" + mAdapter.getSize() + ")");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
