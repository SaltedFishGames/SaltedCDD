package cn.saltedfish.saltedcdd.stage.title;

import cn.saltedfish.saltedcdd.stage.BasePresenter;
import cn.saltedfish.saltedcdd.stage.BaseView;

public interface TitleContract {
    interface View extends BaseView<Presenter> {
        void setPresenter(Presenter pPresenter);
    }

    interface Presenter extends BasePresenter  {
        void start();

        void startGame();

        void openSettings();

        void quit();
    }
}