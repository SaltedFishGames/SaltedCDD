package cn.saltedfish.saltedcdd.stage.title;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.saltedfish.saltedcdd.R;
<<<<<<< HEAD:app/src/main/java/cn/saltedfish/saltedcdd/title/TitleActivity.java
import cn.saltedfish.saltedcdd.FullscreenActivity;
import cn.saltedfish.saltedcdd.gameplay.GamePlayActivity;
=======
import cn.saltedfish.saltedcdd.stage.FullscreenActivity;
>>>>>>> 55370c802983f7548cc50960bb5c37f2d0f493bb:app/src/main/java/cn/saltedfish/saltedcdd/stage/title/TitleActivity.java

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
