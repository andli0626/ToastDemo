package com.lilin.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * 
 * @author lilin
 * @date 2013-1-21 下午11:36:17
 * @annotation Toast帮助类
 */
public class ToastHelp {

	// 自定义
	public static void showCustomToast(Context con, View view) {

		Toast toast = new Toast(con);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(view);
		toast.show();
	}

	public static void showWarningVoiceToast(Context con, String msg, boolean is) {
		MyVoiceToast.makeText(con, msg, is, R.drawable.toast_warning).show();
	}

	public static void showSadVoiceToast(Context con, String msg, boolean is) {
		MyVoiceToast.makeText(con, msg, is, R.drawable.toast_sad).show();
	}

	public static void showRightVoiceToast(Context con, String msg, boolean is) {
		MyVoiceToast.makeText(con, msg, is, R.drawable.toast_right).show();
	}

	public static void showVoiceToast(Context con, String msg, boolean is,
			int imgid) {
		MyVoiceToast.makeText(con, msg, is, imgid).show();
	}

}
