package com.wiatec.playz.view.activity;

import com.wiatec.playz.pojo.ChannelTypeInfo;

import java.util.List;

/**
 * channel type
 */

public interface ChannelType extends Common {

    void loadChannelType(boolean execute, List<ChannelTypeInfo> channelTypeInfoList);
}
