package com.wiatec.playz.presenter;

import com.wiatec.playz.model.AdImageProvider;
import com.wiatec.playz.model.ChannelLoadService;
import com.wiatec.playz.model.ChannelProvider;
import com.wiatec.playz.model.LoadService;
import com.wiatec.playz.pojo.ChannelInfo;
import com.wiatec.playz.pojo.ImageInfo;
import com.wiatec.playz.view.activity.Channel;

import java.util.List;

/**
 * channel presenter
 */

public class ChannelPresenter extends BasePresenter {

    private Channel channel;
    AdImageProvider adImageProvider;
    ChannelProvider channelProvider;

    public ChannelPresenter(Channel channel) {
        this.channel = channel;
        adImageProvider= new AdImageProvider();
        channelProvider = new ChannelProvider();
    }

    //调用model - loadService 获取需要的Image文件
    public void loadAdImage(){
        if(adImageProvider != null){
            adImageProvider.load(new LoadService.OnLoadListener<ImageInfo>() {
                @Override
                public void onLoad(boolean execute, ImageInfo imageInfo) {
                    channel.loadAdImage(execute, imageInfo);
                }
            });
        }
    }

    //调用model - channelLoadService 获取需要的Image文件
    public void loadChannel(String type){
        if(channelProvider != null){
            channelProvider.load(type, new ChannelLoadService.OnLoadListener<List<ChannelInfo>>() {
                @Override
                public void onLoad(boolean execute, List<ChannelInfo> channelInfos) {
                    channel.loadChannel(execute, channelInfos);
                }
            });
        }
    }

    public void loadFavorite(){
        if(channelProvider != null){
            channelProvider.loadFavorite(new ChannelLoadService.OnLoadListener<List<ChannelInfo>>() {
                @Override
                public void onLoad(boolean execute, List<ChannelInfo> channelInfos) {
                    channel.loadFavorite(execute, channelInfos);
                }
            });
        }
    }
}
