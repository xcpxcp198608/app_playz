package com.wiatec.playz.adapter;

import android.view.View;

import com.px.common.adapter.BaseRecycleAdapter;
import com.px.common.image.ImageMaster;
import com.wiatec.playz.R;
import com.wiatec.playz.pojo.ChannelTypeInfo;

import java.util.List;

/**
 * channel type adapter
 */

public class ChannelTypeAdapter extends BaseRecycleAdapter<ChannelTypeViewHolder> {

    private List<ChannelTypeInfo> channelTypeInfoList;

    public ChannelTypeAdapter(List<ChannelTypeInfo> channelTypeInfoList) {
        this.channelTypeInfoList = channelTypeInfoList;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.item_rcv_channel_type;
    }

    @Override
    protected ChannelTypeViewHolder createHolder(View view) {
        return new ChannelTypeViewHolder(view);
    }

    @Override
    protected void bindHolder(ChannelTypeViewHolder holder, int position) {
        ChannelTypeInfo channelTypeInfo = channelTypeInfoList.get(position);
        ImageMaster.load(channelTypeInfo.getIcon(),holder.imageView, R.drawable.hold1 ,
                R.drawable.hold1);
        holder.textView.setText(channelTypeInfo.getName());
    }

    @Override
    public int getItemCounts() {
        return channelTypeInfoList.size();
    }
}
