package com.wiatec.playz.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.px.common.image.ImageMaster;
import com.px.common.utils.AppUtil;
import com.px.common.utils.Logger;
import com.wiatec.playz.R;
import com.wiatec.playz.adapter.MainViewPagerAdapter;
import com.wiatec.playz.adapter.MainViewPagerTransform;
import com.wiatec.playz.databinding.ActivityMainBinding;
import com.wiatec.playz.instance.Constant;
import com.wiatec.playz.pojo.ImageInfo;
import com.wiatec.playz.pojo.UpgradeInfo;
import com.wiatec.playz.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<MainPresenter> implements Main {

    private ActivityMainBinding binding;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.ibtSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.launchApp(MainActivity.this, Constant.packageName.settings);
            }
        });
//        presenter.loadAdImage();
        initViewPager();
    }

    private List<View> createViewList() {
        List<View> viewList = new ArrayList<>();
        viewList.add(LayoutInflater.from(this).inflate(R.layout.item_view_online, binding.viewPager, false));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.item_view_playz, binding.viewPager, false));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.item_view_movies, binding.viewPager, false));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.item_view_online, binding.viewPager, false));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.item_view_playz, binding.viewPager, false));
        return viewList;
    }

    private void initViewPager(){
        binding.viewPager.setOffscreenPageLimit(5);
        binding.viewPager.setPageMargin(100);
        binding.viewPager.setPageTransformer(true, new MainViewPagerTransform());
        final List<View> viewList = createViewList();
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(viewList);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setCurrentItem(1);
        adapter.setOnItemPageClick(new MainViewPagerAdapter.OnItemPageClick() {
            @Override
            public void onClick(View view, int position) {
               launchShortcut(position);
            }
        });
        binding.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                presenter.loadAdImage();
                if(position == 0){
                    binding.viewPager.setCurrentItem(viewList.size()-2);
                }else if(position == viewList.size() -1){
                    binding.viewPager.setCurrentItem(1);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.checkUpgrade();
    }

    @Override
    public void checkUpgrade(boolean upgrade, UpgradeInfo upgradeInfo) {
        if(upgrade){
            showUpgradeDialog(upgradeInfo);
        }
    }

    /**
     * 显示app upgrade 对话框
     * @param upgradeInfo upgrade info
     */
    private void showUpgradeDialog(final UpgradeInfo upgradeInfo) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        Window window = alertDialog.getWindow();
        if(window == null) return;
        window.setContentView(R.layout.dialog_update);
        Button btConfirm = (Button) window.findViewById(R.id.bt_confirm);
        Button btCancel = (Button) window.findViewById(R.id.bt_cancel);
        TextView textView = (TextView) window.findViewById(R.id.tv_info);
        textView.setText(upgradeInfo.getInfo());
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , UpgradeActivity.class);
                intent.putExtra("upgradeInfo" , upgradeInfo);
                startActivity(intent);
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void launchShortcut(int position){
        switch (position){
            case 1:
                Intent intent = new Intent(MainActivity.this, ChannelTypeActivity.class);
                intent.putExtra("type", Constant.key.playz);
                startActivity(intent);
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, MoviesActivity.class));
                break;
            case 3:
                Intent intent1 = new Intent(MainActivity.this, ChannelTypeActivity.class);
                intent1.putExtra("type", Constant.key.online);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    @Override
    public void loadAdImage(boolean isSuccess, ImageInfo imageInfo) {
        if(isSuccess){
            ImageMaster.load(imageInfo.getUrl(), binding.ivMain, R.drawable.img_hold4,
                    R.drawable.img_hold4);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
