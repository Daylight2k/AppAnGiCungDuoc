package com.example.appangicungduoc.DanhSach;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appangicungduoc.Database;
import com.example.appangicungduoc.R;

public class ThongTinMon extends AppCompatActivity {

    TextView txtTen,txtLoai,txtGia,txtNguyenLieu;
    Button btSua,btXoa;
    ImageView imageView;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_mon);
        anhXa();
        setTT();
        btXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThongTinMon.this);
                builder.setMessage("Bạn có muốn xoá dữ liệu này không?").setPositiveButton("Có",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    xoa(id);
                                    Toast.makeText(ThongTinMon.this, "Đã xóa!" , Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(ThongTinMon.this,DSMonActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                                catch (Exception e) {
                                    Toast.makeText(ThongTinMon.this, "Xóa không thành công!", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                ).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Thoát
                    }
                }).show();
            }
        });
        btSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ThongTinMon.this, MainActivitySuaMon.class);
                intent.putExtra("id",id);
                intent.putExtra("ten", txtTen.getText().toString());
                intent.putExtra("loai", txtLoai.getText().toString());
                intent.putExtra("gia", txtGia.getText().toString());
                intent.putExtra("nguyenLieu", txtNguyenLieu.getText().toString());
                finish();
                startActivity(intent);
            }
        });
    }

    void anhXa()
    {
        imageView=findViewById(R.id.imageView);
        txtTen=findViewById(R.id.txtTTTenMon);
        txtLoai=findViewById(R.id.txtTTLoai);
        txtGia=findViewById(R.id.txtTTGia);
        txtNguyenLieu=findViewById(R.id.txtTTNguyenLieu);
        btXoa=findViewById(R.id.btXoa);
        btSua=findViewById(R.id.btSua);

    }
    void setTT()
    {
        Intent intent = getIntent();

        id=intent.getIntExtra("id",0);
        imageView.setImageResource(intent.getIntExtra("anh", 0));
        txtTen.setText(intent.getStringExtra("ten"));
        txtLoai.setText(intent.getStringExtra("loai"));
        txtGia.setText(intent.getStringExtra("gia"));
        txtNguyenLieu.setText(intent.getStringExtra("nguyenLieu"));
    }
void xoa(int idMon)
    {
        SQLiteDatabase database = Database.initDatabase(ThongTinMon.this,"anGiCungDuoc.sqlite");
        database.delete("MonAn", "ID = ?", new String[]{idMon + ""});


    }
}