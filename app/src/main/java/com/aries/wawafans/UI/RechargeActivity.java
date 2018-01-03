package com.aries.wawafans.UI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.aries.wawafans.Adapter.PriceAdapter;
import com.aries.wawafans.Bean.PayOrder;
import com.aries.wawafans.Bean.Price;
import com.aries.wawafans.Bean.ResponseBean;
import com.aries.wawafans.Bean.ResultState;
import com.aries.wawafans.R;
import com.aries.wawafans.UTILS.Net;
import com.aries.wawafans.UTILS.PayResult;
import com.aries.wawafans.UTILS.StaticUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class RechargeActivity extends BaseActivity implements View.OnClickListener,Net.PayCallBack{
    private RecyclerView priceList;
    private TextView coin;

    private ArrayList<Price> prices;
    private PriceAdapter adapter;

    private static final int SDK_PAY_FLAG = 1;


    private static final int PRICE_FLAG = 0;
    private static final int ALIPAY_FLAG = 1;
    private static final int WXPAY_FLAG = 2;
    private static final int STATUS_FLAG = 3;

    public static String outTradeNo = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        //创建微信api并注册到微信
        StaticUtil.wx_api = WXAPIFactory.createWXAPI(RechargeActivity.this, StaticUtil.WX_Appid, true);
        StaticUtil.wx_api.registerApp(StaticUtil.WX_Appid);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null == priceList) {
            initView();
        }
        showData();
    }

    private void initView() {
        priceList = findViewById(R.id.pricelist);
        coin = findViewById(R.id.coin);
        findViewById(R.id.wxpay).setOnClickListener(this);
        findViewById(R.id.alipay).setOnClickListener(this);


        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        priceList.setLayoutManager(manager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_menu);
    }

    private void loadData() {
/*        prices = new ArrayList<>(5);
        Price priceA = new Price(5,10000,188);
        Price priceB = new Price(4,5000,100);
        Price priceC = new Price(3,2000,50);
        Price priceD = new Price(2,1000,30);
        Price priceE = new Price(1,200,10);
        prices.add(priceA);
        prices.add(priceB);
        prices.add(priceC);
        prices.add(priceD);
        prices.add(priceE);*/
        Net net = new Net();
        net.httpPost(Net.price_url,PRICE_FLAG,null,this);
        showData();
    }

    private void showData() {
        if (null == prices) {
            loadData();
        } else {
            if (null == adapter) {
                adapter = new PriceAdapter(prices);
                priceList.setAdapter(adapter);
            } else {
                adapter.setData(prices);
            }
        }
    }

    //todo 支付，1为支付宝，0为微信
    private void pay(int type, Price price) {
        int user_id = application.getUser().getId();
        int price_id = price.getId();
        outTradeNo = ""+System.currentTimeMillis() + (long) (Math.random() * 10000000L);
        Hashtable<String,String> params = new Hashtable<String,String>();
        params.put("user_id",""+user_id);
        params.put("price_id",""+price_id);
        params.put("outTradeNo",outTradeNo);
        Net net = new Net();
        switch (type){
            case 0:
                params.put("channel","alipay");
                net.httpPost(Net.order_product_url,ALIPAY_FLAG,params,this);
                break;
            default:
                params.put("channel","wxpay");
                net.httpPost(Net.order_product_url,WXPAY_FLAG,params,this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Price price = adapter.getPrice();
        if (null != price) {
            switch (v.getId()) {
                case R.id.wxpay:
                    pay(0, price);
                    break;
                case R.id.alipay:
                    pay(1, price);
                    break;
            }
        } else {
            showMessage("未选择充值金额！");
        }
    }

    public void payStatusQuery(){
        Net net = new Net();
        Hashtable<String,String> params = new Hashtable<String,String>();
        params.put("outTradeNo",outTradeNo);
        net.httpPost(Net.order_status_url,STATUS_FLAG,params,this);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(RechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        payStatusQuery();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(RechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        payStatusQuery();
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    @Override
    public void onSuccess(int type,String data) {
        Gson gson= new Gson();
        if(data!=null&&data.length()!=0){
            ResponseBean responseBean = gson.fromJson(data,ResponseBean.class);
            if(responseBean.getStatus()== ResultState.OK){
                switch (type){
                    case PRICE_FLAG:
                        Type price_type = new TypeToken<ArrayList<Price>>(){}.getType();
                        prices = gson.fromJson(responseBean.getDataObject(),price_type);
                        showData();
                        break;
                    case ALIPAY_FLAG:
                        final String orderinfo = responseBean.getDataObject();
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(RechargeActivity.this);
                                String result = alipay.pay(orderinfo,true);
                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };
                        Thread payThread = new Thread(runnable);
                        payThread.start();
                        break;
                    case WXPAY_FLAG:
                        if(StaticUtil.wx_api!=null&&StaticUtil.wx_api.isWXAppInstalled()){
                            Type wx_type = new TypeToken<Map<String,String>>(){}.getType();
                            Map<String,String> paramObject = gson.fromJson(responseBean.getDataObject(),wx_type);
                            PayReq payReq = new PayReq();
                            payReq.appId = paramObject.get("appid");
                            payReq.partnerId = paramObject.get("partnerid");
                            payReq.prepayId= paramObject.get("prepayid");
                            payReq.packageValue = "Sign=WXPay";
                            payReq.nonceStr= paramObject.get("nonceStr");
                            payReq.timeStamp= paramObject.get("timeStamp");
                            payReq.sign= paramObject.get("sign");
                            StaticUtil.wx_api.sendReq(payReq);
                        }else{
                            Toast.makeText(getApplicationContext(),"未安装微信,请下载最新微信",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case STATUS_FLAG:
                        PayOrder payOrder = gson.fromJson(responseBean.getDataObject(),PayOrder.class);
                        if(payOrder.getOrderStatus()==1){
                            Toast.makeText(RechargeActivity.this,payOrder.getPayCoin(), Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }else{
                Toast.makeText(RechargeActivity.this, responseBean.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(RechargeActivity.this, "服务器响应数据为空", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onFail(int type, String msg) {

    }
}
