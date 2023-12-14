
package Entity;

import java.util.Objects;

/**
 *
 * @author FPTSHOP
 */
public class LoaiVanPhongPham {
    private String maLoaiVanPhongPham;
    private String tenLoaiVanPhongPham;

    public LoaiVanPhongPham() {
    }

    public LoaiVanPhongPham(String tenLoaiVanPhongPham) {
        this.tenLoaiVanPhongPham = tenLoaiVanPhongPham;
    }
    
    

    public LoaiVanPhongPham(String maLoaiVanPhongPham, String tenLoaiVanPhongPham) {
        this.maLoaiVanPhongPham = maLoaiVanPhongPham;
        this.tenLoaiVanPhongPham = tenLoaiVanPhongPham;
    }

    public String getMaLoaiVanPhongPham() {
        return maLoaiVanPhongPham;
    }

    public void setMaLoaiVanPhongPham(String maLoaiVanPhongPham) {
        this.maLoaiVanPhongPham = maLoaiVanPhongPham;
    }

    public String getTenLoaiVanPhongPham() {
        return tenLoaiVanPhongPham;
    }

    public void setTenLoaiVanPhongPham(String tenLoaiVanPhongPham) {
        this.tenLoaiVanPhongPham = tenLoaiVanPhongPham;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.maLoaiVanPhongPham);
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
        final LoaiVanPhongPham other = (LoaiVanPhongPham) obj;
        return Objects.equals(this.maLoaiVanPhongPham, other.maLoaiVanPhongPham);
    }

    @Override
    public String toString() {
        return "LoaiVanPhongPham{" + "maLoaiVanPhongPham=" + maLoaiVanPhongPham + ", tenLoaiVanPhongPham=" + tenLoaiVanPhongPham + '}';
    }
    
    
}
