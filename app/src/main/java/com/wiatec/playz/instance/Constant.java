package com.wiatec.playz.instance;

import android.os.Environment;

/**
 * constant
 */

public final class Constant {

    public static final class url{
        static final String base = "http://www.ldlegacy.com:8080/playz/";
        public static final String ad_image = base+"adimage/";
        public static final String channel = base+"channel/";
        public static final String channel_type = base+"channeltype/";
        public static final String playz_type = base+"playztype/";
        public static final String upgrade = base+"upgrade/";
    }

    public static final class path{
        public static final String ad_video = Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/Android/data/com.wiatec.btv_launcher/files/download/btvad.mp4";
    }

    public static final class packageName{
        public static final String market = "com.px.bmarket";
        public static final String btv = "org.xbmc.kodi";
        public static final String tv_house = "com.fanshi.tvvideo";
        public static final String terrarium_tv = "com.nitroxenon.terrarium";
        public static final String popcom = "pct.droid";
        public static final String settings = "com.android.tv.settings";
    }

    public static final class key{
        public static final String channel_type = "channelType";
        public static final String type_favorite = "FAVORITE";
        public static final String playz = "playz";
        public static final String online = "online";
    }
}
