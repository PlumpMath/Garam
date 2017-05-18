package co.mitsuha.lockscreenlearn;

import android.app.Application;
import android.content.Context;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by studiodoth on 2017. 5. 18..
 */

public class FontApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("NanumMyeongjo.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
