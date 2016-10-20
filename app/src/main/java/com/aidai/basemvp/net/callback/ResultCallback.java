package com.aidai.basemvp.net.callback;

import android.content.Context;

import com.aidai.basemvp.App;
import com.aidai.basemvp.utils.BToast;
import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by ChristLu on 2015/12/21.
 */
public abstract class ResultCallback<T> {
	public Type mType;

	private Context context;

	public ResultCallback() {
		mType = getSuperclassTypeParameter(getClass());
	}


	static Type getSuperclassTypeParameter(Class<?> subclass) {
		Type superclass = subclass.getGenericSuperclass();
		if (superclass instanceof Class) {
			throw new RuntimeException("Missing type parameter.");
		}
		ParameterizedType parameterized = (ParameterizedType) superclass;
		return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
	}

	//请求成功后的回调
	public void onResponse(T response) {

	}

	public void onError(Request request, Exception e) {
		//网络请求错误
		BToast.showShort(App.getInstance().getContext(), "数据异常,请确保网络通畅");
	}

	public void onNetError(Request request, Exception e) {
		onError(request, e);
//        BToast.showShort(App.getContext(), "网络异常,请重试");
	}

	//检测接口是否存在
	public void checkInterfaceExist(Request request, Exception e) {
		//检测接口是否存在

	}


	public void cancel() {

	}


}
