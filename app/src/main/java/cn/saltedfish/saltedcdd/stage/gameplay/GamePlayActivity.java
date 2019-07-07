package cn.saltedfish.saltedcdd.stage.gameplay;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.stage.FullscreenActivity;

public class GamePlayActivity extends FullscreenActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /*暂停按钮*/
        ImageButton btn_pause = findViewById(R.id.button_pause);
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*暂停游戏，弹出option（退出游戏/重新一局/继续游戏）*/
            }
        });

    }

    @Override
    public void onBackPressed(){
        finish();

    }

}
