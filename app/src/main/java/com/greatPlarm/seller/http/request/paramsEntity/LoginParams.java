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
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
