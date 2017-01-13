package com.greatPlarm.seller.ui.shop;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greatPlarm.seller.R;
import com.greatPlarm.seller.base.BaseFragment;
import com.greatPlarm.seller.base.BaseView;
import com.greatPlarm.seller.ui.set.SettingActivity;
import com.wangku.library.utils.IntentUtils;
import com.wangku.library.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/12 0012.
 */

public class ShopFragment extends BaseFragment<ShopPresenter> implements BaseView {
    @BindView(R.id.btn_back)
    LinearLayout btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_manu)
    LinearLayout llManu;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.rlCommonHead)
    RelativeLayout rlCommonHead;
    @BindView(R.id.iv_store_logo)
    ImageView ivStoreLogo;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_store_location)
    TextView tvStoreLocation;
    @BindView(R.id.tv_store_address)
    TextView tvStoreAddress;
    @BindView(R.id.tv_store_pay_method)
    TextView tvStorePayMethod;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected ShopPresenter getmPresenter() {
        return new ShopPresenter(this);
    }

    @Override
    protected void initEventAndData() {

        btnBack.setVisibility(View.GONE);
        tvTitle.setText("店铺管理");
        ivDelete.setVisibility(View.VISIBLE);
        ivDelete.setImageResource(R.mipmap.wddp_set);
    }



    @OnClick(R.id.iv_delete)
    public void onClick(View view) {
        ToastUtils.show(mContext,"Cesh");
        IntentUtils.showActivity(mActivity, SettingActivity.class);
    }
}
