
package Entity;

import java.util.Objects;

public class TaiKhoan {
    private String tenTK;
    private String matKhau;
    private String maXacNhan;
    private String trangThai;

    public TaiKhoan(String tenTK, String matKhau, String maXacNhan, String trangThai) {
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.maXacNhan = maXacNhan;
        this.trangThai = trangThai;
    }

    public TaiKhoan(String tenTK, String matKhau, String trangThai) {
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
    }

    public TaiKhoan(String tenTK, String matKhau) {
        this.tenTK = tenTK;
        this.matKhau = matKhau;
    }
    
    public TaiKhoan() {
    }

    public TaiKhoan(String tenTK) {
        this.tenTK = tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setMaXacNhan(String maXacNhan) {
        this.maXacNhan = maXacNhan;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenTK() {
        return tenTK;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getMaXacNhan() {
        return maXacNhan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.tenTK);
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
        final TaiKhoan other = (TaiKhoan) obj;
        return Objects.equals(this.tenTK, other.tenTK);
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "tenTK=" + tenTK + ", matKhau=" + matKhau + ", maXacNhan=" + maXacNhan + ", trangThai=" + trangThai + '}';
    }
    
    
    
           
   
    
}
