package com.aidai.basemvp.base;

import android.content.Context;

import com.aidai.basemvp.utils.BToast;


/**
 * @author JokerX
 * @version V1.0
 * @Package com.example.jokerx.mvpsimple.base
 * @Description: 基础的实体
 * @date 2016/5/16 10:44
 */
public class BasePOJO {

	private String resultCode;

	private String message;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isResultSuccess() {
		return "200".equals(resultCode);
	}

	public void showMsg(Context context) {
		BToast.showShort(context, message);
	}
}
