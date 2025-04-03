package com.codegym.demo.model;

public class Student {
    private String maHs;
    private String hoTen;
    private String lop;

    public Student() {
    }

    public Student(String maHs, String hoTen, String lop) {
        this.maHs = maHs;
        this.hoTen = hoTen;
        this.lop = lop;
    }

    public String getMaHs() {
        return maHs;
    }

    public void setMaHs(String maHs) {
        this.maHs = maHs;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}