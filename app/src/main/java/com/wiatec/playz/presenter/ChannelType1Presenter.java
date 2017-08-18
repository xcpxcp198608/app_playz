package com.wiatec.playz.presenter;

import com.wiatec.playz.model.ChannelType1Provider;
import com.wiatec.playz.model.LoadServiceWithParam;
import com.wiatec.playz.pojo.ChannelType1Info;
import com.wiatec.playz.view.activity.ChannelType1;

import java.util.List;

/**
 * Created by patrick on 14/08/2017.
 * create time : 2:10 PM
 */

public class ChannelType1Presenter extends BasePresenter<ChannelType1> {

    private ChannelType1 channelType1;
    private ChannelType1Provider channelType1Provider;

    public ChannelType1Presenter(ChannelType1 channelType1) {
        this.channelType1 = channelType1;
        channelType1Provider = new ChannelType1Provider();
    }

    public void loadChannelType1(String type){
        channelType1Provider.load(type , new LoadServiceWithParam.OnLoadListener<List<ChannelType1Info>>() {
            @Override
            public void onLoad(boolean execute, List<ChannelType1Info> channelType1InfoList) {
                channelType1.loadChannelType1(execute, channelType1InfoList);
            }
        });
    }

}
