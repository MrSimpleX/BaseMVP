package com.aidai.basemvp.utils;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.utils
 * @Description: 图片加载工具类
 * @date 2016/9/13 17:06
 */

public class ImageLoadUtil {

	/**
	 * 加载图片
	 *
	 * @param simpleDraweeView 图片载体
	 * @param url 图片url
	 */
	public static void loadImage(SimpleDraweeView simpleDraweeView, String url) {
		if (url.endsWith(".gif")) {
			DraweeController draweeController;
			draweeController = Fresco.newDraweeControllerBuilder()
				.setAutoPlayAnimations(true)
				.setUri(Uri.parse(url))//设置uri
				.build();
			//设置Controller
			simpleDraweeView.setController(draweeController);
		} else {
			Uri uri = Uri.parse(url);
			simpleDraweeView.setImageURI(uri);
		}
	}

}
