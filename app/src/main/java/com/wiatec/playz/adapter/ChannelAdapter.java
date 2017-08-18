package com.wiatec.playz.adapter;

import android.view.View;

import com.px.common.adapter.BaseRecycleAdapter;
import com.px.common.image.ImageMaster;
import com.wiatec.playz.R;
import com.wiatec.playz.pojo.ChannelInfo;

import java.util.List;

/**
 * channel adapter
 */

public class ChannelAdapter extends BaseRecycleAdapter<ChannelViewHolder> {

    private List<ChannelInfo> channelInfoList;

    public ChannelAdapter(List<ChannelInfo> channelInfoList) {
        this.channelInfoList = channelInfoList;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.item_rcv_channel;
    }

    @Override
    protected ChannelViewHolder createHolder(View view) {
        return new ChannelViewHolder(view);
    }

    @Override
    protected void bindHolder(final ChannelViewHolder holder, final int position) {
        ChannelInfo channelInfo = channelInfoList.get(position);
        holder.textView.setText(channelInfo.getName());
        ImageMaster.load(channelInfo.getIcon(), holder.imageView, R.drawable.hold2,
                R.drawable.hold2);
    }

    @Override
    public int getItemCounts() {
        return channelInfoList.size();
    }
}
