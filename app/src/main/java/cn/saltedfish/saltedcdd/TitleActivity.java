package cn.saltedfish.saltedcdd;

import android.os.Bundle;
import cn.saltedfish.saltedcdd.common.activity.FullscreenActivity;

public class TitleActivity extends FullscreenActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
    }
}
