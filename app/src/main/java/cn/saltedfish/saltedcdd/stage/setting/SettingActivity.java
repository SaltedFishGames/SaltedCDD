package cn.saltedfish.saltedcdd.stage.setting;

import cn.saltedfish.saltedcdd.stage.FullscreenActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import cn.saltedfish.saltedcdd.R;

public class SettingActivity extends FullscreenActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        /*返回按钮*/
        ImageButton btn_back = findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }
}
