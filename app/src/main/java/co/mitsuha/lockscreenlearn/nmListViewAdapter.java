package co.mitsuha.lockscreenlearn;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by studiodoth on 2017. 5. 18..
 */

public class nmListViewAdapter extends BaseAdapter {
    private ArrayList<nmListViewItem> listViewItemList = new ArrayList<nmListViewItem>();
    public nmListViewAdapter() {}
    @Override
    public int getCount() {
        return listViewItemList.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if(convertView==null) {
            LayoutInflater infl = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infl.inflate(R.layout.nmlistview_item,parent,false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.nm_title);
        Button deleteButton = (Button) convertView.findViewById(R.id.nm_delete);
        nmListViewItem item = listViewItemList.get(position);

        titleTextView.setText(item.getTitle());
        Drawable bg = ContextCompat.getDrawable(context,R.drawable.nmlistview_del);
        switch(item.getType()) {
            case "password":
                bg.setColorFilter(ContextCompat.getColor(context, R.color.colorPurple), PorterDuff.Mode.MULTIPLY);
                break;
            case "image":
                bg.setColorFilter(ContextCompat.getColor(context, R.color.colorBlue), PorterDuff.Mode.MULTIPLY);
                break;
            default:
                break;
        }
        deleteButton.setBackground(bg);
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
        nm.setTitle(m.title);
        nm.setType(m.type);
        listViewItemList.add(nm);
    }
}
