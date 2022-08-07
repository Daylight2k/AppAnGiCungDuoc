package com.example.appangicungduoc.DanhSach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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

public class MainActivityThemMon extends AppCompatActivity {


    EditText txtTenMon,txtGia,txtNguyenLieu;
    RadioButton radioButtonChinh,radioButtonPhu,radioButtoncanh;
    Button btthem;
    String loai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_mon);

        anhxa();
        checkRb();
        btthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkTT()==true) {
                    themMon();
                    finish();
                    Intent intent = new Intent(MainActivityThemMon.this, DSMonActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivityThemMon.this, "Thêm không thành công,kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void anhxa()
    {
        txtTenMon=findViewById(R.id.txtThemTenMon);
        txtGia=findViewById(R.id.txtThemGia);
        txtNguyenLieu=findViewById(R.id.txtThemNguyenLieu);
        radioButtoncanh=findViewById(R.id.rbCanh);
        radioButtonChinh=findViewById(R.id.rbChinh);
        radioButtonPhu=findViewById(R.id.rbPhu);
        btthem=findViewById(R.id.btOkThem);
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

    Boolean checkTT()
    {
        if (txtTenMon.getText().toString().equals("") == true || txtGia.getText().toString().equals("")==true || loai.equals("")==true)
        {
            return false;
        }
        else
            return true;
    }

    void themMon()
    {
            String tenMon = txtTenMon.getText().toString();
            int gia = Integer.parseInt(txtGia.getText().toString());
            String nguyenLieu = txtNguyenLieu.getText().toString();

            ContentValues contentValues = new ContentValues();
            contentValues.put("TenMon", tenMon);
            contentValues.put("Loai", loai);
            contentValues.put("Gia", gia);
            contentValues.put("NguyenLieu", nguyenLieu);
            SQLiteDatabase database = Database.initDatabase(MainActivityThemMon.this, "anGiCungDuoc.sqlite");
            database.insert("MonAn", null, contentValues);
            Toast.makeText(MainActivityThemMon.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

    }
}