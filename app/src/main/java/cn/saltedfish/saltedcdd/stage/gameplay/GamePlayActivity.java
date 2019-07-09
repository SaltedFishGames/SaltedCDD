package cn.saltedfish.saltedcdd.stage.gameplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageButton;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.stage.FullscreenActivity;
import cn.saltedfish.saltedcdd.stage.Navigator;
import cn.saltedfish.saltedcdd.stage.setting.SettingActivity;
import cn.saltedfish.saltedcdd.stage.title.TitleActivity;

public class GamePlayActivity extends FullscreenActivity {
    protected GamePlayPresenter mPresenter;

    protected GamePlayView mView;

    protected GameModel mGameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Navigator navigatorRestartGame = new Navigator() {
            @Override
            public void navigate()
            {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        };

        Navigator navigatorQuitGame = new Navigator() {
            @Override
            public void navigate()
            {
                finish();
            }
        };

        mView = new GamePlayView(this);
        mGameModel = new GameModel();
        mPresenter = new GamePlayPresenter(mGameModel, navigatorRestartGame, navigatorQuitGame, mView);

        mPresenter.start();
    }

    @Override
    public void onBackPressed()
    {
        mPresenter.onPauseGameClicked();
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mGameModel.destroy();
    }

}
