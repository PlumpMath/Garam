package co.mitsuha.lockscreenlearn;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;
import java.util.Random;

public class LockScreenActivity extends Activity {

    SaveHelper saveHelper;

    public void makeFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT < 19) { //View.SYSTEM_UI_FLAG_IMMERSIVE is only on API 19+
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        } else {
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Garam","LockScreenActivity onCreate Triggered");
        saveHelper = new SaveHelper(getApplicationContext());
        super.onCreate(savedInstanceState);
        makeFullScreen();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        setContentView(R.layout.activity_lock_screen);

        WebView wv = (WebView)findViewById(R.id.lockWebView);
        wv.setWebViewClient(new WebViewClient());
        WebSettings ws = wv.getSettings();
        ws.setDomStorageEnabled(true);
        ws.setJavaScriptEnabled(true);
        wv.setVerticalScrollBarEnabled(false);
        wv.setHorizontalScrollBarEnabled(false);
        wv.setBackgroundColor(0);
        wv.setBackgroundResource(R.drawable.bg);

        List<Memo> memos = saveHelper.getMemos();
        if(memos.size()==0) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("가람")
                            .setContentText("문제가 없어 잠금화면을 켜지 못했습니다 :(");
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(0, mBuilder.build());
            this.finish();
            return;
        }
        int choose = new Random().nextInt(memos.size());
        wv.loadUrl("file:///android_asset/prob_templates/"+memos.get(choose).type+".html?probID="+memos.get(choose).ID+"&ans="+memos.get(choose).password+"&hint="+memos.get(choose).title);
        wv.addJavascriptInterface(new judgeJsInteface(this), "judge");
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void unlockScreen(View view) {
        this.finish();
    }
}
