
package InHoaDon;


import java.util.List;

public class ParameterHoaDon {
    String tenNhanVien;
    String maHoaDon;
    String tongTien;
    String ngay;
    String sdt;
    List<FieldHoaDon> listFields;
    
    public ParameterHoaDon() {

    }

    public ParameterHoaDon(String tenNhanVien, String maHoaDon, String tongTien, String ngay, String sdt, List<FieldHoaDon> listFields) {
        this.tenNhanVien = tenNhanVien;
        this.maHoaDon = maHoaDon;
        this.tongTien = tongTien;
        this.ngay = ngay;
        this.sdt = sdt;
        this.listFields = listFields;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public List<FieldHoaDon> getListFields() {
        return listFields;
    }

    public void setListFields(List<FieldHoaDon> listFields) {
        this.listFields = listFields;
    }
    
    
}
