package com.wiatec.playz.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.px.common.http.HttpMaster;
import com.px.common.http.Listener.StringListener;
import com.wiatec.playz.entity.ResultInfo;
import com.wiatec.playz.instance.Constant;
import com.wiatec.playz.pojo.ChannelInfo;
import com.wiatec.playz.sql.FavoriteChannelDao;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * channel provider
 */

public class ChannelProvider implements ChannelLoadService<List<ChannelInfo>> {

    @Inject
    public ChannelProvider() {

    }

    @Override
    public void load(String type, final OnLoadListener<List<ChannelInfo>> onLoadListener) {
        HttpMaster.post(Constant.url.channel)
                .parames("country", type)
                .enqueue(new StringListener() {
                    @Override
                    public void onSuccess(String s) throws IOException {
                        List<ChannelInfo> channelInfoList = new Gson().fromJson(s,
                                new TypeToken< List<ChannelInfo>>(){}.getType());
                        if(channelInfoList == null || channelInfoList.size() <= 0){
                            onLoadListener.onLoad(false, null);
                            return;
                        }
                        onLoadListener.onLoad(true, channelInfoList);
                    }

                    @Override
                    public void onFailure(String e) {
                        onLoadListener.onLoad(false, null);
                    }
                });
    }

    @Override
    public void loadFavorite(OnLoadListener<List<ChannelInfo>> onLoadListener) {
        FavoriteChannelDao favoriteChannelDao = FavoriteChannelDao.getInstance();
        List<ChannelInfo> channelInfoList = favoriteChannelDao.queryAll();
        if(channelInfoList == null || channelInfoList.size() <= 0){
            onLoadListener.onLoad(false, null);
            return;
        }
        onLoadListener.onLoad(true, channelInfoList);
    }
}
