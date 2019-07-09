package cn.saltedfish.saltedcdd.stage;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.saltedfish.saltedcdd.data.Config;

public class FullscreenActivity extends AppCompatActivity {
    private boolean mImmersiveEnabled = false;

    @Override
    @TargetApi(19)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 19) {
            mImmersiveEnabled = true;
            hideSystemUI();
        }


    }

    @TargetApi(19)
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (mImmersiveEnabled && hasFocus) {
            hideSystemUI();
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();

        BGMContoller.getInstance().pause();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        BGMContoller.getInstance().play();
    }
}
