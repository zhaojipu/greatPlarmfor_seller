package com.greatPlarm.seller.ui.shop;

import com.greatPlarm.seller.R;
import com.greatPlarm.seller.base.BaseFragment;
import com.greatPlarm.seller.base.BaseView;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/12 0012.
 */

public class ShopFragment extends BaseFragment<ShopPresenter> implements BaseView{
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

    }
}
