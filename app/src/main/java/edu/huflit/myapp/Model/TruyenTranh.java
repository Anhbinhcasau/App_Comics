package edu.huflit.myapp.Model;

public class TruyenTranh {
    private String tenTruyen;
    private String LinkAnh;
    private String noiDungTruyen;

    public TruyenTranh(String tieuDe, String noiDung, String img, String tacGia, int id) {
        this.tenTruyen = tieuDe;
        this.LinkAnh = img;
        this.tacGia = tacGia;
        this.idTruyen = id;
        this.noiDungTruyen = noiDung;
    }


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




    public void setIdTruyen(int idTruyen) {
        this.idTruyen = idTruyen;
    }

    private int idTruyen;

    public TruyenTranh() {

    }
    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }


    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }
}
