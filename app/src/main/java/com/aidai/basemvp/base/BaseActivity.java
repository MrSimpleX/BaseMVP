package com.aidai.basemvp.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aidai.basemvp.R;
import com.aidai.basemvp.utils.TUtil;
import com.aidai.basemvp.utils.ViewFindUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import kr.co.namee.permissiongen.PermissionGen;


/**
 * @author JokerX
 * @version V1.0
 * @Package com.aidai.aidaimvp.base
 * @Description: Activity基类
 * @date 2016/5/18 10:03
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity implements BaseView {

	private ImageView mIvLeft;
	private TextView mTvTitle;
	private TextView mTvRight;
	private ImageView mIvRight;
	private RelativeLayout mRlTitle;
	private FrameLayout mContent;
	private View mView;
	public T mPresenter;
	public E mModel;
	private static List<Activity> sActivities = new ArrayList<>();
	public Context mContext;
	private Handler mHandler;
	private static final int REQUEST_CODE_SCAN = 0x0000;
	private static final String DECODED_CONTENT_KEY = "codedContent";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_base);
		this.initView();
		this.initPermission();
		this.initPresenter();
	}

	private void initView() {
		mView = getWindow().getDecorView();
		mIvLeft = ViewFindUtils.find(mView, R.id.iv_left);
		mTvTitle = ViewFindUtils.find(mView, R.id.tv_title);
		mTvRight = ViewFindUtils.find(mView, R.id.tv_title);
		mIvRight = ViewFindUtils.find(mView, R.id.iv_right);
		mContent = ViewFindUtils.find(mView, R.id.content);
		sActivities.add(this);
		mContext = this;
		mPresenter = TUtil.getT(this, 0);
		mModel = TUtil.getT(this, 1);
		this.initPresenter();
		//设置返回按钮默认显示
		setLeftButtonVisible(View.VISIBLE);
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				BaseActivity.finishAll();
			}
		};

	}

	/**
	 * 初始化相关权限（Android 6.0 +）
	 */
	private void initPermission() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			PermissionGen.needPermission(this, 100,
				new String[]{
					Manifest.permission.READ_CONTACTS,
					Manifest.permission.RECEIVE_SMS,
					Manifest.permission.WRITE_CONTACTS,
					Manifest.permission.CAMERA,
					Manifest.permission.VIBRATE,
					Manifest.permission.CALL_PHONE
				}
			);
		}
	}

	/**
	 * 设置内容标题
	 *
	 * @param title 用户设置的标题
	 */
	public void setMainTitle(String title) {
		mTvTitle.setText(title);
	}

	/**
	 * 设置主内容区域
	 *
	 * @param resId 主资源文件Id
	 */
	public void setMainContentView(int resId) {
		FrameLayout frameLayout = (FrameLayout) findViewById(R.id.content);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(resId, null);
		if (null != v) {
			frameLayout.addView(v);
		}
	}

	/**
	 * 设置左边图标
	 *
	 * @param visible 是否显示
	 */
	public void setLeftButtonVisible(int visible) {
		mIvLeft.setVisibility(visible);
		mIvLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	/**
	 * 设置右边控件相关属性
	 *
	 * @param visable         显示状态
	 * @param resId           资源Id
	 * @param isText          显示种类
	 * @param contextStr      文字内容
	 * @param onClickListener 点击事件
	 */
	public void setRightResState(int visable, int resId, boolean isText,
															 String contextStr, View.OnClickListener onClickListener) {
		if (isText) {
			mTvRight.setVisibility(visable);
			mTvRight.setText(contextStr);
			mTvRight.setOnClickListener(onClickListener);
		} else {
			mIvRight.setVisibility(visable);
			mIvRight.setImageResource(resId);
			mIvRight.setOnClickListener(onClickListener);
		}
	}

	/**
	 * 静态方法结束所有Activity
	 */
	public static void finishAll() {
		for (Activity a : sActivities) {
			if (a instanceof Activity && !a.isFinishing()) {
				a.finish();
				a.overridePendingTransition(0, 0);
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mPresenter != null) {
			mPresenter.onDestroy();
		}
		ButterKnife.unbind(this);
		sActivities.remove(this);
	}

	/**
	 * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
	 */
	public abstract void initPresenter();

}
