package com.aidai.basemvp.api;


import com.aidai.basemvp.net.OkHttpClientManager;
import com.aidai.basemvp.net.callback.ResultCallback;

import java.util.Map;

/**
 * Created by ChristLu on 2015/12/21.
 */
public class BaseAPI {

	public static final String HTTP_SUFFIX = ".html";

	public void exeutePostMethods(ResultCallback<?> callBack, String url, Map<String, String> params) {
		OkHttpClientManager.postAsyn(url, callBack, params);
	}

	public void exeuteGetMethods(ResultCallback<?> callBack, String url) {
		OkHttpClientManager.getAsyn(url, callBack);
	}

}
