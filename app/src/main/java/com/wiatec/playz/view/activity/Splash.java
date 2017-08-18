package com.wiatec.playz.view.activity;

import com.wiatec.playz.pojo.UpgradeInfo;

/**
 * splash activity interface
 */

public interface Splash extends Common {
    
    void checkUpgrade(boolean upgrade, UpgradeInfo upgradeInfo);
}
