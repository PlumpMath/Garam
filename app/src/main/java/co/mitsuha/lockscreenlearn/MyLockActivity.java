package co.mitsuha.lockscreenlearn;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyLockActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lock);
    }
    public void passwordMenuClick(View v) {
        openNMActivity("password");
    }
    public void imageMenuClick(View v) {
        openNMActivity("image");
    }
    public void engwordMenuClick(View v) {
        openNMActivity("engword");
    }
    private void openNMActivity(String type) {
        Intent in = new Intent(this,NotesManageActivity.class);
        in.putExtra("type",type);
        startActivity(in);
    }
}
