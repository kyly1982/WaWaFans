package com.aries.wawafans.wxapi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.aries.wawafans.Bean.PayOrder;
import com.aries.wawafans.Bean.ResponseBean;
import com.aries.wawafans.Bean.ResultState;
import com.aries.wawafans.UI.RechargeActivity;
import com.aries.wawafans.UTILS.Net;
import com.aries.wawafans.UTILS.StaticUtil;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import java.util.Hashtable;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler, Net.PayCallBack {
	private static final int STATUS_FLAG = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		StaticUtil.wx_api.handleIntent(getIntent(),this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		StaticUtil.wx_api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			int code = resp.errCode;
			switch (code){
				case 0:
					Toast.makeText(getApplicationContext(),"支付回调成功",Toast.LENGTH_SHORT).show();
					payStatusQuery();
					break;
				case -1:
					Toast.makeText(getApplicationContext(),"支付出现错误。请重新支付",Toast.LENGTH_SHORT).show();
					//错误
					break;
				case -2:
					//用户取消
					break;
			}
		}
	}

	private void payStatusQuery(){
		Net net = new Net();
		Hashtable<String,String> params = new Hashtable<String,String>();
		params.put("outTradeNo", RechargeActivity.outTradeNo);
		net.httpPost(Net.order_status_url,STATUS_FLAG,params,this);
	}

	@Override
	public void onSuccess(int type, String data) {
		Gson gson= new Gson();
		if(data!=null&&data.length()!=0){
			ResponseBean responseBean = gson.fromJson(data,ResponseBean.class);
			if(responseBean.getStatus()== ResultState.OK){
				switch (type){
					case STATUS_FLAG:
						PayOrder payOrder = gson.fromJson(responseBean.getDataObject(),PayOrder.class);
						if(payOrder.getOrderStatus()==1){
							Toast.makeText(WXPayEntryActivity.this,payOrder.getPayCoin(), Toast.LENGTH_SHORT).show();
						}
						break;
				}
			}else{
				Toast.makeText(WXPayEntryActivity.this, responseBean.getMsg(), Toast.LENGTH_SHORT).show();
			}
		}else{
			Toast.makeText(WXPayEntryActivity.this, "服务器响应数据为空", Toast.LENGTH_SHORT).show();
		}
		finish();
	}

	@Override
	public void onFail(int type, String msg) {

	}
}