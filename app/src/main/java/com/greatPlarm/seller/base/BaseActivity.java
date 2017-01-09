package com.greatPlarm.seller.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greatPlarm.seller.R;
import com.wangku.library.app.App;
import com.wangku.library.utils.NotNull;
import com.wangku.library.utils.ViewUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 * 基类，简单的展示界面，例如：设置、关于我们等
 */

public abstract class BaseActivity extends SupportActivity implements BaseView {



    public abstract void initEventAndData();

    protected Context mContext;
    private RelativeLayout baseHeadView;
    private RelativeLayout baseContentView;
    private RelativeLayout baseErrorView;
    protected LayoutInflater inflater;
    protected TextView tvTitle;
    protected LinearLayout btnBack;
    protected Bundle bundle;
    private Unbinder bind;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        mContext = this;
        initBaseview();
        initEventAndData();
        bundle=getIntent().getExtras();
    }

    /**
     * 给界面设置带有标题跟返回键的布局
     *
     * @param layoutId--布局id
     */
    protected void setStandardLayout(int layoutId) {
        baseHeadView.setVisibility(View.VISIBLE);
        setBaseHeadView();
        setBaseContentView(layoutId);

        bind = ButterKnife.bind(this);
        App.getInstance().addActivity(this);
    }

    /**
     * 设置为无标题的界面
     * @param layoutId
     */
    protected void setNoHeadLayout(int layoutId){
        setStandardLayout(layoutId);
        baseHeadView.setVisibility(View.GONE);
    }
    /**
     * 初始化根布局
     */
    private void initBaseview() {
        baseHeadView = (RelativeLayout) findViewById(R.id.rl_baseLayout_head);
        baseContentView = (RelativeLayout) findViewById(R.id.rl_baseLayout_content);
        baseErrorView = (RelativeLayout) findViewById(R.id.rl_baseLayout_error);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    private void setBaseHeadView() {
        ViewUtils.addViewToLayout(inflater, R.layout.common_head, baseHeadView);
        initHeadView();
    }

    protected void setBaseContentView(int layoutId) {
        ViewUtils.addViewToLayout(inflater, layoutId, baseContentView);
    }

    private void initHeadView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.base_head_bar_height));
        baseHeadView.setLayoutParams(params);
        btnBack = (LinearLayout) baseHeadView.findViewById(R.id.btn_back);
        tvTitle = (TextView) baseHeadView.findViewById(R.id.tv_title);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.this.finish();
            }
        });
    }

    /**
     * 设置标题
     *
     * @param title--标题
     */
    protected void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(NotNull.isNotNull(bind)){
            bind.unbind();
        }
        App.getInstance().removeActivity(this);
    }
}
