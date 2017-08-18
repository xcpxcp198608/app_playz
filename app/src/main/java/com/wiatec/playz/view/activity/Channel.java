package com.wiatec.playz.view.activity;

import com.wiatec.playz.pojo.ChannelInfo;

import java.util.List;

/**
 * channel
 */

public interface Channel extends Common {

    void loadChannel(boolean execute, List<ChannelInfo> channelInfoList);
    void loadFavorite(boolean execute, List<ChannelInfo> channelInfoList);
}
