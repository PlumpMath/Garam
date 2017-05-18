package co.mitsuha.lockscreenlearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this,LockScreenService.class));
        setContentView(R.layout.activity_main);
    }
    public void passwordMenuClick(View v) {
        Intent it = new Intent(this,PasswordManageActivity.class);
        startActivity(it);
    }
    public void photoMenuClick(View v) {
        Intent it = new Intent(this,PhotoManageActivity.class);
        startActivity(it);
    }
    public void mylockMenuClick(View v) {
        Intent it = new Intent(this,MyLockActivity.class);
        startActivity(it);
    }
    public void engwordMenuClick(View v) {
        Intent it = new Intent(this,EnglishWordManageActivity.class);
        startActivity(it);
    }
    public void patternMenuClick(View v) {
        Intent it = new Intent(this,PatternManageActivity.class);
        startActivity(it);
    }
}
