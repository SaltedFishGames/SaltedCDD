package cn.saltedfish.saltedcdd.stage.setting;

import cn.saltedfish.saltedcdd.data.Config;
import cn.saltedfish.saltedcdd.stage.FullscreenActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.stage.Navigator;

public class SettingActivity extends FullscreenActivity {
    protected SettingContract.Presenter mPresenter;

    protected SettingContract.View mView;

    protected Navigator mBackNavigator = new Navigator() {
        @Override
        public void navigate()
        {
            String newNickname = mView.getNickname();
            if (newNickname.length() > 0)
            {
                Config.setNickname(newNickname);
            }

            Config.setBGMVolume(mView.getBGMVolume() / 100f);
            Config.save();

            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mView = new SettingView(this);
        mPresenter = new SettingPresenter(mBackNavigator, mView);

        mPresenter.start();
    }

    @Override
    public void onBackPressed()
    {
        mBackNavigator.navigate();
    }
}
