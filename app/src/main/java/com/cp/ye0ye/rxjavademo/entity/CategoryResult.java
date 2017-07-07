package com.cp.ye0ye.rxjavademo.entity;

import java.util.List;

/**
 * Created by Administrator on 6/28/2017.
 */

public class CategoryResult {



    /**
    * 这是获取的Json字段中的是否成功
    * */
    public boolean error;

    /**
     * Json中的List实际数据
     * */
    public List<ResultsBean> results;

    public static class ResultsBean {
        public String _id; //id
        public String createdAt; //
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
        public List<String> images;

    }
}
