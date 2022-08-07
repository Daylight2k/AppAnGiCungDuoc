package com.example.appangicungduoc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<MonAnModel> monAnModels;

    public MyAdapter(Context context, ArrayList<MonAnModel> monAnModels) {
        this.context = context;
        this.monAnModels = monAnModels;
    }

    @Override
    public int getCount() {
        return monAnModels.size();
    }

    @Override
    public Object getItem(int i) {
        return monAnModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.custom,viewGroup,false);

        ImageView img = view.findViewById(R.id.img);
        TextView txtTenMon = view.findViewById(R.id.tenMon);
        TextView txtGia = view.findViewById(R.id.txtGia);

        img.setImageResource(monAnModels.get(i).idAnh);
        txtTenMon.setText(monAnModels.get(i).tenMon);
        String s=String.valueOf(monAnModels.get(i).gia);
        txtGia.setText("Giá : "+s+" vnđ");
        return view;
    }


}
