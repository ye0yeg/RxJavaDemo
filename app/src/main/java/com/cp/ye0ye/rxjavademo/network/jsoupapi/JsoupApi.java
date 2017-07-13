package com.cp.ye0ye.rxjavademo.network.jsoupapi;

import com.cp.ye0ye.rxjavademo.entity.HireResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 7/7/2017.
 */

public interface JsoupApi {

    @GET("page-{page}")
    Observable<HireResult> getHireData(@Path("page") int page);

}
