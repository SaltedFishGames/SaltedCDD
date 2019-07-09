package cn.saltedfish.saltedcdd.stage.gameplay;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.stage.FullscreenActivity;

public class GamePlayActivity extends FullscreenActivity {
    /**游戏菜单layout*/
    ConstraintLayout layout_menu;
    /**游戏暂停button*/
    ImageButton btn_pauseGame;

    protected GamePlayPresenter mPresenter;

    protected GamePlayView mView;

    protected GameModel mGameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d("CDD", "Hello");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        layout_menu = findViewById(R.id.layout_menu);

        /*游戏暂停*/
        btn_pauseGame = findViewById(R.id.button_pauseGame);
        btn_pauseGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*暂停游戏，弹出option（退出游戏/重新一局/继续游戏）*/
                pauseGame();
            }
        });

        /*继续游戏按钮*/
        ImageButton btn_continueGame = findViewById(R.id.button_continueGame);
        btn_continueGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_menu.setVisibility(View.GONE);
                btn_pauseGame.setVisibility(View.VISIBLE);
            }
        });

        /*返回主界面按钮*/
        ImageButton btn_returnHome = findViewById(R.id.button_returnHome);
        btn_returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*结束游戏进程，释放资源*/

                /*界面跳转*/
                finish();
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });

        /*重新一局按钮*/
        ImageButton btn_refreshGame = findViewById(R.id.button_refreshGame);
        btn_refreshGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mView = new GamePlayView(this);
        mGameModel = new GameModel();
        mPresenter = new GamePlayPresenter(mGameModel, mView);

        mPresenter.start();
    }

    /**按返回键时不直接退出游戏，而是暂停游戏*/
    @Override
    public void onBackPressed(){
        this.pauseGame();
    }

    /**暂停游戏，弹出option（退出游戏/重新一局/继续游戏）*/
    private void pauseGame(){
        layout_menu.setVisibility(View.VISIBLE);
        btn_pauseGame.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mGameModel.destroy();
    }

}
