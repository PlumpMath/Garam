package co.mitsuha.lockscreenlearn;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.webkit.JavascriptInterface;

/**
 * Created by studiodoth on 2017. 5. 17..
 */

public class judgeJsInteface {
    private Context con;
    public judgeJsInteface(Context context) {
        this.con = context;
    }
    @JavascriptInterface
    public void correct(String probID) {
        ((Activity)con).finish();
    }
    @JavascriptInterface
    public void wrong(String probID, String answer) {
        Vibrator v = (Vibrator) con.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }
}
