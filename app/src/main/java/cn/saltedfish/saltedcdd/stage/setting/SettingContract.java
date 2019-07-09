package cn.saltedfish.saltedcdd.stage.setting;

import cn.saltedfish.saltedcdd.stage.BasePresenter;
import cn.saltedfish.saltedcdd.stage.BaseView;

public interface SettingContract {
    interface View extends BaseView<Presenter> {
        void setPresenter(Presenter pPresenter);

        void setBGMVolume(int pVolume);

        int getBGMVolume();

        void setNickname(String pNickname);

        String getNickname();
    }

    interface Presenter extends BasePresenter {
        void start();

        void syncBGMVolume();

        void leave();
    }
}
