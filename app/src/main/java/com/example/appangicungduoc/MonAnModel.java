package com.example.appangicungduoc;


import android.graphics.Bitmap;

import java.sql.Blob;

public class MonAnModel {
    int id;
    String tenMon;
    String loai;
    int gia;
    String nguyenLieu;
    public int idAnh;

    public MonAnModel(int id,String tenMon, String loai, int gia, String nguyenLieu) {
        this.id=id;
        this.tenMon = tenMon;
        this.loai = loai;
        this.gia = gia;
        this.nguyenLieu = nguyenLieu;

       loadimg(loai);

    }

    public void loadimg(String loai)
    {
        if(loai.equals("Món chính")==true)
            this.idAnh=R.drawable.chinh_removebg_preview;
        if(loai.equals("Món phụ")==true)
            this.idAnh=R.drawable.phu_removebg_preview;
        if(loai.equals("Món rau")==true)
            this.idAnh=R.drawable.canh_removebg_preview;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(String nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public int getIdAnh() {
        return idAnh;
    }

    public void setIdAnh(int idAnh) {
        this.idAnh = idAnh;
    }
}
