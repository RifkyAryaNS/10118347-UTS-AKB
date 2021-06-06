package com.example.rifkyans10118347;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter {

    private LayoutInflater layout;



    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CustomCursorAdapter(Context context, Cursor cursor, int flags){
        super(context, cursor, flags);
        layout = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup){
        View view = layout.inflate(R.layout.row, viewGroup, false);
        MyHolder holder = new MyHolder();
        holder.listID = (TextView)view.findViewById(R.id.listID);
        holder.listJudul = (TextView)view.findViewById(R.id.listJudul);
        holder.listCategory = (TextView)view.findViewById(R.id.listCategory);
        holder.listIsi = (TextView)view.findViewById(R.id.listIsi);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        MyHolder holder = (MyHolder)view.getTag();

        holder.listID.setText(cursor.getString(cursor.getColumnIndex(DBHandler.row_id)));
        holder.listJudul.setText(cursor.getString(cursor.getColumnIndex(DBHandler.row_judul)));
        holder.listCategory.setText(cursor.getString(cursor.getColumnIndex(DBHandler.row_category)));
        holder.listIsi.setText(cursor.getString(cursor.getColumnIndex(DBHandler.row_isi)));
    }

    class MyHolder{
        TextView listID, listJudul, listCategory, listIsi;
    }
}
