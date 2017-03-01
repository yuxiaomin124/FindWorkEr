package com.jinyuankeji.yxm.findhuo.lottery.detail;

import com.google.gson.annotations.SerializedName;

/**
 * Created by  yxiaomin on 2017/3/1 0001.
 */

public class LotteryAddPayMsBean {

    /**
     *
     * res : 10001
     * cache_update : 10000
     * data : {"appid":"wx63c88ed7e6190564","noncestr":"AAmKRjronH38MAgxymEbjAYWCnJhOj3H","package":"Sign=WXPay","partnerid":"1436891202","prepayid":"wx201703011403538e992553090757541465","timestamp":1488348234,"sign":"919B057766B54298E585C47AE9ACD401","packagestr":"Sign=WXPay"}
     */

    private int res;
    private int cache_update;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appid : wx63c88ed7e6190564
         * noncestr : AAmKRjronH38MAgxymEbjAYWCnJhOj3H
         * package : Sign=WXPay
         * partnerid : 1436891202
         * prepayid : wx201703011403538e992553090757541465
         * timestamp : 1488348234
         * sign : 919B057766B54298E585C47AE9ACD401
         * packagestr : Sign=WXPay
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private int timestamp;
        private String sign;
        private String packagestr;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPackagestr() {
            return packagestr;
        }

        public void setPackagestr(String packagestr) {
            this.packagestr = packagestr;
        }
    }
}
