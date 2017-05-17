package co.mitsuha.lockscreenlearn;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class PasswordManageActivity extends Activity {

    SaveHelper saveHelper;
    final String typeText = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        saveHelper = new SaveHelper(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_manage);
    }
    public void addItemClick(View v) {
        String title = ((EditText)findViewById(R.id.passwordEditTitle)).getText().toString(),
                password = ((EditText)findViewById(R.id.passwordEditPassword)).getText().toString();
        title = title.trim(); password = password.trim();
        if(title==""||password=="") {
            Toast.makeText(getApplicationContext(),"모든 항목이 채워져 있어야 합니다.",Toast.LENGTH_LONG).show();
            return;
        }
        saveHelper.addItem(new Memo(typeText,title,password));
        List<Memo> memos = saveHelper.getMemos();
        for(int i=0;i<memos.size();i++) {
            Log.d("garamQueryRes",memos.get(i).toString());
        }
        Toast.makeText(getApplicationContext(),"성공적으로 추가되었습니다.",Toast.LENGTH_LONG).show();
        ((EditText)findViewById(R.id.passwordEditTitle)).setText("");
        ((EditText)findViewById(R.id.passwordEditPassword)).setText("");
    }
}
