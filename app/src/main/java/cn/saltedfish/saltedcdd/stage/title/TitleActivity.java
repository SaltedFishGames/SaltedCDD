package cn.saltedfish.saltedcdd.stage.title;

import android.content.Intent;
import android.os.Bundle;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.stage.BGMContoller;
import cn.saltedfish.saltedcdd.stage.FullscreenActivity;
import cn.saltedfish.saltedcdd.stage.Navigator;
import cn.saltedfish.saltedcdd.stage.gameplay.GamePlayActivity;
import cn.saltedfish.saltedcdd.stage.setting.SettingActivity;

public class TitleActivity extends FullscreenActivity {
    protected TitlePresenter mPresenter;

    protected TitleView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        Navigator navigatorToGameStage = new Navigator() {
            @Override
            public void navigate()
            {
                Intent game = new Intent(TitleActivity.this, GamePlayActivity.class);
                startActivity(game);
            }
        };

        Navigator navigatorToSettingStage = new Navigator() {
            @Override
            public void navigate()
            {
                Intent setting = new Intent(TitleActivity.this, SettingActivity.class);
                startActivity(setting);
            }
        };

        Navigator navigatorToQuit = new Navigator() {
            @Override
            public void navigate()
            {
                finishAffinity();
                System.exit(0);
            }
        };

        mView = new TitleView(this);
        mPresenter = new TitlePresenter(navigatorToGameStage, navigatorToSettingStage, navigatorToQuit, mView);

        mPresenter.start();
    }
}
