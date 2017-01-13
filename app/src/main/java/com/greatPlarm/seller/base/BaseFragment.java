package com.greatPlarm.seller.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/12 0012.
 */

public abstract class BaseFragment<T extends BsaePresenter> extends SupportFragment implements BaseView {

    protected T mPresenter;
    protected View mView;
    protected Context mContext;
    protected Activity mActivity;
    private Unbinder mUnbinder;
    private boolean isInited=false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
        mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(getLayoutId(),container);
        mPresenter=getmPresenter();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
        mUnbinder= ButterKnife.bind(this,view);
        if(savedInstanceState==null){
            if (!isHidden()) {
                isInited = true;
                initEventAndData();
            }
        } else {
            if (true) {
                isInited = true;
                initEventAndData();
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isInited && !hidden) {
            isInited = true;
            initEventAndData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }

    protected abstract int getLayoutId();
protected abstract T getmPresenter();
    protected abstract void initEventAndData();
}
