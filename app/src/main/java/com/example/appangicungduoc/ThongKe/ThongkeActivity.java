package com.example.appangicungduoc.ThongKe;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appangicungduoc.R;

import java.util.ArrayList;

public class ThongkeActivity extends AppCompatActivity {
    Spinner spnThang ;
    Spinner spnLoai ;
    SQLiteDatabase db;
    ListView ls;
    TextView txtTongTien;
    int Tien,click=0;

    ArrayList<ThongKeModel> thongKe;
    ThongKeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_main);
        spnThang = (Spinner) findViewById(R.id.spinnerThang);
        spnLoai = (Spinner) findViewById(R.id.spinnerLoai);
        ls=findViewById(R.id.lv);
        txtTongTien=findViewById(R.id.txtTongTien);

        setSpinnerLoai();
        setSpinnerThang();

        spnThang.setVisibility(View.GONE);
        initData();
        thongKe=new ArrayList<>();
        adapter=new ThongKeAdapter(ThongkeActivity.this,thongKe);
        thongKe.clear();
        ls.setAdapter(adapter);

        spnLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("Tất cả")==true)
                {
                    loadData();
                    if(Tien==0)
                    {
                        Toast.makeText(ThongkeActivity.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                }

                if(parent.getItemAtPosition(position).toString().equals("Tháng hiện tại")==true)
                {
                    thongKe.clear();
                    Tien=0;
                    String sql="SELECT * FROM ThongKe  \n" +
                            "WHERE date(Date,'start of month') \n" +
                            "= date('now','start of month')";
                    Cursor cursor= db.rawQuery(sql,null);
                    while (cursor.moveToNext())
                    {
                        String ngay=cursor.getString(0);
                        int tien=cursor.getInt(1);
                        thongKe.add(new ThongKeModel(ngay,tien));
                        Tien+=tien;
                    }
                    cursor.close();
                    adapter.notifyDataSetChanged();
                    txtTongTien.setText(String.valueOf(Tien)+" VND");
                    if(Tien==0)
                    {

                        Toast.makeText(ThongkeActivity.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                }
                if(parent.getItemAtPosition(position).toString().equals("Tháng")==true)
                {
                    spnThang.setVisibility(View.VISIBLE);
                    thongKe.clear();
                    adapter.notifyDataSetChanged();
                    Tien=0;
                    txtTongTien.setText(String.valueOf(Tien)+" VND");
                    spnThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            int thang=Integer.parseInt(parent.getItemAtPosition(position).toString());
                            thang=thang-1;
                            thongKe.clear();
                            Tien=0;
                            String sql="SELECT * FROM ThongKe  \n" +
                                    "WHERE date(Date,'start of month') \n" +
                                    "= date('now','start of year','+"+thang+" month')";
                            Cursor cursor= db.rawQuery(sql,null);
                            while (cursor.moveToNext())
                            {
                                String ngay=cursor.getString(0);
                                int tien=cursor.getInt(1);
                                thongKe.add(new ThongKeModel(ngay,tien));
                                Tien+=tien;
                            }
                            cursor.close();
                            adapter.notifyDataSetChanged();
                            txtTongTien.setText(String.valueOf(Tien)+" VND");
                            if(Tien==0)
                            {
                                Toast.makeText(ThongkeActivity.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else {
                    spnThang.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    void setSpinnerLoai()
    {
        ArrayList<String> loai;
        loai=new ArrayList<>();
        loai.add("Tất cả");
        loai.add("Tháng hiện tại");
        loai.add("Tháng");
        ArrayAdapter<String> adapterLoai=new ArrayAdapter<>(ThongkeActivity.this, android.R.layout.simple_list_item_1,loai);
        adapterLoai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLoai.setAdapter(adapterLoai);
    }
    void setSpinnerThang()
    {
        ArrayList<String> thang;
        thang=new ArrayList<>();
        thang.add("1");
        thang.add("2");
        thang.add("3");
        thang.add("4");
        thang.add("5");
        thang.add("6");
        thang.add("7");
        thang.add("8");
        thang.add("9");
        thang.add("10");
        thang.add("11");
        thang.add("12");

        ArrayAdapter<String> adapter=new ArrayAdapter<>(ThongkeActivity.this, android.R.layout.simple_list_item_1,thang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnThang.setAdapter(adapter);
    }

    private void initData() {
        db=openOrCreateDatabase("anGiCungDuoc.sqlite",MODE_PRIVATE,null);
        String sql="CREATE TABLE IF NOT EXISTS ThongKe (Date date primary key, Tien INTEGER)";
        db.execSQL(sql);
    }
    void loadData()
    {
        Tien=0;
        String sql="SELECT * FROM ThongKe";
        Cursor cursor= db.rawQuery(sql,null);
        thongKe.clear();
        while (cursor.moveToNext())
        {
            String ngay=cursor.getString(0);
            int tien=cursor.getInt(1);
            thongKe.add(new ThongKeModel(ngay,tien));
            Tien+=tien;
        }
        cursor.close();
        adapter.notifyDataSetChanged();

        txtTongTien.setText(String.valueOf(Tien)+" VND");
    }
}