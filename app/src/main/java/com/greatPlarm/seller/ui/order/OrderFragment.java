package com.greatPlarm.seller.ui.order;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greatPlarm.seller.R;
import com.greatPlarm.seller.adapters.MyPagerAdapter;
import com.greatPlarm.seller.base.BaseFragment;
import com.wangku.library.widget.pager.PagerSlidingTab;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/12 0012.
 */

public class OrderFragment extends BaseFragment<OrderPresenter>  {
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
    @BindView(R.id.pagerSlidingTab)
    PagerSlidingTab pagerSlidingTab;
    @BindView(R.id.viewPager_order)
    ViewPager viewPagerOrder;

    MyPagerAdapter adapter;
    List<Fragment> fragments=new LinkedList<>();
    private String[] titles={"全部","进行中","已完成","已关闭","退款"};

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
        btnBack.setVisibility(View.GONE);
        tvTitle.setText("订单管理");

        initFragments();

    }

    private void initFragments() {
        fragments.add(new AllOrderFragment());
        fragments.add(new UnderWayOrderFragment());
        fragments.add(new CompletedOrderFragment());
        fragments.add(new ClosedOrderFragment());
        fragments.add(new RefundOrderFragment());

        adapter=new MyPagerAdapter(getFragmentManager(),fragments,titles);
        viewPagerOrder.setAdapter(adapter);
        pagerSlidingTab.setViewPager(viewPagerOrder);
    }


}
