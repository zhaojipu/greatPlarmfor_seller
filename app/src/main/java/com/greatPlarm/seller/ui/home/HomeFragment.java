package com.greatPlarm.seller.ui.home;

import com.greatPlarm.seller.R;
import com.greatPlarm.seller.base.BaseFragment;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/12 0012.
 */

public class HomeFragment extends BaseFragment<HomePresenter>{

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter getmPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initEventAndData() {

    }


}
