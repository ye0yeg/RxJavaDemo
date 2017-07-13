package com.cp.ye0ye.rxjavademo.network;

import com.cp.ye0ye.rxjavademo.entity.HireResult;
import com.cp.ye0ye.rxjavademo.network.api.GankApi;
import com.cp.ye0ye.rxjavademo.network.jsoupapi.JsoupApi;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 6/28/2017.
 */

public class NetWork {
    private static final String XYZP_URL = "http://xyzp.haitou.cc";
    private static final String SS_URL = "http://sxxx.haitou.cc";

    private static List<HireResult.ResultBean.DataBean> listData = new ArrayList<>();
    private static List<HireResult.ResultBean.DataBean> listDataSS = new ArrayList<>();
    static HireResult.ResultBean.DataBean hireResult;


    private static GankApi gankApi;
    private static JsoupApi jsoupApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();


    public static GankApi getGankApi() {
        if (gankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    //使用okHttpClient请求
                    .client(okHttpClient)
                    //基Url
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            gankApi = retrofit.create(GankApi.class);
        }
        return gankApi;
    }

    private static JsoupApi getJsoupApi() {
        if (jsoupApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://xyzp.haitou.cc")
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            jsoupApi = retrofit.create(JsoupApi.class);
        }
        return jsoupApi;
    }

    public static List<HireResult.ResultBean.DataBean> getHireData(int mPage) {
        try {
            if (listData != null) {
                listData = null;
                listData = new ArrayList<>();
            }
            ///page-2
            Document document = JsoupNewWork.getConnect(XYZP_URL + "/page-" + mPage).get();
            Elements elements = document.select("tbody tr");
            for (Element element :
                    elements) {
                hireResult = new HireResult.ResultBean.DataBean();
                String name = element.getElementsByClass("text-success company").text();
                String time = element.getElementsByClass("cxxt-time").text();
                String citi = element.getElementsByClass("text-ellipsis").text();
                String url = "http://xyzp.haitou.cc" + element.getElementsByTag("a").get(0).attr("href");

                System.out.println(name + time + citi + url + "  页码：" + mPage);

                hireResult.setTitle(name);
                hireResult.setHireTime(time);
                hireResult.setCity(citi);
                hireResult.setUrl(url);

                listData.add(hireResult);
                System.out.println(listData.size());
            }
            return listData;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException::失败和失败原因：" + e.toString());
        }
        return null;
    }

    public static List<HireResult.ResultBean.DataBean> getInter(int page) {
        try {
            if (listDataSS != null) {
                listDataSS = null;
                listDataSS = new ArrayList<>();
            }
            ///page-2
            Document document = JsoupNewWork.getConnect(SS_URL + "/page-" + page).get();
            Elements elements = document.select("tbody tr");
            for (Element element :
                    elements) {
                hireResult = new HireResult.ResultBean.DataBean();
                String name = element.getElementsByClass("text-success company").text();
                String time = element.getElementsByClass("cxxt-time").text();
                String citi = element.getElementsByClass("text-ellipsis").text();
                String url = "http://sxxx.haitou.cc" + element.getElementsByTag("a").get(0).attr("href");
                System.out.println(name + time + citi + url + "  页码：" + page);

                hireResult.setTitle(name);
                hireResult.setHireTime(time);
                hireResult.setCity(citi);
                hireResult.setUrl(url);

                listDataSS.add(hireResult);
                System.out.println(listDataSS.size());
            }
            return listDataSS;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException::失败和失败原因：" + e.toString());
        }
        return null;
    }
}
