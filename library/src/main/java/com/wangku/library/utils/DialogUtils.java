package com.wangku.library.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;


import com.wangku.library.R;

import java.util.Calendar;

/**
 *
 * 内容摘要：Dialog utils
 */
public class DialogUtils {

	private DialogUtils() {}

	public static AlertDialog createItemsDialog(Context context, int itemsId,
												DialogInterface.OnClickListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setItems(itemsId, listener);
		AlertDialog dialog = builder.create();
		return dialog;
	}

	public static DatePickerDialog createDatePickerDialog(Context context,
														  DatePickerDialog.OnDateSetListener listener) {
		Calendar calendar = Calendar.getInstance();
		DatePickerDialog dialog =
				new DatePickerDialog(context, listener, calendar.get(Calendar.YEAR),
						calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		return dialog;
	}

	public static AlertDialog createConfirmDialog(Context context, String title, String message,
												  DialogInterface.OnClickListener clickListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title).setMessage(message).setPositiveButton("确定", clickListener)
				.setNegativeButton("取消", clickListener);
		AlertDialog dialog = builder.create();
		return dialog;
	}
	public static AlertDialog createSingleButtonDialog(Context context, String title, String message,
													   DialogInterface.OnClickListener clickListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title).setMessage(message).setPositiveButton("确定", clickListener)
				;
		AlertDialog dialog = builder.create();
		return dialog;
	}
}
