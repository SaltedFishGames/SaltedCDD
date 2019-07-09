package cn.saltedfish.saltedcdd.stage.setting;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;

import cn.saltedfish.saltedcdd.R;

public class SettingView implements SettingContract.View {
    protected SettingContract.Presenter mPresenter;

    protected SeekBar mBGMVolumeSeekBar;

    protected EditText mNicknameInput;

    protected ImageButton mBtnBack;

    public SettingView(Activity pActivity)
    {
        mBGMVolumeSeekBar = pActivity.findViewById(R.id.seekBar_volume);
        mBGMVolumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                mPresenter.syncBGMVolume();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        mNicknameInput = pActivity.findViewById(R.id.editText_nickname);

        mBtnBack = pActivity.findViewById(R.id.button_back);
        mBtnBack.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                mPresenter.leave();
            }
        });
    }

    @Override
    public void setPresenter(SettingContract.Presenter pPresenter)
    {
        mPresenter = pPresenter;
    }

    @Override
    public void setBGMVolume(int pVolume)
    {
        mBGMVolumeSeekBar.setProgress(pVolume);
    }

    @Override
    public int getBGMVolume()
    {
        return mBGMVolumeSeekBar.getProgress();
    }

    @Override
    public void setNickname(String pNickname)
    {
        mNicknameInput.setText(pNickname);
    }

    @Override
    public String getNickname()
    {
        return String.valueOf(mNicknameInput.getText());
    }
}
