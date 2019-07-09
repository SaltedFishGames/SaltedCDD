package cn.saltedfish.saltedcdd.stage.setting;

import cn.saltedfish.saltedcdd.data.Config;
import cn.saltedfish.saltedcdd.stage.BGMContoller;
import cn.saltedfish.saltedcdd.stage.Navigator;

public class SettingPresenter implements SettingContract.Presenter {
    protected SettingContract.View mView;

    protected Navigator mBackNavigator;

    public SettingPresenter(Navigator pBackNavigator, SettingContract.View pView)
    {
        mView = pView;
        mView.setPresenter(this);

        mBackNavigator = pBackNavigator;
    }

    @Override
    public void start()
    {
        mView.setBGMVolume((int)(Config.getBGMVolume() * 100));
        mView.setNickname(Config.getNickname());
    }

    @Override
    public void syncBGMVolume()
    {
        BGMContoller.getInstance().setVolume(mView.getBGMVolume() / 100f);
    }

    @Override
    public void leave()
    {
        mBackNavigator.navigate();
    }
}
