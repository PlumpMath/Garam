package co.mitsuha.lockscreenlearn;

import android.provider.BaseColumns;

/**
 * Created by studiodoth on 2017. 5. 17..
 */

public class Memo {
    public int ID;
    public String type;
    public String title;
    public String password;
    public Memo() {}
    public Memo(String type, String title, String password) {
        super();
        this.type = type;
        this.title = title;
        this.password = password;
    }
    @Override
    public String toString() {
        return "Memo [id: "+ID+", type: "+type+", title: "+title+", password: "+password+"]";
    }
}
