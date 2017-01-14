package com.greatPlarm.seller.ui.order;

import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greatPlarm.seller.R;
import com.greatPlarm.seller.base.BaseFragment;
import com.greatPlarm.seller.weight.CustomSwipeRefreshLayout;
import com.wangku.library.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/14 0014.
 */

public abstract class BaseOrderFragment extends BaseFragment<OrderPresenter> {
    @BindView(R.id.btn_reload)
    Button btnReload;
    @BindView(R.id.layout_error)
    LinearLayout layoutError;
    @BindView(R.id.recycler_order)
    RecyclerView recyclerOrder;
    @BindView(R.id.swipeLayout_order)
    CustomSwipeRefreshLayout swipeLayoutOrder;
    @BindView(R.id.tv_order_title)
    TextView tvTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_order;
    }

    @Override
    protected OrderPresenter getmPresenter() {
        return new OrderPresenter(this);
    }

    public abstract void getOrderList();

    public abstract void refreshOrderList();

    public abstract void loadmoreOrderList();


    @OnClick(R.id.btn_reload)
    public void onClick() {
        ToastUtils.show(mActivity,"正在加载");
    }
}
