package com.example.appangicungduoc.DanhSach;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.appangicungduoc.Database;
import com.example.appangicungduoc.R;

public class MainActivitySuaMon extends AppCompatActivity {

    EditText txtTenMon,txtGia,txtNguyenLieu;
    RadioButton radioButtonChinh,radioButtonPhu,radioButtoncanh;
    Button btsuatt;
    String loai;

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_mon);
        anhxa();
        setTT();

        checkRb();
        btsuatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivitySuaMon.this);
                builder.setMessage("Bạn có muốn sửa dữ liệu này không?").setPositiveButton("Có",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    suaTT(id);
                                    Toast.makeText(MainActivitySuaMon.this, "Đã sửa!" , Toast.LENGTH_LONG).show();
                                    finish();
                                    Intent intent= new Intent(MainActivitySuaMon.this, DSMonActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }
                                catch (Exception e) {
                                    Toast.makeText(MainActivitySuaMon.this, "sửa không thành công!", Toast.LENGTH_LONG).show();
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
    }
    private void anhxa()
    {
        txtTenMon=findViewById(R.id.txtSuaTen);
        txtGia=findViewById(R.id.txtSuaGia);
        txtNguyenLieu=findViewById(R.id.txtSuaNguyenLieu);
        radioButtoncanh=findViewById(R.id.rbSuaCanh);
        radioButtonChinh=findViewById(R.id.rbSuaChinh);
        radioButtonPhu=findViewById(R.id.rbSuaPhu);
        btsuatt=findViewById(R.id.btOkSua);
    }
    void setTT()
    {
        Intent intent = getIntent();

        id=intent.getIntExtra("id",0);

        txtTenMon.setText(intent.getStringExtra("ten"));
        loai=intent.getStringExtra("loai");
        txtGia.setText(intent.getStringExtra("gia"));
        txtNguyenLieu.setText(intent.getStringExtra("nguyenLieu"));
        if(loai.equals(radioButtonChinh.getText().toString())==true)
        {
            radioButtonChinh.setChecked(true);
        }
        if(loai.equals(radioButtonPhu.getText().toString())==true)
        {
            radioButtonPhu.setChecked(true);
        }
        if(loai.equals(radioButtoncanh.getText().toString())==true)
        {
            radioButtoncanh.setChecked(true);
        }
    }
    private void checkRb()
    {
        radioButtonPhu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    loai=radioButtonPhu.getText().toString().trim();
                }
            }
        });
        radioButtoncanh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    loai=radioButtoncanh.getText().toString().trim();
                }
            }
        });
        radioButtonChinh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    loai=radioButtonChinh.getText().toString().trim();
                }
            }

        });
    }
    void suaTT(int id)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenMon", txtTenMon.getText().toString());
        contentValues.put("Loai", loai);
        contentValues.put("Gia", txtGia.getText().toString());
        contentValues.put("NguyenLieu",txtNguyenLieu.getText().toString());

        SQLiteDatabase database = Database.initDatabase(this, "anGiCungDuoc.sqlite");
        database.update("MonAn", contentValues, "ID = ?", new String[] {id + ""});

    }
}