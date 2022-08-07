package com.example.appangicungduoc.ThongKe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appangicungduoc.MonAnModel;
import com.example.appangicungduoc.R;

import java.util.ArrayList;

public class ThongKeAdapter extends BaseAdapter {

    Context context;
    ArrayList<ThongKeModel> thongKeModel;

    public ThongKeAdapter(Context context, ArrayList<ThongKeModel> thongKeModel) {
        this.context = context;
        this.thongKeModel = thongKeModel;
    }

    @Override
    public int getCount() {
        return thongKeModel.size();
    }

    @Override
    public Object getItem(int position) {
        return thongKeModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.customtk,viewGroup,false);

        TextView ngay=(TextView)view.findViewById(R.id.txtDate);
        TextView tien=(TextView)view.findViewById(R.id.txtTien);

        ngay.setText(thongKeModel.get(position).getNgay());
        tien.setText(String.valueOf(thongKeModel.get(position).getTien()));
        return view;
    }
}
