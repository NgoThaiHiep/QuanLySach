
package Entity;
import java.time.LocalDate;

/**
 *
 * @author Thái Hiệp
 */
public class HangCho {
    private KhachHang khachHang;
    private SanPham sanPham;
    private int soLuong;
    private LocalDate ngayMua;

    public HangCho() {
    }

    public HangCho(KhachHang khachHang, SanPham sanPham, int soLuong, LocalDate ngayMua) {
        this.khachHang = khachHang;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.ngayMua = ngayMua;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public LocalDate getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(LocalDate ngayMua) {
        this.ngayMua = ngayMua;
    }

    @Override
    public String toString() {
        return "HangCho{" + "khachHang=" + khachHang + ", sanPham=" + sanPham + ", soLuong=" + soLuong + ", ngayMua=" + ngayMua + '}';
    }
    
}
