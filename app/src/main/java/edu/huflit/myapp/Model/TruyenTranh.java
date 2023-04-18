package edu.huflit.myapp.Model;

public class TruyenTranh {
    private String tenTruyen;
    private String tenChap;
    private String LinkAnh;

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    private String tacGia;

    public Integer getIdTruyen() {
        return idTruyen;
    }

    public TruyenTranh(String tenTruyen, String tenChap, String linkAnh, String tacGia, Integer idTruyen) {
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        LinkAnh = linkAnh;
        this.tacGia = tacGia;
        this.idTruyen = idTruyen;
    }

    public void setIdTruyen(Integer idTruyen) {
        this.idTruyen = idTruyen;
    }

    private Integer idTruyen;

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
