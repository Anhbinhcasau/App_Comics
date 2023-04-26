package edu.huflit.myapp.Model;

public class TapTruyen {
    private Integer tenTap;
    private Integer Id;

    public TapTruyen(Integer tenTap, Integer id, Integer idTruyen) {
        this.tenTap = tenTap;
        Id = id;
        IdTruyen = idTruyen;
    }


    public Integer getIdTruyen() {
        return IdTruyen;
    }

    public void setIdTruyen(Integer idTruyen) {
        IdTruyen = idTruyen;
    }

    private Integer IdTruyen;





    public TapTruyen() {
    }

    public Integer getTenTap() {
        return tenTap;
    }

    public void setTenTap(Integer tenTap) {
        this.tenTap = tenTap;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
