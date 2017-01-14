package com.wangku.library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 内容摘要：配置文件工具类
 */
public class SharedPreferencesUtils {

    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";

    private static final String PREFERENCE_NAME = "WK_MOBILE";

    public static SharedPreferencesUtils instance;
    public SharedPreferences sharedPreferences;

    private SharedPreferencesUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Single instance.
     */
    public static SharedPreferencesUtils getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesUtils(context);
        }
        return instance;
    }

    public void putValue(String key, Object value) {
        Editor editor = sharedPreferences.edit();
        if (value == null || value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        }
        editor.commit();
    }

    public <T extends Object> Object getValue(String key, Class<T> cls) {
        if (cls.equals(String.class)) {
            String returnValue = sharedPreferences.getString(key, "");
            return returnValue;
        } else if (cls.equals(Boolean.class)) {
            return sharedPreferences.getBoolean(key, false);
        } else if (cls.equals(Integer.class)) {
            return sharedPreferences.getInt(key, 0);
        }else if(cls.equals(Long.class)){
            return sharedPreferences.getLong(key, 0L);
        }
        return null;
    }

    public String getStringValue(String key) {
        String value = (String) getValue(key, String.class);
        return value;
    }

    public int getIntegerValue(String key) {
        int value = (int) getValue(key, Integer.class);
        return value;
    }

    public int getIntegerValue(String key, int defaultValue) {
        int value = sharedPreferences.getInt(key, defaultValue);
        return value;
    }

    public String getStringValue(String key, String defaultValue) {
        String value = sharedPreferences.getString(key, defaultValue);
        return value;
    }

    public int getWidth() {
        return (Integer) getValue(WIDTH, Integer.class);
    }

    public int getHeight() {
        return (Integer) getValue(HEIGHT, Integer.class);
    }

    /**
     * 清除配置文件
     *
     * @author 陶玉亮
     */
    public void clearPreferences() {
        if (sharedPreferences != null) {
            Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }
}
