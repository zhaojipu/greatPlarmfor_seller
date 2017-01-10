package com.greatPlarm.seller.http;

import android.content.Context;

import rx.functions.Action1;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 */

public class ErrorCallBack implements Action1<Throwable>{
    private Context context;

    public ErrorCallBack(Context context) {
        this.context = context;
    }

    @Override
    public void call(Throwable throwable) {
        String message = throwable.getMessage();
    }
}
