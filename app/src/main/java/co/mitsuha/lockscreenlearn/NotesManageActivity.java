package co.mitsuha.lockscreenlearn;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class NotesManageActivity extends Activity {

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
        String type = getIntent().getStringExtra("type");
        listItems = saveHelper.getMemosType(type);

        ListView listview = (ListView)findViewById(R.id.noteManageListView);
        nmListViewAdapter adapter = new nmListViewAdapter();

        listview.setAdapter(adapter);
        for(Memo m : listItems) {
            adapter.addItem(m);
        }
    }
}
