package edu.huflit.myapp.Model;

public class TruyenTranh {
    private String tenTruyen;
    private String tenChap;
    private String LinkAnh;

    public String getThLoai() {
        return thLoai;
    }

    public void setThLoai(String thLoai) {
        this.thLoai = thLoai;
    }

    private String thLoai;
    private String noiDungTruyen;

    public String getNoiDungTruyen() {
        return noiDungTruyen;
    }

    public void setNoiDungTruyen(String noiDungTruyen) {
        this.noiDungTruyen = noiDungTruyen;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    private String tacGia;

    public int getIdTruyen() {
        return idTruyen;
    }


    public TruyenTranh(String tenTruyen, String tenChap, String noiDungTruyen, String thLoai, String linkAnh, String tacGia, int idTruyen, int yeuThich) {

        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        LinkAnh = linkAnh;
        this.tacGia = tacGia;
        this.idTruyen = idTruyen;
        this.noiDungTruyen = noiDungTruyen;
        this.thLoai = thLoai;
        this.yeuThich = yeuThich;
    }

    public void setIdTruyen(int idTruyen) {
        this.idTruyen = idTruyen;
    }

    private int idTruyen;
    private int yeuThich;

    public int getYeuThich() {
        return yeuThich;
    }

    public void setYeuThich(int yeuThich) {
        this.yeuThich = yeuThich;
    }

    public TruyenTranh() {

    }
    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }
}
