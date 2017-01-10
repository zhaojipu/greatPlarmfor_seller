package com.greatPlarm.seller.http.request.paramsEntity;

import com.greatPlarm.seller.http.request.RequestParam;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 * 登录接口的请求参数
 */

public class LoginParams implements RequestParam{

    private String loginName;
    private String loginPassword;

    private int shopId;
    private int pageNum;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return loginPassword;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.loginPassword = loginPassword;
    }
}
