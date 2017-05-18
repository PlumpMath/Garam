package co.mitsuha.lockscreenlearn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eftimoff.patternview.PatternView;
import com.eftimoff.patternview.cells.Cell;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class PatternManageActivity extends BaseActivity {
    final String[] texts = new String[9];
    final Boolean[] filled = {false,false,false,false,false,false,false,false,false};
    final String type = "pattern";
    SaveHelper saveHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_manage);
        saveHelper = new SaveHelper(getApplicationContext());
    }
    public void textItemClick(View v) {
        int target=0;
        final int buttonId = v.getId();
        switch (buttonId) {
            case R.id.patternTextBtn1:
                target=1;
                break;
            case R.id.patternTextBtn2:
                target=2;
                break;
            case R.id.patternTextBtn3:
                target=3;
                break;
            case R.id.patternTextBtn4:
                target=4;
                break;
            case R.id.patternTextBtn5:
                target=5;
                break;
            case R.id.patternTextBtn6:
                target=6;
                break;
            case R.id.patternTextBtn7:
                target=7;
                break;
            case R.id.patternTextBtn8:
                target=8;
                break;
            case R.id.patternTextBtn9:
                target=9;
                break;
        }
        final int Target = target;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("글씨 입력");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = input.getText().toString().trim();
                if(text.equals("")) {
                    Toast.makeText(getApplicationContext(),"내용이 없습니다",Toast.LENGTH_SHORT).show();
                    return;
                }
                texts[Target-1]=text;
                if(!filled[Target-1]) {
                    filled[Target-1]=true;
                    ((Button)findViewById(buttonId)).setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.shape_blue));
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog ad = builder.create();
        ad.show();
    }
    public void addItemClick(View v) {
        PatternView pv = (PatternView)findViewById(R.id.patternLock);
        String pattern = patternString(pv.getPattern());
        for(int i=0;i<9;i++) {
            if(!filled[i]) {
                textEmptyToast(i);
                return;
            }
        }
        String titleText = ((EditText)findViewById(R.id.patternTitleEditText)).getText().toString().trim();
        if(titleText.equals("")) {
            Toast.makeText(getApplicationContext(),"제목이 비어있어요",Toast.LENGTH_SHORT).show();
            return;
        }
        String textsJSON="[]";
        try {
            textsJSON = new JSONArray(texts).toString();
        }
        catch(JSONException e) {}
        saveHelper.addItem(new Memo(type,textsJSON,titleText+pattern));
        Toast.makeText(getApplicationContext(),"성공적으로 추가되었습니다.",Toast.LENGTH_LONG).show();
        this.finish();
    }
    private void textEmptyToast(int loc) {
        Toast.makeText(getApplicationContext(),"글씨가 비어있어요 ("+String.valueOf((int)(loc/3+1))+"번째 줄, "+String.valueOf(loc%3+1)+"번때 칸)",Toast.LENGTH_SHORT).show();
    }
    private String patternString(List<Cell> cells) {
        String ret="";
        for(Cell c : cells ) {
            ret+=String.valueOf(3*c.getRow()+c.getColumn()+1);
        }
        for(int i=0;i<(9-cells.size());i++) ret+="0";
        return ret;
    }
}
