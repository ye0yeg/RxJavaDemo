package com.cp.ye0ye.rxjavademo.entity;

import java.util.List;

/**
 * Created by Administrator on 6/28/2017.
 */

public class CategoryResult {

    public boolean error;
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
