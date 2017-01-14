package com.wangku.library.utils;

import android.content.Context;
import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Utils {
	
	private static final String MONEY_REGULAR = "\\d+(\\.\\d+)?";
	
	/**
	 * 格式化金额		
	 * @param s
	 * @param len
	 * @return
	 */
	public static String formatMoney(Double s, int len) {
		if (s == null) {
			return "";
		}
		NumberFormat formater = null;

		if (len == 0) {
			formater = new DecimalFormat("###,###");
		} else {
			StringBuffer buff = new StringBuffer();
			buff.append("###,###.");
			for (int i = 0; i < len; i++) {
				buff.append("#");
			}
			formater = new DecimalFormat(buff.toString());
		}
		String result = formater.format(s);
		if(result.indexOf(".") == -1)
		{
			result = "￥" + result + ".00";
		}
		else
		{
			result = "￥" + result;
		}
		return result;
	}
	
	
	/*格式化金额为两位小数点*/
	public static String moneyFormat(double val) {
	    DecimalFormat df = new DecimalFormat("#.00");
	    String res = df.format(val);
	    if(".00".equals(res)){
	    	res = "0.00";
	    } else if (Double.parseDouble(res) < 1.00&& Double.parseDouble(res) > 0.00) {
			res = 0 + res;
		}
	    return res;
	}
	/*格式化小数点后一位*/
	public static String toInt(double val) {
		DecimalFormat df = new DecimalFormat("#.0");
		String res=df.format(val);
		return res;
	}

	/*给整数加括号（商品数量）*/
	public static String numFormat(int val){
	    String res = "（"+val+"）";
	    return res;
	}
	
	/*格式化分期数*/
	public static String intFormat(int val){
	    String res = ""+val+"期";
	    return res;
	}
	
	public static String moneyFormat(String val) {
		if (!TextUtils.isEmpty(val) && val.matches(MONEY_REGULAR) ) {
			return moneyFormat(Double.parseDouble(val));
		}
		return "0.00";
	}
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
    /**
     *double保留1位
     */
    public static String doubleFormat(double val){
        BigDecimal mData = new BigDecimal(val).setScale(1, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(mData);
    }
    /**
     *String说保留1位
     */
    public static String tringFormat(String val){
        BigDecimal mData = new BigDecimal(val).setScale(1, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(mData);
    }
}
