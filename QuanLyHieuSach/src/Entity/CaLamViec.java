
package Entity;

import java.util.Objects;

/**
 *
 * @author ThaiHiep
 */
public class CaLamViec {
    private String maCa ;
    private String tenCa;
    private String thoiGianBatDau;
    private String thoiGianKetThuc;

    public CaLamViec(String maCa, String tenCa, String thoiGianBatDau, String thoiGianKetThuc) {
        this.maCa = maCa;
        this.tenCa = tenCa;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    
    public CaLamViec(String maCa) {
        this.maCa = maCa;
    }

    public CaLamViec() {
    }

    public void setMaCa(String maCa) {
        this.maCa = maCa;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public void setThoiGianKetThuc(String thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }
    public String getMaCa() {
        return maCa;
    }

    public String getTenCa() {
        return tenCa;
    }

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public String getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.maCa);
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
        final CaLamViec other = (CaLamViec) obj;
        return Objects.equals(this.maCa, other.maCa);
    }

    @Override
    public String toString() {
        return "CaLamViec{" + "maCa=" + maCa + ", tenCa=" + tenCa + ", thoiGianBatDau=" + thoiGianBatDau + ", thoiGianKetThuc=" + thoiGianKetThuc + '}';
    }

    
    
}
