package com.greatPlarm.seller.ui.good;

import com.greatPlarm.seller.R;
import com.greatPlarm.seller.base.BaseFragment;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/12 0012.
 */

public class GoodsFragment extends BaseFragment<GoodsPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods;
    }

    @Override
    protected GoodsPresenter getmPresenter() {
        return new GoodsPresenter(this);
    }

    @Override
    protected void initEventAndData() {

    }
}
