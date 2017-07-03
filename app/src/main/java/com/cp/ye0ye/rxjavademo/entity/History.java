package com.cp.ye0ye.rxjavademo.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by ye0ye on 2017/7/4.
 */
public class History extends DataSupport{
    private long createTimeMill;
    private String content;

    public long getCreateTimeMill() {
        return createTimeMill;
    }

    public void setCreateTimeMill(long createTimeMill) {
        this.createTimeMill = createTimeMill;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
