package com.wiatec.playz.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.px.common.adapter.BaseRecycleAdapter;
import com.px.common.animator.Zoom;
import com.wiatec.playz.R;
import com.wiatec.playz.adapter.ChannelType1Adapter;
import com.wiatec.playz.databinding.ActivityChannelType1Binding;
import com.wiatec.playz.pojo.ChannelType1Info;
import com.wiatec.playz.presenter.ChannelType1Presenter;

import java.util.List;

public class ChannelTypeActivity1 extends BaseActivity<ChannelType1Presenter> implements ChannelType1 {

    private ActivityChannelType1Binding binding;

    @Override
    protected ChannelType1Presenter createPresenter() {
        return new ChannelType1Presenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_channel_type1);
        String type = getIntent().getStringExtra("type");
        presenter.loadChannelType1(type);
    }

    @Override
    public void loadChannelType1(boolean execute, final List<ChannelType1Info> channelType1InfoList) {
        if(!execute){
            binding.pbLoading.setVisibility(View.GONE);
            binding.tvLoading.setText(getString(R.string.data_load_error));
            binding.btRetry.setVisibility(View.VISIBLE);
            binding.btRetry.requestFocus();
            return;
        }
        binding.llLoading.setVisibility(View.GONE);
        ChannelType1Adapter channelType1Adapter = new ChannelType1Adapter(channelType1InfoList);
        binding.rcvChannelType1.setAdapter(channelType1Adapter);
        binding.rcvChannelType1.setLayoutManager(new GridLayoutManager(this ,5));
        channelType1Adapter.setOnItemFocusListener(new BaseRecycleAdapter.OnItemFocusListener() {
            @Override
            public void onFocus(View view, int position, boolean hasFocus) {
                if(hasFocus){
                    Zoom.zoomIn10to11(view);
                }else{
                    Zoom.zoomOut11to10(view);
                }
            }
        });
        channelType1Adapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ChannelType1Info channelType1Info = channelType1InfoList.get(position);
                Intent intent = new Intent(ChannelTypeActivity1.this, ChannelTypeActivity2.class);
                intent.putExtra("type", channelType1Info.getTag());
                startActivity(intent);
            }
        });
    }

}
