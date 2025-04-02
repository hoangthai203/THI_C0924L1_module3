package com.codegym.demo.model;

public class Student {
    private String maHS;
    private String hoTen;
    private String lop;

    public Student(String maHS, String hoTen, String lop) {
        this.maHS = maHS;
        this.hoTen = hoTen;
        this.lop = lop;
    }

    public String getMaHS() {
        return maHS;
    }

    public void setMaHS(String maHS) {
        this.maHS = maHS;
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
