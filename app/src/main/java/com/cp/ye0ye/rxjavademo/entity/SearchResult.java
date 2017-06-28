package com.cp.ye0ye.rxjavademo.entity;

import java.util.List;

/**
 * Created by Administrator on 6/28/2017.
 */
public class SearchResult {
    public int count;

    public boolean error;
    public List<ResultsBean> resultsBeanList;

    public static class ResultsBean {
        public String desc;
        public String ganhuo_id;
        public String publishedAt;
        public String readability;
        public String type;
        public String url;
        public String who;
    }
}
