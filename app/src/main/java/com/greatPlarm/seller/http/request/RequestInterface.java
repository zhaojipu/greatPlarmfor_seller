package com.greatPlarm.seller.http.request;

import com.greatPlarm.seller.http.responseEntity.LoginEntity;
import com.greatPlarm.seller.http.responseEntity.RootResponse;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 * 定义请求服务端的接口及请求的公共参数
 */

public interface RequestInterface {

    /**
     * 登录接口
     */
    interface Login{
        @POST("goods/getCateList")
        Observable<RootResponse<LoginEntity>> login(@Body RootRequestEntity entity );

        @FormUrlEncoded
        @GET("login")
        Observable<RootResponse<LoginEntity>> logins(@Field("loginName") String loginName,@Field("loginPassword") String loginPassword,@Field("deviceId") String deviceId);
    }

    class RootRequestEntity{
        private String versionCode;
        private String token;
        private RequestParam data;

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setData(RequestParam data) {
            this.data = data;
        }
    }
}
