package co.mitsuha.lockscreenlearn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NotesManageActivity extends BaseActivity  {

    private SaveHelper saveHelper;
    private List<Memo> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_manage);
        saveHelper = new SaveHelper(getApplicationContext());
        loadList();

    }

    private void loadList() {
        final Context context = getApplicationContext();
        String type = getIntent().getStringExtra("type");
        listItems = saveHelper.getMemosType(type);

        ListView listview = (ListView)findViewById(R.id.noteManageListView);
        nmListViewAdapter adapter = new nmListViewAdapter();

        if(type.equals("image")) adapter.setShowTitlePassword(true);

        listview.setAdapter(adapter);
        for(Memo m : listItems) {
            adapter.addItem(m);
        }
        Drawable bg = ContextCompat.getDrawable(context,R.drawable.round_rectangle_noborder);
        switch(type) {
            case "password":
                bg.setColorFilter(ContextCompat.getColor(context, R.color.colorBlue), PorterDuff.Mode.MULTIPLY);
                break;
            case "image":
                bg.setColorFilter(ContextCompat.getColor(context, R.color.colorPurple), PorterDuff.Mode.MULTIPLY);
                break;
            case "engword":
                bg.setColorFilter(ContextCompat.getColor(context, R.color.colorRed), PorterDuff.Mode.MULTIPLY);
                break;
            default:
                break;
        }
        TextView tv = (TextView)findViewById(R.id.mylockTitle);
        tv.setBackground(bg);
        switch(type) {
            case "image":
                tv.setText("이미지");
                break;
            case "password":
                tv.setText("비밀번호");
                break;
            case "engword":
                tv.setText("영단어");
                break;
            default:
                break;
        }
    }
}
