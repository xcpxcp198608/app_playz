package com.wiatec.playz.manager;

import com.px.common.http.HttpMaster;
import com.px.common.http.Listener.StringListener;
import com.px.common.utils.AESUtil;
import com.px.common.utils.CommonApplication;
import com.px.common.utils.EmojiToast;
import com.px.common.utils.Logger;
import com.wiatec.playz.R;
import com.wiatec.playz.model.UserContentResolver;
import com.wiatec.playz.pojo.ChannelInfo;

import java.io.IOException;
import java.util.List;

/**
 * play manager
 */

public class PlayManager {

    private List<ChannelInfo> channelInfoList;
    private int currentPosition;
    private ChannelInfo channelInfo;
    private PlayListener mPlayListener;
    private int level;

    public PlayManager(List<ChannelInfo> channelInfoList, int currentPosition) {
        this.channelInfoList = channelInfoList;
        this.currentPosition = currentPosition;
        channelInfo = channelInfoList.get(currentPosition);
        String levelStr = UserContentResolver.get("userLevel");
        try {
            level = Integer.parseInt(levelStr);
        }catch (Exception e){
            level = 1;
        }
    }

    public interface PlayListener{
        void play(String url);
        void playAd();
        void launchApp(String packageName);
    }

    public void setPlayListener(PlayListener playListener){
        mPlayListener = playListener;
    }

    public ChannelInfo getChannelInfo(){
        return channelInfo;
    }

    public void dispatchChannel(){
        String type = channelInfo.getType();
        String url = AESUtil.decrypt(channelInfo.getUrl(), AESUtil.KEY);
        if("live".equals(type)){
            if(channelInfo.isLocked()){
                if(level > 2){
                    if(mPlayListener != null){
                        mPlayListener.play(url);
                    }
                }else{
                    String experience = UserContentResolver.get("experience");
                    if("true".equals(experience)){
                        if(mPlayListener != null){
                            mPlayListener.play(url);
                        }
                    }else{
                        if(mPlayListener != null){
                            EmojiToast.show(CommonApplication.context.getString(R.string.authority), EmojiToast.EMOJI_SAD);
                            mPlayListener.playAd();
                        }
                    }
                }
            }else{
                if(mPlayListener != null){
                    mPlayListener.play(url);
                }
            }
        }else if("relay".equals(type)){
            if(channelInfo.isLocked()){
                if(level > 2){
                    relayUrl(url);
                }else{
                    String experience = UserContentResolver.get("experience");
                    if("true".equals(experience)){
                        relayUrl(url);
                    }else{
                        if(mPlayListener != null){
                            EmojiToast.showLong(CommonApplication.context.getString(R.string.authority), EmojiToast.EMOJI_SAD);
                            mPlayListener.playAd();
                        }
                    }
                }
            }else{
                relayUrl(url);
            }
        }else if("app".equals(type)){
            if(mPlayListener != null){
                mPlayListener.launchApp(AESUtil.decrypt(channelInfo.getUrl(), AESUtil.KEY));
            }
        }else{
            Logger.d("type error");
        }
    }

    private void relayUrl(String url){
        HttpMaster.get(url)
                .enqueue(new StringListener() {
                    @Override
                    public void onSuccess(String s) throws IOException {
                        if(s != null){
                            if(mPlayListener != null){
                                mPlayListener.play(s);
                            }
                        }
                    }

                    @Override
                    public void onFailure(String e) {
                        Logger.d(e);
                    }
                });
    }

    public void previousChannel(){
        currentPosition -- ;
        if(currentPosition < 0){
            currentPosition = channelInfoList.size()-1;
        }
        channelInfo = channelInfoList.get(currentPosition);
        dispatchChannel();
    }

    public void nextChannel(){
        currentPosition ++ ;
        if(currentPosition >= channelInfoList.size()){
            currentPosition = 0;
        }
        channelInfo = channelInfoList.get(currentPosition);
        dispatchChannel();
    }
}
