package edu.huflit.myapp.Model;

public class TheLoaiTruyen {
    private String tenTL;
    private int hinhTL;

    public TheLoaiTruyen(String tenTL, int hinhTL) {
        this.tenTL = tenTL;
        this.hinhTL = hinhTL;
    }

    public String getTenTL() {
        return tenTL;
    }

    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
    }

    public int getHinhTL() {
        return hinhTL;
    }

    public void setHinhTL(int hinhTL) {
        this.hinhTL = hinhTL;
    }
}
