package com.wiatec.playz.presenter;

import com.wiatec.playz.model.AdImageProvider;
import com.wiatec.playz.model.ChannelTypeProvider;
import com.wiatec.playz.model.LoadService;
import com.wiatec.playz.model.LoadServiceWithParam;
import com.wiatec.playz.pojo.ChannelTypeInfo;
import com.wiatec.playz.pojo.ImageInfo;
import com.wiatec.playz.view.activity.ChannelType;

import java.util.List;

/**
 * channel type presenter
 */

public class ChannelTypePresenter extends BasePresenter<ChannelType> {

    private ChannelType channelType;
    AdImageProvider adImageProvider;
    ChannelTypeProvider channelTypeProvider;


    public ChannelTypePresenter(ChannelType channelType) {
        this.channelType = channelType;
        adImageProvider = new AdImageProvider();
        channelTypeProvider = new ChannelTypeProvider();
    }

    public void loadAdImage(){
        if(adImageProvider != null){
            adImageProvider.load(new LoadService.OnLoadListener<ImageInfo>() {
                @Override
                public void onLoad(boolean execute, ImageInfo imageInfo) {
                    channelType.loadAdImage(execute, imageInfo);
                }
            });
        }
    }

    public void loadChannelType(String type){
        if(channelTypeProvider != null){
            channelTypeProvider.load(type , new LoadServiceWithParam.OnLoadListener<List<ChannelTypeInfo>>() {
                @Override
                public void onLoad(boolean execute, List<ChannelTypeInfo> channelTypeInfoList) {
                    channelType.loadChannelType(execute , channelTypeInfoList);
                }
            });
        }
    }
}
