package com.wiatec.playz.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.px.common.adapter.BaseRecycleAdapter;
import com.px.common.animator.Zoom;
import com.wiatec.playz.R;
import com.wiatec.playz.adapter.ChannelType2Adapter;
import com.wiatec.playz.databinding.ActivityChannelType2Binding;
import com.wiatec.playz.instance.Constant;
import com.wiatec.playz.pojo.ChannelType2Info;
import com.wiatec.playz.presenter.ChannelType2Presenter;

import java.util.List;

public class ChannelTypeActivity2 extends BaseActivity<ChannelType2Presenter> implements ChannelType2 {

    private ActivityChannelType2Binding binding;

    @Override
    protected ChannelType2Presenter createPresenter() {
        return new ChannelType2Presenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_channel_type2);
        String type = getIntent().getStringExtra("type");
        presenter.loadChannelType2(type);
    }

    @Override
    public void loadChannelType2(boolean execute, final List<ChannelType2Info> channelType2InfoList) {
        if(!execute){
            binding.pbLoading.setVisibility(View.GONE);
            binding.tvLoading.setText(getString(R.string.data_load_error));
            binding.btRetry.setVisibility(View.VISIBLE);
            binding.btRetry.requestFocus();
            return;
        }
        binding.llLoading.setVisibility(View.GONE);
        ChannelType2Adapter channelType2Adapter = new ChannelType2Adapter(channelType2InfoList);
        binding.rcvChannelType2.setAdapter(channelType2Adapter);
        binding.rcvChannelType2.setLayoutManager(new GridLayoutManager(this ,5));
        channelType2Adapter.setOnItemFocusListener(new BaseRecycleAdapter.OnItemFocusListener() {
            @Override
            public void onFocus(View view, int position, boolean hasFocus) {
                if(hasFocus){
                    Zoom.zoomIn10to11(view);
                }else{
                    Zoom.zoomOut11to10(view);
                }
            }
        });
        channelType2Adapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ChannelType2Info channelType2Info = channelType2InfoList.get(position);
                Intent intent = new Intent(ChannelTypeActivity2.this, ChannelActivity.class);
                intent.putExtra(Constant.key.channel_type, channelType2Info.getTag());
                startActivity(intent);
            }
        });
    }

}
