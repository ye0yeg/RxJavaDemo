package com.cp.ye0ye.rxjavademo.network;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * Created by Administrator on 7/7/2017.
 * <p>
 * 从外界 JsoupNetWork.getConnect 可以获得一个mConnect，使用这个mConnect可以对数据进行操作
 */
public class JsoupNewWork {

    private int count;

    public static Connection getConnect(String url) {
        //获得url实例以后
        Connection mConnection = Jsoup.connect(url);
        //增加头
        mConnection.header("Accept-Encoding", "gzip,deflate,sdch");
        mConnection.header("Connection", "close");
        //安全证书验证
        mConnection.validateTLSCertificates(false);
        return mConnection;
    }
}
