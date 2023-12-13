package Entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author ThaiHiep
 */
public class HoaDon {
    private String maHoaDon;
    private LocalDate ngayLap;
    private NhanVien nhanVien;
    private KhachHang khachHanh;
    
    private double  soTienKhachDua;
   private double tongTien; 
       private double tienBanDau;
    private double vat;
    private double tienThua;

    public HoaDon(String maHoaDon, LocalDate ngayLap, NhanVien nhanVien, KhachHang khachHanh, double soTienKhachDua, double tongTien, double tienBanDau, double vat, double tienThua) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.nhanVien = nhanVien;
        this.khachHanh = khachHanh;
        this.soTienKhachDua = soTienKhachDua;
        this.tongTien = tongTien;
        this.tienBanDau = tienBanDau;
        this.vat = vat;
        this.tienThua = tienThua;
    }

    public double getSoTienKhachDua() {
        return soTienKhachDua;
    }

    public void setSoTienKhachDua(double soTienKhachDua) {
        this.soTienKhachDua = soTienKhachDua;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getTienBanDau() {
        return tienBanDau;
    }

    public void setTienBanDau(double tienBanDau) {
        this.tienBanDau = tienBanDau;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getTienThua() {
        return tienThua;
    }

    public void setTienThua(double tienThua) {
        this.tienThua = tienThua;
    }



   
    
    public HoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public HoaDon() {
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHanh() {
        return khachHanh;
    }

    public void setKhachHanh(KhachHang khachHanh) {
        this.khachHanh = khachHanh;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.maHoaDon);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HoaDon other = (HoaDon) obj;
        return Objects.equals(this.maHoaDon, other.maHoaDon);
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", ngayLap=" + ngayLap + ", nhanVien=" + nhanVien + ", khachHanh=" + khachHanh + ", soTienKhachDua=" + soTienKhachDua + ", tongTien=" + tongTien + ", tienBanDau=" + tienBanDau + ", vat=" + vat + ", tienThua=" + tienThua + '}';
    }



   
    
    
}
