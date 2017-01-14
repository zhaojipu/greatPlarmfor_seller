package com.wangku.library.component;

import com.wangku.library.app.App;

import java.io.File;

public class Constants {


    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_NET_CACHE = PATH_DATA + "/NetCache";
    public static final String PATH_PIC_CACHE = PATH_DATA + "/PicCache";


}
