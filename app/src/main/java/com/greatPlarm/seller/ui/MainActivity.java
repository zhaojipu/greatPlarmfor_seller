package com.greatPlarm.seller.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.greatPlarm.seller.R;
import com.greatPlarm.seller.app.MyApplication;
import com.greatPlarm.seller.http.responseEntity.LoginEntity;
import com.greatPlarm.seller.ui.good.GoodsFragment;
import com.greatPlarm.seller.ui.home.HomeFragment;
import com.greatPlarm.seller.ui.order.OrderFragment;
import com.greatPlarm.seller.ui.shop.ShopFragment;
import com.wangku.library.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {

    @BindView(R.id.realtabcontent)
    FrameLayout realtabcontent;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;

    private LayoutInflater mLayoutInflater;
    private Class fragments[] = {HomeFragment.class, OrderFragment.class, GoodsFragment.class, ShopFragment.class};

    private int images[] = {R.mipmap.home_index_default, R.mipmap.home_order_default, R.mipmap.home_goods_default, R.mipmap.home_store_default};

    private String titles[] = {"首页", "订单", "商品", "商铺"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        initEventAndData();
        setupTabView();
    }

    private void setupTabView() {
        mLayoutInflater = LayoutInflater.from(this);
        tabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        tabhost.getTabWidget().setDividerDrawable(null);
        int count = fragments.length;
        for (int i = 0; i < count; i++) {
            tabhost.addTab(
                    tabhost.newTabSpec(titles[i])
                            .setIndicator(getTabItemView(i)), fragments[i], null);
        }
    }
    //    @Override
    public void initEventAndData() {
//        setNoHeadLayout(R.layout.activity_main);

        mLayoutInflater = LayoutInflater.from(this);

        // 找到TabHost
        tabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // 得到fragment的个数
        int count = fragments.length;
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(titles[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            tabhost.addTab(tabSpec, fragments[i], null);
            // 设置Tab按钮的背景
            tabhost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.color.white_fff);
        }
    }
    public boolean isFormGoodsDetail;
    private long exitTime = 0;
    //两次点击退出程序
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (isFormGoodsDetail) {
                finish();
            } else {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    ToastUtils.show(this, getString(R.string.exit_app_tips));
                    exitTime = System.currentTimeMillis();
                } else {
                    MyApplication.getInstance().exitApp();
                }
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    public void showMsg(LoginEntity entity) {

    }

    /**
     *
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.tab_main, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_tab);
        imageView.setImageResource(images[index]);
        TextView textView = (TextView) view.findViewById(R.id.tv_tab);
        textView.setText(titles[index]);

        return view;
    }

}
