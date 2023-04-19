package edu.huflit.myapp.Model;

public class TapTruyen {
    private String tenTap;
    private Integer Id;


    public TapTruyen(String tenTap, int id) {
        this.tenTap = tenTap;
        Id = id;
    }

    public TapTruyen() {
    }

    public String getTenTap() {
        return tenTap;
    }

    public void setTenTap(String tenTap) {
        this.tenTap = tenTap;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
