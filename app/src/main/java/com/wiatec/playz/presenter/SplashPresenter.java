package com.wiatec.playz.presenter;

import com.wiatec.playz.model.AdImageProvider;
import com.wiatec.playz.model.UpgradeProvider;
import com.wiatec.playz.model.LoadService;
import com.wiatec.playz.pojo.ImageInfo;
import com.wiatec.playz.pojo.UpgradeInfo;
import com.wiatec.playz.view.activity.Splash;

/**
 * splash presenter
 */

public class SplashPresenter extends BasePresenter<Splash> {

    AdImageProvider adImageProvider;
    UpgradeProvider upgradeProvider;
    private Splash splash;

    public SplashPresenter(Splash splash){
        this.splash = splash;
        adImageProvider = new AdImageProvider();
        upgradeProvider = new UpgradeProvider();
    }

    //调用model - AdImageProvider 获取需要的Image文件
    public void loadAdImage(){
        if(adImageProvider != null){
            adImageProvider.load(new LoadService.OnLoadListener<ImageInfo>() {
                @Override
                public void onLoad(boolean execute, ImageInfo imageInfo) {
                    splash.loadAdImage(execute, imageInfo);
                }
            });
        }
    }

    //检查app upgradeProvider
    public void checkUpgrade(){
            upgradeProvider.load(new LoadService.OnLoadListener<UpgradeInfo>() {
                @Override
                public void onLoad(boolean execute, UpgradeInfo upgradeInfo) {
                    splash.checkUpgrade(execute, upgradeInfo);
                }
            });
    }
}
