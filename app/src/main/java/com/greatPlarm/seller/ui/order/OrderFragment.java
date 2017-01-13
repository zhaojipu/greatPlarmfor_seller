package com.greatPlarm.seller.ui.order;

import com.greatPlarm.seller.R;
import com.greatPlarm.seller.base.BaseFragment;
import com.greatPlarm.seller.base.BaseView;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/12 0012.
 */

public class OrderFragment extends BaseFragment<OrderPresenter> implements BaseView{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected OrderPresenter getmPresenter() {
        return new OrderPresenter(this);
    }

    @Override
    protected void initEventAndData() {

    }
}
