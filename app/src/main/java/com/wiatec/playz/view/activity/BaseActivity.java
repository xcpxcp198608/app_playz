package com.wiatec.playz.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.wiatec.playz.R;
import com.wiatec.playz.model.UserContentResolver;
import com.wiatec.playz.presenter.BasePresenter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * base activity
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected T presenter;
    protected abstract T createPresenter();
    protected int userLevel;
    private Subscription keyEventSubscription;
    protected boolean isSubscribe = true;
    protected boolean startAd = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        presenter = createPresenter();
        presenter.attach(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //通过Content provider从btv_launcher的sp中获取当前用户level
        String level = UserContentResolver.get("userLevel");
        try {
            userLevel = Integer.parseInt(level);
        }catch (Exception e){
            userLevel = 1;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAd = true;
        subscribeKeyEvent();
    }

    private void subscribeKeyEvent(){
        //不需要按键事件监听的页面在start中将 isSubscribe 设置为false
        if(!isSubscribe){
            return;
        }
        //用户等级大于2级时不进行按键事件监听
        if(userLevel > 2){
            return;
        }
        keyEventSubscription = Observable.timer(1200 , TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if(startAd) {
                            startActivity(new Intent(BaseActivity.this, AdScreenActivity.class));
                        }
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        release();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        startAd = true;
        subscribeKeyEvent();
        return super.onKeyUp(keyCode, event);
    }

    private void jumpToChannelById() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setGravity(Gravity.TOP|Gravity.LEFT);
        layoutParams.x = 0;
        layoutParams.y = 0;
        window.setAttributes(layoutParams);
        dialog.setContentView(R.layout.dialog_jump_channel);
        EditText etChannelId = (EditText) window.findViewById(R.id.etChannelId);
        etChannelId.requestFocus();
    }

    @Override
    protected void onPause() {
        super.onPause();
        release();
    }

    @Override
    protected void onStop() {
        super.onStop();
        release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
        release();
    }

    private void release(){
        startAd = false;
        if(keyEventSubscription != null){
            keyEventSubscription.unsubscribe();
        }
    }

}
