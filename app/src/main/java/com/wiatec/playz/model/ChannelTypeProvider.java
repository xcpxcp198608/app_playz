package com.wiatec.playz.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.px.common.http.HttpMaster;
import com.px.common.http.Listener.StringListener;
import com.wiatec.playz.entity.ResultInfo;
import com.wiatec.playz.instance.Constant;
import com.wiatec.playz.pojo.ChannelTypeInfo;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * channel type data model
 */

public class ChannelTypeProvider implements LoadServiceWithParam<List<ChannelTypeInfo>>{

    @Inject
    public ChannelTypeProvider() {
    }

    @Override
    public void load(String param, final OnLoadListener<List<ChannelTypeInfo>> onLoadListener) {
        String url ;
        if(Constant.key.online.equals(param)) {
            url = Constant.url.channel_type;
        }else if(Constant.key.playz.equals(param)) {
            url = Constant.url.playz_type;
        }else{
            url = Constant.url.channel_type;
        }
        HttpMaster.get(url)
                .enqueue(new StringListener() {
                    @Override
                    public void onSuccess(String s) throws IOException {
                        List<ChannelTypeInfo> channelTypeInfoList = new Gson().fromJson(s,
                                new TypeToken<List<ChannelTypeInfo>>() {
                                }.getType());
                        if (channelTypeInfoList == null || channelTypeInfoList.size() <= 0) {
                            onLoadListener.onLoad(false, null);
                            return;
                        }
                        onLoadListener.onLoad(true, channelTypeInfoList);
                    }

                    @Override
                    public void onFailure(String e) {
                        onLoadListener.onLoad(false, null);
                    }
                });

    }
}
