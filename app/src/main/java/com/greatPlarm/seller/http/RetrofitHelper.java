package com.greatPlarm.seller.http;

import com.greatPlarm.seller.app.Contants;
import com.greatPlarm.seller.http.request.RequestInterface;
import com.greatPlarm.seller.http.request.RequestParam;
import com.greatPlarm.seller.http.responseEntity.LoginEntity;
import com.greatPlarm.seller.http.responseEntity.RootResponse;
import com.wangku.library.utils.NetworkStatusManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 */

public class RetrofitHelper {
    private static OkHttpClient okHttpClient;
    private static RetrofitHelper instance=new RetrofitHelper();
    private Retrofit retrofit;
    private RequestInterface.RootRequestEntity requestEntity;
    private RetrofitHelper(){
        init();
    }

    public static RetrofitHelper get(){
        return instance;
    }
    private void init() {
        initOkHttp();
        requestEntity=new RequestInterface.RootRequestEntity();
        requestEntity.setToken("4515451545af4ds5f4dsfdssd54f");
        requestEntity.setVersionCode("1.0.0");
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        /*1、设置Log日志的打印方式*/
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        /*2、设置缓存路径*/
        File cacheFile=new File(Contants.PATH_CACHE);
        final Cache cache=new Cache(cacheFile,1024*1024*50L);
        Interceptor cacheInterceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request();

                if(!NetworkStatusManager.isNetworkConnected()){
                    request=request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response=chain.proceed(request);
                if (NetworkStatusManager.isNetworkConnected()){
                    //有网络时，不缓存，最大保存时长为0
                    int maxAge=0;
                    response.newBuilder()
                            .header("Cache-Control","public,max-age="+maxAge)
                            .removeHeader("Pragma")
                            .build();

                }else{
                    //无网络时，设置超时为4周
                    int maxStale=60*60*24*4;
                    response.newBuilder()
                            .header("Cache-Control","public, only-if-cache, max-stale="+maxStale)
                            .build();
                }

                return response;
            }
        };
        /*3、设置缓存*/
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        /*4、设置超时*/
        builder.connectTimeout(30L, TimeUnit.SECONDS);//网络链接30秒
        builder.readTimeout(20L, TimeUnit.SECONDS);//从内存或sd卡读取数据20秒
        builder.writeTimeout(20L, TimeUnit.SECONDS);//向sd卡写入数据20秒
        /*5、错误重连*/
        builder.retryOnConnectionFailure(true);
        okHttpClient=builder.build();

        /*6、初始化Retrofit*/
        retrofit=new Retrofit.Builder()
                .baseUrl(Contants.HTTP_URL_HEAD)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    /**
     * 登录接口
     * @param requestParam--登录接口的请求参数对象
     * @return--请求完登录接口后，返回的数据
     */
    public Observable<RootResponse<LoginEntity>> login(RequestParam requestParam){
        requestEntity.setData(requestParam);
        return retrofit.create(RequestInterface.Login.class).login(requestEntity);
    }

    /**
     * 登录接口
     * @param requestParam--登录接口的请求参数对象
     * @return--请求完登录接口后，返回的数据
     */
    public Observable<RootResponse<LoginEntity>> logins(RequestParam requestParam){
//        requestEntity.setData(requestParam);
        HashMap<String,String> map=new HashMap<>();
        map.put("loginName","weihua123");
        map.put("loginPassword","wk1234");
        map.put("deviceId","weihua123");
        return retrofit.create(RequestInterface.Login.class).logins("weihua123","weihua123","sd");
    }
}
