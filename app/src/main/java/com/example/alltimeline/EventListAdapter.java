package com.example.alltimeline;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventListAdapter extends BaseAdapter {
    LayoutInflater inflater = null;
    private ArrayList<Event> m_oData = null;
    private int nListCnt = 0;

    public EventListAdapter(ArrayList<Event> _oData) {
        m_oData = _oData;
        nListCnt = m_oData.size();
    }

    @Override
    public int getCount() {
        Log.i("TAG", "getCount");
        return nListCnt;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final Context context = parent.getContext();
            if (inflater == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.user_event_list, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.item_text_name);
        name.setText(m_oData.get(position).getname());
        TextView year = (TextView) convertView.findViewById(R.id.item_text_year);
        year.setText(m_oData.get(position).getyear() + "");
        TextView memo = (TextView) convertView.findViewById(R.id.item_text_memo);
        memo.setText(m_oData.get(position).getmemo());

        return convertView;
    }
}
