package com.greatPlarm.seller.http.responseEntity;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 * 服务器返回数据，解析对象
 */

public class RootResponse<T> {
    private int code=-1;
    private T data;
    private String msg;

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
