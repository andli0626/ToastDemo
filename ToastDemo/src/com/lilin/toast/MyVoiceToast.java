package com.lilin.toast;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 新数据Toast提示控件(带音乐播放)
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-8-30
 */
public class MyVoiceToast extends Toast {

	private MediaPlayer mPlayer;
	private boolean isSound;

	public MyVoiceToast(Context context) {
		this(context, false);
	}

	public MyVoiceToast(Context context, boolean isSound) {
		super(context);

		this.isSound = isSound;

		mPlayer = MediaPlayer.create(context, R.raw.newdatatoast);
		mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});

	}

	@Override
	public void show() {
		super.show();

		if (isSound) {
			mPlayer.start();
		}
	}

	/**
	 * 设置是否播放声音
	 */
	public void setIsSound(boolean isSound) {
		this.isSound = isSound;
	}

	/**
	 * 获取控件实例
	 * 
	 * @param context
	 * @param text
	 *            提示消息
	 * @param isSound
	 *            是否播放声音
	 * @return
	 */
	public static MyVoiceToast makeText(Context context, CharSequence text,
			boolean isSound) {
		MyVoiceToast result = new MyVoiceToast(context, isSound);

		LayoutInflater inflate = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		DisplayMetrics dm = context.getResources().getDisplayMetrics();

		View v = inflate.inflate(R.layout.toast_custom, null);
		v.setMinimumWidth(dm.widthPixels);// 设置控件最小宽度为手机屏幕宽度

		TextView tv = (TextView) v.findViewById(R.id.new_data_toast_message);
		tv.setText(text);

		result.setView(v);
		result.setDuration(600);
		result.setGravity(Gravity.TOP, 0, (int) (dm.density * 75));

		return result;
	}

	public static MyVoiceToast makeText(Context context, CharSequence text,
			boolean isSound, int imgid) {
		MyVoiceToast result = new MyVoiceToast(context, isSound);

		LayoutInflater inflate = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		DisplayMetrics dm = context.getResources().getDisplayMetrics();

		View v = inflate.inflate(R.layout.toast_custom, null);
		v.setMinimumWidth(dm.widthPixels);// 设置控件最小宽度为手机屏幕宽度

		TextView tv = (TextView) v.findViewById(R.id.new_data_toast_message);
		tv.setText(text);
		ImageView img = (ImageView) v.findViewById(R.id.tvImageToast);
		img.setBackgroundDrawable(context.getResources().getDrawable(imgid));

		result.setView(v);
		result.setDuration(600);
		result.setGravity(Gravity.TOP, 0, (int) (dm.density * 75));

		return result;
	}

}
