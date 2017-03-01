package com.jinyuankeji.yxm.findhuo.my;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinyuankeji.yxm.findhuo.Constants;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.lottery.detail.LotteryAddPayBean;
import com.jinyuankeji.yxm.findhuo.lottery.detail.LotteryAddPayMsBean;
import com.jinyuankeji.yxm.findhuo.tools.DataValue;
import com.jinyuankeji.yxm.findhuo.tools.URLValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2016/12/22 0022.
 */

public class MoneyActivity extends Activity {
    private ImageView back;
    private Button addPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_money);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addPay = (Button) findViewById(R.id.btn_add_pay);
        getPay();
        addPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pay();
                //
            }
        });

    }

    private LotteryAddPayBean mPayBean;
    private int data;

    private void getPay() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        Log.d("LotteryDetailActivity", DataValue.LOTTERY_MAIN_ID);
        params.addBodyParameter("account", DataValue.DRIVER_YES_OR_NOT_TEL);
        params.addBodyParameter("money", "0.01");

        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_MY_GET_PAY, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.e("pay get请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json.length() == 0) {
                        } else {
                            Gson gson = new Gson();
                            mPayBean = gson.fromJson(json, LotteryAddPayBean.class);
                            if (mPayBean == null) {
                                Log.d("LotteryFragment", "实体类为null");
                            } else if (mPayBean.getRes() == 10003) {
                                Toast.makeText(MoneyActivity.this, "用户不存在 ", Toast.LENGTH_SHORT).show();
                            } else if (mPayBean.getRes() == 10002) {
                                Toast.makeText(MoneyActivity.this, "生成充值订单失败", Toast.LENGTH_SHORT).show();
                            } else if (mPayBean.getRes() == 10001) {
                                Toast.makeText(MoneyActivity.this, "充值订单生成成功", Toast.LENGTH_SHORT).show();
                                data = mPayBean.getData();
                            }
                        }
                    }
                });
    }

   private IWXAPI api;
    private void pay() {
        api = WXAPIFactory.createWXAPI(this,null);
        api.registerApp(Constants.APP_ID);
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("id_recharge", data + "");
        httpUtils.send(HttpRequest.HttpMethod.POST, URLValue.URL_NOR + URLValue.URL_LOTTERY_PAY_PRE, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Log.i("请求失败", "3333333333333333333333 error: " + arg1.toString());
                    }
                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        Log.e("pay Sure请求成功", "111111111111111111111111111111 onSuccess" + arg0.result.toString());
                        String json = arg0.result.toString();
                        if (json != null && json.length() > 0) {
//                            String content = new String(json);
                            Gson gson = new Gson();
                            LotteryAddPayMsBean bean = gson.fromJson(json, LotteryAddPayMsBean.class);
                            PayReq request = new PayReq();
                            if (bean == null) {

                            } else if (bean.getRes() == 10001) {
                                if (null != bean && bean.getData() != null) {
                                    request.appId = bean.getData().getAppid();
                                    request.partnerId = bean.getData().getPartnerid();
                                    request.prepayId = bean.getData().getPrepayid();
                                    request.packageValue = bean.getData().getPackagestr();
                                    request.nonceStr = bean.getData().getNoncestr();
                                    request.timeStamp = bean.getData().getTimestamp() + "";
                                    request.sign = bean.getData().getSign();
                                    api.sendReq(request);

                                    Toast.makeText(MoneyActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d("PAY_GET", "返回错误");
                                    Toast.makeText(MoneyActivity.this, "返回错误", Toast.LENGTH_SHORT).show();
                                }
                            } else if (bean.getRes() == 10002) {
                                Toast.makeText(MoneyActivity.this, "生成预付单失败", Toast.LENGTH_SHORT).show();
                            } else if (bean.getRes() == 10003) {
                                Toast.makeText(MoneyActivity.this, "充值订单不存在", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("PAY_GET", "服务器请求错误");
                                Toast.makeText(MoneyActivity.this, "服务器请求错误", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
//        try {
//            byte[] buf = Util.httpGet(url);
//            Log.d("PayActivity", "buf:" + buf);

//            if (buf != null && buf.length > 0) {
//                String content = new String(buf);
//                Log.e("get server pay params:", content);
//                JSONObject json = new JSONObject(content);
//                if (null != json && !json.has("retcode")) {
//                    PayReq request = new PayReq();
//                    request.appId = "wx63c88ed7e6190564";
//                    request.partnerId = "1900000109";
//                    request.prepayId = "1101000000140415649af9fc314aa427";
//                    request.packageValue = "Sign=WXPay";
//                    request.nonceStr = "1101000000140429eb40476f8896f4c9";
//                    request.timeStamp = "1398746574";
//                    request.sign = "c1a27813d84137e40538d2df93c800ff";
//                    api.sendReq(request);
//                    Toast.makeText(PaySureActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.d("PAY_GET", "返回错误" + json.getString("retmsg"));
//                    Toast.makeText(PaySureActivity.this, "返回错误" + json.getString("retmsg"), Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Log.d("PAY_GET", "服务器请求错误");
//                Toast.makeText(PaySureActivity.this, "服务器请求错误", Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//            Log.e("PAY_GET", "异常：" + e.getMessage());
//            Toast.makeText(PaySureActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }

}
