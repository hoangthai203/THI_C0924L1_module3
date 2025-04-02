package com.codegym.demo.model;

import java.util.Date;

public class BorrowRecord {
    private String maMuon;
    private String maSach;
    private String maHS;
    private Date ngayMuon;
    private Date ngayTra;
    private boolean trangThai;

    public BorrowRecord(String maMuon, String maSach, String maHS, Date ngayMuon, Date ngayTra, boolean trangThai) {
        this.maMuon = maMuon;
        this.maSach = maSach;
        this.maHS = maHS;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }

    public String getMaMuon() {
        return maMuon;
    }

    public void setMaMuon(String maMuon) {
        this.maMuon = maMuon;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaHS() {
        return maHS;
    }

    public void setMaHS(String maHS) {
        this.maHS = maHS;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
