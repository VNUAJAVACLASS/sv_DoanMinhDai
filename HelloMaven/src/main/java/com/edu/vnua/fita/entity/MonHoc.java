package com.edu.vnua.fita.entity;

public class MonHoc {
    private String maMon;
    private String tenMon;
    private int nhom;
    private int to;
    private int soTinChi;
    private int soTietLT;
    private int soTietTH;
    private String lopHoc;
    private int tietBD;
    private int soTiet;
    private String phong;
    private String gv;

    public MonHoc() {}

    public MonHoc(String maMon, String tenMon, int nhom,int to, int soTinChi, int soTietLT, int soTietTH, String lopHoc) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.nhom = nhom;
        this.to = to;
        this.soTinChi = soTinChi;
        this.soTietLT = soTietLT;
        this.soTietTH = soTietTH;
        this.lopHoc = lopHoc;
    }

    public MonHoc(MonHoc orther) {
        this.maMon = orther.maMon;
        this.tenMon = orther.tenMon;
        this.nhom = orther.nhom;
        this.to = orther.to;
        this.soTinChi = orther.soTinChi;
        this.soTietLT = orther.soTietLT;
        this.soTietTH = orther.soTietTH;
        this.lopHoc = orther.lopHoc;
    }

    public String getMaMon() {
        return maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public int getNhom() {
        return nhom;
    }

    public int getTo() { return to;}

    public int getSoTinChi() {
        return soTinChi;
    }

    public int getSoTietLT() {
        return soTietLT;
    }

    public int getSoTietTH() {
        return soTietTH;
    }

    public String getLopHoc() {
        return lopHoc;
    }

    public int getTietBD() {
        return tietBD;
    }

    public int getSoTiet() {
        return soTiet;
    }

    public String getPhong() {
        return phong;
    }

    public String getGv() {
        return gv;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public void setSoTiet(int soTiet) {
        this.soTiet = soTiet;
    }

    public void setTietBD(int tietBD) {
        this.tietBD = tietBD;
    }

    public void setGv(String gv) {
        this.gv = gv;
    }
}