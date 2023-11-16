
package Entity;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author ThaiHiep
 */
public class NhanVien {
    private String maNV;
    private String hoTenNhanVien;
     private String CCCD;
    private  LocalDate  ngaySinh;
    private boolean gioiTinh;
    private String email;
    private String soDienThoai;
    private String diaChi;
    private ChucVu chucVu;
    private TaiKhoan taiKhoan;
    private CaLamViec caLam;
    private String trangThai;
    private String hinhAnh;
   
    public NhanVien(String maNV, String hoTenNhanVien, String CCCD, LocalDate ngaySinh, boolean gioiTinh, String email, String soDienThoai, ChucVu chucVu, TaiKhoan taiKhoan, CaLamViec caLam, String trangThai, String hinhAnh,String diaChi) {
        this.maNV = maNV;
        this.hoTenNhanVien = hoTenNhanVien;
        this.CCCD = CCCD;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.chucVu = chucVu;
        this.taiKhoan = taiKhoan;
        this.caLam = caLam;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
        this.diaChi = diaChi;
    }

    public NhanVien(String maNV, String hoTenNhanVien, ChucVu chucVu) {
        this.maNV = maNV;
        this.hoTenNhanVien = hoTenNhanVien;
        this.chucVu = chucVu;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getCCCD() {
        return CCCD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

   
    public NhanVien() {
    }

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    
    public String getMaNV() {
        return maNV;
    }



    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public TaiKhoan getDangNhap() {
        return taiKhoan;
    }

    public String getHoTenNhanVien() {
        return hoTenNhanVien;
    }

    public void setHoTenNhanVien(String hoTenNhanVien) {
        this.hoTenNhanVien = hoTenNhanVien;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public CaLamViec getCaLam() {
        return caLam;
    }

    public void setCaLam(CaLamViec caLam) {
        this.caLam = caLam;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }


    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDangNhap(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.maNV);
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
        final NhanVien other = (NhanVien) obj;
        return Objects.equals(this.maNV, other.maNV);
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNV=" + maNV + ", hoTenNhanVien=" + hoTenNhanVien + ", ngaySinh=" + ngaySinh + ", soDienThoai=" + soDienThoai + ", gioiTinh=" + gioiTinh + ", email=" + email + ", chucVu=" + chucVu + ", taiKhoan=" + taiKhoan + ", caLam=" + caLam + ", trangThai=" + trangThai + ", hinhAnh=" + hinhAnh + '}';
    }
    
    
    
}
