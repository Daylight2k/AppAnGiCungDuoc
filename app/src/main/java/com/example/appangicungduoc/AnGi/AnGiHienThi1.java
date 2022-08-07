package com.example.appangicungduoc.AnGi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appangicungduoc.DanhSach.DSMonActivity;
import com.example.appangicungduoc.Database;
import com.example.appangicungduoc.MainActivity;
import com.example.appangicungduoc.MonAnModel;
import com.example.appangicungduoc.MyAdapter;
import com.example.appangicungduoc.R;

import java.util.ArrayList;
import java.util.Random;

public class AnGiHienThi1 extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1;
    SQLiteDatabase db;
    ArrayList<MonAnModel> data=new ArrayList<>();
    MyAdapter adapter=new MyAdapter(AnGiHienThi1.this,data);
    String DB_NAME="anGiCungDuoc.sqlite";
    SQLiteDatabase database=null;

    Random random=new Random();
    int vt;
    int soNguoi,tongTien,dem,dem1,giaMon;
    String loai;
    double nganSach;
    TextView txtTen,txtGia,txtNguyenLieu,errot,tvChinh;
    Button ok,doiMon,trolai;
    ImageView anh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_gi_hien_thi1);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        anhxa();
        getTT();

        loadData(nganSach);
        if(dem>0)
            randomMon();

        doiMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doiMon();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                thongbao();
                Intent intent=new Intent(AnGiHienThi1.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


                finish();
            }
        });
        trolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AnGiHienThi1.this, AngiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    private void thongbao() {

        NotificationCompat.Builder builder=new NotificationCompat.Builder(AnGiHienThi1.this,"My Notification");
        builder.setContentTitle("Nguyên liệu cần mua:");
        builder.setContentText(data.get(vt).getNguyenLieu().toString());
        builder.setSmallIcon(R.drawable.giohang);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AnGiHienThi1.this);
        managerCompat.notify(1,builder.build());
    }

    void anhxa()
    {
        txtTen=findViewById(R.id.txtHT1TenMon);
        txtGia=findViewById(R.id.txtHT1Gia);
        txtNguyenLieu=findViewById(R.id.txtHT1NguyenLieu);
        ok=findViewById(R.id.btOk1Mon);
        doiMon=findViewById(R.id.btDoi1Mon);
        anh=findViewById(R.id.img1mon);
        errot=findViewById(R.id.txtER);
        trolai=findViewById(R.id.troVe);
        tvChinh=findViewById(R.id.textView6);

    }
    void loadData(double giaMax)
    {
        if(loai.equals("rỗng")==true)
            {
                try {

                data=new ArrayList<>();
                adapter=new MyAdapter(AnGiHienThi1.this,data);
                database = Database.initDatabase(AnGiHienThi1.this, DB_NAME);
                Cursor cursor = database.rawQuery("Select * from MonAn where Gia <= '" + giaMax +"' ",null);
                data.clear();
                dem=cursor.getCount();
                if (dem>0) {
                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(0);
                        String name = cursor.getString(1);
                        String loai = cursor.getString(2);
                        int gia = cursor.getInt(3);
                        String nguyenlieu = cursor.getString(4);
                        data.add(new MonAnModel(id, name, loai, gia, nguyenlieu));
                    }
                }
                else {
                    errot.setVisibility(View.VISIBLE);
                    trolai.setVisibility(View.VISIBLE);

                    tvChinh.setVisibility(View.INVISIBLE);
                    txtGia.setVisibility(View.INVISIBLE);
                    txtNguyenLieu.setVisibility(View.INVISIBLE);
                    txtTen.setVisibility(View.INVISIBLE);
                    anh.setVisibility(View.INVISIBLE);
                    doiMon.setVisibility(View.INVISIBLE);
                    ok.setVisibility(View.INVISIBLE);

                }
                cursor.close();
                }
                catch (Exception e)
                {
                    Toast.makeText(AnGiHienThi1.this,"Lỗi tìm kiếm",Toast.LENGTH_SHORT).show();
                }
            }
        else
        {
            try {

                data=new ArrayList<>();
                adapter=new MyAdapter(AnGiHienThi1.this,data);
                database = Database.initDatabase(AnGiHienThi1.this, DB_NAME);
                Cursor cursor = database.rawQuery("Select * from MonAn where Gia <= '" + giaMax +"' and Loai like '%"+ loai +"%' ",null);
                data.clear();
                dem=cursor.getCount();
                if (dem>0) {
                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(0);
                        String name = cursor.getString(1);
                        String loai = cursor.getString(2);
                        int gia = cursor.getInt(3);
                        String nguyenlieu = cursor.getString(4).toString();
                        data.add(new MonAnModel(id, name, loai, gia, nguyenlieu));
                    }
                }
                else {
                    errot.setVisibility(View.VISIBLE);
                    trolai.setVisibility(View.VISIBLE);

                    tvChinh.setVisibility(View.INVISIBLE);
                    txtGia.setVisibility(View.INVISIBLE);
                    txtNguyenLieu.setVisibility(View.INVISIBLE);
                    txtTen.setVisibility(View.INVISIBLE);
                    anh.setVisibility(View.INVISIBLE);
                    doiMon.setVisibility(View.INVISIBLE);
                    ok.setVisibility(View.INVISIBLE);

                }
                cursor.close();
            }
            catch (Exception e)
            {
                Toast.makeText(AnGiHienThi1.this,"Lỗi tìm kiếm",Toast.LENGTH_SHORT).show();
            }
        }
    }

    void randomMon() {
        errot.setVisibility(View.INVISIBLE);
        trolai.setVisibility(View.INVISIBLE);

        vt = random.nextInt(dem);
        txtTen.setText(data.get(vt).getTenMon().toString());
        giaMon = data.get(vt).getGia() * soNguoi;
        txtGia.setText("Giá :" +String.valueOf(giaMon)+" VNĐ");
        anh.setImageResource(data.get(vt).idAnh);
        txtNguyenLieu.setText("Nguyên liệu:\n"+data.get(vt).getNguyenLieu().toString());
    }
    void getTT()
    {
        Intent intent = getIntent();
        tongTien=intent.getIntExtra("tien",0);
        soNguoi=intent.getIntExtra("soNguoi",1);
        loai=intent.getStringExtra("loai");
        nganSach=tongTien/soNguoi;
    }
    void doiMon()
    {
        try
        {
            if(dem>0)
            {
                data.remove(vt);
                dem--;
               randomMon();
            }
            else {
                errot.setVisibility(View.VISIBLE);
                trolai.setVisibility(View.VISIBLE);

                tvChinh.setVisibility(View.INVISIBLE);
                txtGia.setVisibility(View.INVISIBLE);
                txtNguyenLieu.setVisibility(View.INVISIBLE);
                txtTen.setVisibility(View.INVISIBLE);
                anh.setVisibility(View.INVISIBLE);
                doiMon.setVisibility(View.INVISIBLE);
                ok.setVisibility(View.INVISIBLE);
            }
        }
        catch (Exception e)
        {
            errot.setVisibility(View.VISIBLE);
            trolai.setVisibility(View.VISIBLE);

            tvChinh.setVisibility(View.INVISIBLE);
            txtGia.setVisibility(View.INVISIBLE);
            txtNguyenLieu.setVisibility(View.INVISIBLE);
            txtTen.setVisibility(View.INVISIBLE);
            anh.setVisibility(View.INVISIBLE);
            doiMon.setVisibility(View.INVISIBLE);
            ok.setVisibility(View.INVISIBLE);
        }
    }
    private void insertData() {
        db=openOrCreateDatabase("anGiCungDuoc.sqlite",MODE_PRIVATE,null);

        String sql= " INSERT INTO ThongKe VALUES (date('now'),"+giaMon+") ";
        String sql2 = " UPDATE ThongKe SET Tien=Tien+ "+giaMon+" WHERE Date=date('now')";
        try {
            db.execSQL(sql);
            Toast.makeText(this, "Đã thêm chi tiêu", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            db.execSQL(sql2);
            Toast.makeText(this, "Đã cập nhật chi tiêu", Toast.LENGTH_SHORT).show();
        }
    }
}