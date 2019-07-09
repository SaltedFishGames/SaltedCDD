package cn.saltedfish.saltedcdd.stage.title;

import android.content.Intent;

import java.lang.ref.WeakReference;

import cn.saltedfish.saltedcdd.stage.Navigator;
import cn.saltedfish.saltedcdd.stage.gameplay.GamePlayActivity;

public class TitlePresenter implements TitleContract.Presenter {
    protected TitleContract.View mView;

    protected Navigator mNavigatorToGameStage;

    protected Navigator mNavigatorToSettingStage;

    protected Navigator mNavigatorToQuit;

    public TitlePresenter(Navigator pNavigatorToGameStage, Navigator pNavigatorToSettingStage, Navigator pNavigatorToQuit, TitleContract.View pView)
    {
        mNavigatorToGameStage = pNavigatorToGameStage;
        mNavigatorToSettingStage = pNavigatorToSettingStage;
        mNavigatorToQuit = pNavigatorToQuit;

        mView = pView;
        mView.setPresenter(this);
    }

    @Override
    public void start()
    {

    }

    @Override
    public void startGame()
    {
        mNavigatorToGameStage.navigate();
    }

    @Override
    public void openSettings()
    {
        mNavigatorToSettingStage.navigate();
    }

    @Override
    public void quit()
    {
        mNavigatorToQuit.navigate();
    }
}
