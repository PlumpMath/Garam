package co.mitsuha.lockscreenlearn;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class EnglishWordManageActivity extends BaseActivity {

    int rem=10;
    SaveHelper saveHelper;
    final String typeText = "engword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        saveHelper = new SaveHelper(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_word_manage);
    }
    public void addItemClick(View v) {
        List<Memo> ll = saveHelper.getMemosType(typeText);
        for(Memo m : ll) {
            saveHelper.deleteMemo(m.ID);
        }
        saveHelper.addItem(new Memo("engword","TOEIC Voca","toeic.json"));
        Toast.makeText(getApplicationContext(),"성공적으로 추가되었습니다.",Toast.LENGTH_LONG).show();
        this.finish();
    }
    public void testClick(View v) {
        Toast.makeText(getApplicationContext(),String.valueOf(--rem),Toast.LENGTH_SHORT).show();
        if(rem==0) {
            saveHelper.deleteAll();
            Toast.makeText(getApplicationContext(),"Cleared",Toast.LENGTH_SHORT).show();
            rem=10;
            this.finish();
        }
    }
}
