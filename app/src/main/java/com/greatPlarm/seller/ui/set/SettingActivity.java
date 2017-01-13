package com.greatPlarm.seller.ui.set;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greatPlarm.seller.R;
import com.greatPlarm.seller.base.BaseNetActivity;
import com.greatPlarm.seller.base.BaseView;
import com.greatPlarm.seller.ui.login.LoginActivity;
import com.wangku.library.utils.IntentUtils;
import com.wangku.library.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/13 0013.
 */

public class SettingActivity extends BaseNetActivity<SettingPresente> implements BaseView {
    @BindView(R.id.tv_set_versioncode)
    TextView tvSetVersioncode;
    @BindView(R.id.tv_set_clear)
    TextView tvSetClear;
    @BindView(R.id.btn_login_out)
    Button btnLoginOut;
    @BindView(R.id.rl_check_versioncode)
    RelativeLayout rlCheckVersioncode;

    @Override
    public SettingPresente getmPresenter() {
        return new SettingPresente(this);
    }

    @Override
    public void initEventAndData() {
        setStandardLayout(R.layout.activity_setting);
        setTitle(getString(R.string.setting_title));
    }



    @OnClick({R.id.rl_check_versioncode,R.id.btn_login_out})
    public void onClick(View view) {
        switch (view.getId()){
            //退出登录
            case R.id.btn_login_out:
                ToastUtils.show(mContext,"退出");
                IntentUtils.showActivity(this, LoginActivity.class);
                this.finish();
                break;
            //版本检测
            case R.id.rl_check_versioncode:
                mPresenter.checkVersionCode();
                break;
        }
    }
}
