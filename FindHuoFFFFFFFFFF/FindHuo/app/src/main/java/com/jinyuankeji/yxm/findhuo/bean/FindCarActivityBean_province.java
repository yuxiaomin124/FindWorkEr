package com.jinyuankeji.yxm.findhuo.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class FindCarActivityBean_province {

    /**
     * res : 10001
     * cache_update : 10000
     * data : [{"id":"2","name":"北京"},{"id":"3","name":"安徽"},{"id":"4","name":"福建"},{"id":"5","name":"甘肃"},{"id":"6","name":"广东"},{"id":"7","name":"广西"},{"id":"8","name":"贵州"},{"id":"9","name":"海南"},{"id":"10","name":"河北"},{"id":"11","name":"河南"},{"id":"12","name":"黑龙江"},{"id":"13","name":"湖北"},{"id":"14","name":"湖南"},{"id":"15","name":"吉林"},{"id":"16","name":"江苏"},{"id":"17","name":"江西"},{"id":"18","name":"辽宁"},{"id":"19","name":"内蒙古"},{"id":"20","name":"宁夏"},{"id":"21","name":"青海"},{"id":"22","name":"山东"},{"id":"23","name":"山西"},{"id":"24","name":"陕西"},{"id":"25","name":"上海"},{"id":"26","name":"四川"},{"id":"27","name":"天津"},{"id":"28","name":"西藏"},{"id":"29","name":"新疆"},{"id":"30","name":"云南"},{"id":"31","name":"浙江"},{"id":"32","name":"重庆"},{"id":"33","name":"香港"},{"id":"34","name":"澳门"},{"id":"35","name":"台湾"}]
     */

    private int res;
    private int cache_update;
    private List<DataBean> data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getCache_update() {
        return cache_update;
    }

    public void setCache_update(int cache_update) {
        this.cache_update = cache_update;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * name : 北京
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
