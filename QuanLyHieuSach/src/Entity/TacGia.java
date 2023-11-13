package Entity;

import java.util.Objects;

public class TacGia {
	private String maTacGia;
	private String tenTacGia;
	public TacGia(String maTacGia, String tenTacGia) {
		super();
		this.maTacGia = maTacGia;
		this.tenTacGia = tenTacGia;
	}
	public TacGia(String maTacGia) {
		super();
		this.maTacGia = maTacGia;
		
	}
	public TacGia() {
		super();
	}
	public String getMaTacGia() {
		return maTacGia;
	}
	public void setMaTacGia(String maTacGia) {
		this.maTacGia = maTacGia;
	}
	public String getTenTacGia() {
		return tenTacGia;
	}
	public void setTenTacGia(String tenTacGia) {
		this.tenTacGia = tenTacGia;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maTacGia);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TacGia other = (TacGia) obj;
		return Objects.equals(maTacGia, other.maTacGia);
	}
	@Override
	public String toString() {
		return "TacGia [maTacGia=" + maTacGia + ", tenTacGia=" + tenTacGia + "]";
	}

	
	
}
