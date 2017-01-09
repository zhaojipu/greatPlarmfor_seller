package com.greatPlarm.seller.util;


import com.greatPlarm.seller.http.responseEntity.RootResponse;
import com.wangku.library.utils.NotNull;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 * 线程处理
 */

public class RxUtils {

    public static <T> Observable.Transformer<T,T> rxSchedulerHelper(){

        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T>Observable.Transformer<RootResponse<T>,T> handleResule(){
        return new Observable.Transformer<RootResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<RootResponse<T>> rootResponseObservable) {
                return rootResponseObservable.flatMap(new Func1<RootResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(RootResponse<T> tRootResponse) {
                        if(NotNull.isNotNull(tRootResponse)){
                            return Observable.error(new Exception());
                        }else{
                            if(tRootResponse.getCode()==200){
                                return createData(tRootResponse.getData());
                            }else{
                                return Observable.error(new Exception(tRootResponse.getMsg()));
                            }
                        }
                    }
                });
            }
        };
    }

    public static <T> Observable<T> createData(final T t){
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onNext(t);
                subscriber.onCompleted();
            }
        });
    }
}
