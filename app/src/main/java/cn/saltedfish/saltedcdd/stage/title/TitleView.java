package cn.saltedfish.saltedcdd.stage.title;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import cn.saltedfish.saltedcdd.R;

public class TitleView implements TitleContract.View {
    protected TitleContract.Presenter mPresenter;

    protected Button mBtnStartGame;

    protected Button mBtnOpenSettings;

    protected Button mBtnQuit;

    public TitleView(Activity pActivity)
    {
        mBtnStartGame = pActivity.findViewById(R.id.button_startGame);
        mBtnStartGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mPresenter.startGame();
            }
        });

        mBtnOpenSettings = pActivity.findViewById(R.id.button_setting);
        mBtnOpenSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mPresenter.openSettings();
            }
        });

        mBtnQuit = pActivity.findViewById(R.id.button_exit);
        mBtnQuit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mPresenter.quit();
            }
        });
    }

    @Override
    public void setPresenter(TitleContract.Presenter pPresenter)
    {
        mPresenter = pPresenter;
    }


}
