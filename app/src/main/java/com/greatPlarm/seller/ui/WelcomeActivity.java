package com.greatPlarm.seller.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.greatPlarm.seller.R;
import com.greatPlarm.seller.base.BaseActivity;
import com.wangku.library.utils.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 */

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.iv_welcome)
    ImageView ivWelcome;

    @Override
    public void initEventAndData() {
        setNoHeadLayout(R.layout.activity_welcome);
        setTitle("欢迎");

        textView.setText("4545454ds5");
    }

    /**
     * 开机动画
     */
    private void initAnimotion() {

    }


    @OnClick(R.id.textView)
    public void onClick(View view) {
        IntentUtils.showActivity(this, MainActivity.class);
    }




}
