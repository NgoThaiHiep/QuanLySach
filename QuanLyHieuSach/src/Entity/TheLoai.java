
package Entity;

import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author ThaiHiep
 */
public class TheLoai {
    private String maTheLoai;
    private String tenTheLoai;

    public TheLoai() {
    }

    public TheLoai(String maTheLoai, String tenTheLoai) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
    }

    public TheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
    private static final Logger LOG = Logger.getLogger(TheLoai.class.getName());

    public static Logger getLOG() {
        return LOG;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.maTheLoai);
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
        final TheLoai other = (TheLoai) obj;
        return Objects.equals(this.maTheLoai, other.maTheLoai);
    }

    @Override
    public String toString() {
        return "TheLoai{" + "maTheLoai=" + maTheLoai + ", tenTheLoai=" + tenTheLoai + '}';
    }
    
    
}
