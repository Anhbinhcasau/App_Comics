package edu.huflit.myapp.Model;

public class TruyenTranh {
    private String tenTruyen,tenChap,LinkAnh;

    public Integer getIdTruyen() {
        return idTruyen;
    }

    public TruyenTranh(String tenTruyen, String tenChap, String linkAnh, Integer idTruyen) {
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        LinkAnh = linkAnh;
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
