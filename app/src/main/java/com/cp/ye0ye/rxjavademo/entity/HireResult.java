package com.cp.ye0ye.rxjavademo.entity;

import java.util.List;

/**
 * Created by Administrator on 7/7/2017.
 */
public class HireResult {


    private ResultBean mResultBean;

    public ResultBean getResultBean() {
        return mResultBean;
    }

    public void setResultBean(ResultBean resultBean) {
        mResultBean = resultBean;
    }


    public static class ResultBean {

        private List<DataBean> mDataBeen;

        public List<DataBean> getDataBeen() {
            return mDataBeen;
        }

        public void setDataBeen(List<DataBean> dataBeen) {
            mDataBeen = dataBeen;
        }


        public static class DataBean {

            //数据体现在这里
            String title;
            String hireTime;
            String hireThing;
            String city;
            String publishTime;


            public String getTitle() {
                return title;
            }

            public void setTitle(final String title) {
                this.title = title;
            }

            public String getHireTime() {
                return hireTime;
            }

            public void setHireTime(String hireTime) {
                this.hireTime = hireTime;
            }

            public String getHireThing() {
                return hireThing;
            }

            public void setHireThing(String hireThing) {
                this.hireThing = hireThing;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

        }

    }


}