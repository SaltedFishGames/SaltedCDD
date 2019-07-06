package cn.saltedfish.saltedcdd.title;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.FullscreenActivity;
import cn.saltedfish.saltedcdd.gameplay.GamePlayActivity;

public class TitleActivity extends FullscreenActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        Button btn_startGame = findViewById(R.id.start_game_btn);
        btn_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game = new Intent(TitleActivity.this, GamePlayActivity.class);
                startActivity(game);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });

        Button btn_exit = findViewById(R.id.exit_btn);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
