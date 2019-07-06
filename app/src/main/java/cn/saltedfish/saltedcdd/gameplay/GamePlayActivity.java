package cn.saltedfish.saltedcdd.gameplay;

import android.os.Bundle;

import cn.saltedfish.saltedcdd.FullscreenActivity;
import cn.saltedfish.saltedcdd.R;

public class GamePlayActivity extends FullscreenActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

}
