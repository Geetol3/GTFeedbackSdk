package com.geetol3.feedback.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.geetol3.feedback.R;
import com.geetol3.feedback.adapters.GTImageAddAdapter;
import com.geetol3.feedback.beans.PicInfo;
import com.geetol3.feedback.http.HttpHelper;
import com.geetol3.feedback.utils.AliOssBatchPicUtils;
import com.geetol3.feedback.utils.GTMatisseUtil;
import com.geetol3.feedback.utils.GTUtils;
import com.geetol3.feedback.utils.Uri2PathUtil;
import com.gtdev5.geetolsdk.mylibrary.base.BaseGTActivity;
import com.gtdev5.geetolsdk.mylibrary.beans.AliOssBean;
import com.gtdev5.geetolsdk.mylibrary.beans.ResultBean;
import com.gtdev5.geetolsdk.mylibrary.callback.BaseCallback;
import com.gtdev5.geetolsdk.mylibrary.util.MD5Tools;
import com.gtdev5.geetolsdk.mylibrary.util.PermissionUtils;
import com.gtdev5.geetolsdk.mylibrary.util.ToastUtils;
import com.gtdev5.geetolsdk.mylibrary.util.Utils;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 反馈回复
 */
public class GTSuggestReplyActivity extends BaseGTActivity {
    private TextView mAddText;
    private EditText mContentEdit;
    private RecyclerView mPicRecyclerView;
    private GTImageAddAdapter mImageAddAdapter;
    private ProgressBar mProgressBar;
    private int mCurrentPosition, mServiceId;

    private List<PicInfo> mPicInfos = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gt_activity_suggest_reply);
        GTUtils.setImmersionStatusBar(this);
        initView();
    }

    /**
     * 初始化页面
     */
    private void initView() {
        mServiceId = getIntent().getIntExtra("data", -1);;
        ImageView back = findViewById(R.id.iv_back);
        mContentEdit = findViewById(R.id.et_content);
        mPicRecyclerView = findViewById(R.id.recyclerview);
        mProgressBar = findViewById(R.id.progressBar);
        mAddText = findViewById(R.id.tv_add);
        back.setOnClickListener(v -> onBackPressed());
        mAddText.setOnClickListener(v -> addReply());
        initRecyclerView();
    }

    /**
     * 初始化列表
     */
    private void initRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        mPicRecyclerView.setLayoutManager(manager);
        mImageAddAdapter = new GTImageAddAdapter(this, 3, null, new GTImageAddAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                mCurrentPosition = position;
                GTMatisseUtil.getPhoto(GTSuggestReplyActivity.this, 1, 1113, 115);
            }

            @Override
            public void OnAddItemClick(int count) {
                GTMatisseUtil.getPhoto(GTSuggestReplyActivity.this, count, 1113, 114);
            }
        });
        mPicRecyclerView.setAdapter(mImageAddAdapter);
    }

    /**
     * 添加回复
     */
    private void addReply() {
        String content = mContentEdit.getText().toString();
        if (Utils.isEmpty(content)) {
            ToastUtils.showShortToast("回复内容不能为空哦");
            return;
        }
        mProgressBar.setVisibility(View.VISIBLE);
        mAddText.setEnabled(false);
        if (mPicInfos.size() > 0) {
            AliOssBean aliOssBean = Utils.getAliOssParam();
            if (aliOssBean != null) {
                AliOssBatchPicUtils.getInstance(this).uploadBatchFile(aliOssBean.getBucketName(),
                        mPicInfos, (success, failure) -> {
                            if (success != null && success.size() > 0) {
                                StringBuilder sb = new StringBuilder("");
                                for (PicInfo picInfo : success) {
                                    sb.append(picInfo.getName());
                                    sb.append(",");
                                }
                                addReplyData(content, sb.toString());
                            }
                        });
            }
        } else {
            addReplyData(content, null);
        }
    }

    /**
     * 添加回复
     */
    private void addReplyData(String content, String img_url) {
        HttpHelper.addReply(mServiceId, content, img_url,
                new BaseCallback<ResultBean>() {
                    @Override
                    public void onRequestBefore() {}

                    @Override
                    public void onFailure(Request request, Exception e) {
                        mProgressBar.setVisibility(View.GONE);
                        mAddText.setEnabled(true);
                        ToastUtils.showShortToast("反馈失败" + e.toString());
                    }

                    @Override
                    public void onSuccess(Response response, ResultBean o) {
                        if (o.isIssucc()) {
                            ToastUtils.showShortToast("回复成功！");
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            mProgressBar.setVisibility(View.GONE);
                            mAddText.setEnabled(true);
                            ToastUtils.showShortToast("反馈失败" + o.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response response, int errorCode, Exception e) {
                        mProgressBar.setVisibility(View.GONE);
                        mAddText.setEnabled(true);
                        ToastUtils.showShortToast("反馈失败" + e.toString());
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case 114:
                    List<Uri> list2 = Matisse.obtainResult(data);
                    if (null != list2 && list2.size() > 0) {
                        List<String> strings = new ArrayList<>();
                        for (Uri uri : list2) {
                            strings.add(uri.toString());
                            try {
                                String path = Uri2PathUtil.getRealPathFromUri(this, uri);
                                String name = GTUtils.getPicName(path);
                                name = MD5Tools.MD5(name) + ".jpg";
                                PicInfo picInfo = new PicInfo(name, path);
                                mPicInfos.add(picInfo);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        mImageAddAdapter.AddDatas(strings);
                    }
                    break;
                case 115:
                    List<Uri> list3 = Matisse.obtainResult(data);
                    if (null != list3 && list3.size() > 0) {
                        try {
                            String path = Uri2PathUtil.getRealPathFromUri(this, list3.get(0));
                            String name = GTUtils.getPicName(path);
                            name = MD5Tools.MD5(name) + ".jpg";
                            PicInfo picInfo = new PicInfo(name, path);
                            mPicInfos.set(mCurrentPosition, picInfo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mImageAddAdapter.repeleceData(list3.get(0).toString(), mCurrentPosition);
                    }
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1113)
            PermissionUtils.onRequestMorePermissionsResult(this, GTMatisseUtil.PICTURE_PERMISSION,
                    new PermissionUtils.PermissionCheckCallBack() {
                @Override
                public void onHasPermission() {
                    ToastUtils.showShortToast("授权成功");
                }

                @Override
                public void onUserHasAlreadyTurnedDown(String... strings) {
                    ToastUtils.showShortToast("授予此权限才能添加照片哦,点击确定继续授权。");
                    PermissionUtils.requestMorePermissions(GTSuggestReplyActivity.this,
                            GTMatisseUtil.PICTURE_PERMISSION, 1113);
                }

                @Override
                public void onUserHasAlreadyTurnedDownAndDontAsk(String... strings) {
                    ToastUtils.showShortToast("您已经拒绝授权，无法继续添加照片，点击确定进入设置开启授权");
                    PermissionUtils.toAppSetting(GTSuggestReplyActivity.this);
                }
            });
    }
}
