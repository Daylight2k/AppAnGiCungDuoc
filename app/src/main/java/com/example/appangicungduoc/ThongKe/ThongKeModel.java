package com.example.appangicungduoc.ThongKe;

public class ThongKeModel {
    String ngay;
    int tien;

    public ThongKeModel(String ngay, int tien) {
        this.ngay = ngay;
        this.tien = tien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }
}
