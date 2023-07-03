package com.example.shiyan2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {//将数据库中的数据显示在ListView中，并为每个列表项设置自定义的布局。
    private Context mContext;
    private Cursor mCursor;
    private LinearLayout mLayout;
    public MyAdapter(Context mContext,Cursor mCursor) {
        this.mContext = mContext;
        this.mCursor = mCursor;
    }

    @Override
    public int getCount() {
        return mCursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return mCursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mLayout = (LinearLayout) inflater.inflate(R.layout.item,null);
        TextView content = (TextView) mLayout.findViewById(R.id.list_content);
        TextView time = (TextView) mLayout.findViewById(R.id.list_time);
        mCursor.moveToPosition(position);
        String dbcontext = mCursor.getString(mCursor.getColumnIndex("content"));
        String dbtime = mCursor.getString(mCursor.getColumnIndex("time"));
        content.setText(dbcontext);
        time.setText(dbtime);
        return mLayout;
    }
}