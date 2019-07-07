package cn.saltedfish.saltedcdd.stage.title;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.stage.FullscreenActivity;
import cn.saltedfish.saltedcdd.stage.gameplay.GamePlayActivity;
import cn.saltedfish.saltedcdd.stage.setting.SettingActivity;

public class TitleActivity extends FullscreenActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        /*开始游戏界面跳转*/
        Button btn_startGame = findViewById(R.id.button_startGame);
        btn_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game = new Intent(TitleActivity.this, GamePlayActivity.class);
                startActivity(game);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
        /*设置界面跳转*/
        Button btn_setting = findViewById(R.id.button_setting);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setting = new Intent(TitleActivity.this, SettingActivity.class);
                startActivity(setting);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
        /*退出游戏*/
        Button btn_exit = findViewById(R.id.button_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
