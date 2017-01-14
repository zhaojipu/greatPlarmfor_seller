package com.wangku.library;

/**
 *
 * 内容摘要：公用配置类
 */
public class Config {

    /** Commons **/
    public static final boolean DEBUG = true;//Is debug model

	/** Photo Request code **/
	public static final int PICKDATA_FROM_GALLERY = 2001;
	public static final int PICKDATA_FROM_CAMERA = 2002;
	public static final int START_PHOTO_CROP = 2003;

	public static final int SOCKET_TIMEOUT_10S = 10 * 1000;// 10s请求超时
	public static final int SOCKET_TIMEOUT_30S = 30 * 1000;// 30s请求超时

}
