package com.wiatec.playz.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.px.common.http.HttpMaster;
import com.px.common.http.Listener.StringListener;
import com.px.common.utils.Logger;
import com.wiatec.playz.instance.Constant;
import com.wiatec.playz.pojo.ChannelType2Info;

import java.io.IOException;
import java.util.List;

/**
 * Created by patrick on 14/08/2017.
 * create time : 2:07 PM
 */

public class ChannelType2Provider implements LoadServiceWithParam<List<ChannelType2Info>> {
    @Override
    public void load(String param , final OnLoadListener<List<ChannelType2Info>> onLoadListener) {
        Logger.d(param);

    }
}
