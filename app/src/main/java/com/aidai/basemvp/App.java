package com.aidai.basemvp;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.basemvp
 * @Description: (用一句话描述该文件做什么)
 * @date 2016/10/20 14:26
 */

public class App extends Application{

	private static App sApp;

	private static Context mContext;

	public long mTimeCount;

	public String mCurrentPhone;

	public static App getInstance() {
		if (null == sApp) {
			sApp = new App();
		}
		return sApp;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		mContext = getApplicationContext();
		//图片加载初始化
		Fresco.initialize(this);


	}

	public static Context getContext() {
		return mContext;
	}

}
