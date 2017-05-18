package co.mitsuha.lockscreenlearn;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by studiodoth on 2017. 5. 18..
 */

public class nmListViewAdapter extends BaseAdapter {
    private SaveHelper saveHelper;
    private boolean showTitlePassword = false;
    private ArrayList<nmListViewItem> listViewItemList = new ArrayList<nmListViewItem>();
    public nmListViewAdapter() {}
    @Override
    public int getCount() {
        return listViewItemList.size();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        final int pos = position;
        saveHelper = new SaveHelper(context);

        if(convertView==null) {
            LayoutInflater infl = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infl.inflate(R.layout.nmlistview_item,parent,false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.nm_title);
        TextView idTextView = (TextView) convertView.findViewById(R.id.nm_id);
        Button deleteButton = (Button) convertView.findViewById(R.id.nm_delete);
        nmListViewItem item = listViewItemList.get(position);

        titleTextView.setText(showTitlePassword==true?item.getPassword():item.getTitle());
        idTextView.setText(String.valueOf(item.getID()));
        Drawable bg = ContextCompat.getDrawable(context,R.drawable.nmlistview_del);
        switch(item.getType()) {
            case "password":
                bg.setColorFilter(ContextCompat.getColor(context, R.color.colorBlue), PorterDuff.Mode.MULTIPLY);
                break;
            case "image":
                bg.setColorFilter(ContextCompat.getColor(context, R.color.colorPurple), PorterDuff.Mode.MULTIPLY);
                break;
            default:
                break;
        }
        deleteButton.setBackground(bg);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveHelper = new SaveHelper(context);
                LinearLayout ll = (LinearLayout)v.getParent();
                saveHelper.deleteMemo(Integer.parseInt(((TextView)ll.getChildAt(0)).getText().toString()));
                Toast.makeText(context,"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                listViewItemList.remove(position);
                nmListViewAdapter.super.notifyDataSetChanged();
            }
        });

        return convertView;
    }
    @Override
    public long getItemId(int position) {
        return listViewItemList.get(position).getID();
    }
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }
    public void addItem(Memo m) {
        nmListViewItem nm = new nmListViewItem();
        nm.setID(m.ID);
        nm.setPassword(m.password);
        nm.setTitle(m.title);
        nm.setType(m.type);
        listViewItemList.add(nm);
    }
    public void setShowTitlePassword(boolean val) {
        showTitlePassword = val;
    }
}
