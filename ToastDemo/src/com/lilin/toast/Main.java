package com.lilin.toast;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener {
	Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViewById(R.id.btnSimpleToast).setOnClickListener(this);
		findViewById(R.id.btnSimpleToastWithCustomPosition).setOnClickListener(
				this);
		findViewById(R.id.btnCustomToast).setOnClickListener(this);
		findViewById(R.id.btnRunToastFromOtherThread).setOnClickListener(this);

	}

	public void showToast() {
		handler.post(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), "我来自其他线程！",
						Toast.LENGTH_LONG).show();

			}
		});
	}

	@Override
	public void onClick(View v) {
		Toast toast = null;
		DisplayMetrics dm = getResources().getDisplayMetrics();
		switch (v.getId()) {
		// 通过继承Toast，实现自定义
		case R.id.btnSimpleToast:
			ToastHelp.showRightVoiceToast(this, "数据加载成功！", true);
			break;
		// 自定义位置Toast
		case R.id.btnSimpleToastWithCustomPosition:
			toast = Toast.makeText(getApplicationContext(), "自定义显示位置:顶部显示",
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.TOP, 0, (int) (dm.density * 75));
			toast.show();
			break;
		// 完全自定义Toast
		case R.id.btnCustomToast:

			LayoutInflater inflater = getLayoutInflater();
			View view = inflater.inflate(R.layout.toast_custom, null);

			ImageView image = (ImageView) view.findViewById(R.id.tvImageToast);
			image.setImageResource(R.drawable.toast_sad);

			TextView title = (TextView) view
					.findViewById(R.id.new_data_toast_message);
			title.setText("加载失败");

			view.setMinimumWidth(dm.widthPixels);// 设置控件最小宽度为手机屏幕宽度
			ToastHelp.showCustomToast(this, view);
			break;

		// 其他线程
		case R.id.btnRunToastFromOtherThread:
			new Thread(new Runnable() {
				public void run() {
					showToast();
				}
			}).start();
			break;

		}

	}
}