package application.Models;

public class TuyenDung {
	private String ma, ten, gtinh, ngsinh, sdt, email, dchi, trinhdo,truong, cnganh, xeploai, vtungtuyen;
	
	public TuyenDung() {
	}
	
	public TuyenDung(String ma) {
		this.ma = ma;
	}

	public TuyenDung(String ma, String ten, String gtinh, String ngsinh, String sdt, String email, String dchi,
			String trinhdo, String truong, String cnganh,String xeploai, String vtungtuyen) {
		super();
		this.ma = ma;
		this.ten = ten;
		this.gtinh = gtinh;
		this.ngsinh = ngsinh;
		this.sdt = sdt;
		this.email = email;
		this.dchi = dchi;
		this.trinhdo = trinhdo;
		this.truong = truong;
		this.cnganh = cnganh;
		this.xeploai = xeploai;
		this.vtungtuyen = vtungtuyen;
	}

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getGtinh() {
		return gtinh;
	}

	public void setGtinh(String gtinh) {
		this.gtinh = gtinh;
	}

	public String getNgsinh() {
		return ngsinh;
	}

	public void setNgsinh(String ngsinh) {
		this.ngsinh = ngsinh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDchi() {
		return dchi;
	}

	public void setDchi(String dchi) {
		this.dchi = dchi;
	}
	
	public String getTrinhdo() {
		return trinhdo;
	}

	public void setTrinhdo(String trinhdo) {
		this.trinhdo = trinhdo;
	}

	public String getTruong() {
		return truong;
	}

	public void setTruong(String truong) {
		this.truong = truong;
	}

	public String getCnganh() {
		return cnganh;
	}

	public void setCnganh(String cnganh) {
		this.cnganh = cnganh;
	}

	public String getXeploai() {
		return xeploai;
	}

	public void setXeploai(String xeploai) {
		this.xeploai = xeploai;
	}

	public String getVtungtuyen() {
		return vtungtuyen;
	}

	public void setVtungtuyen(String vtungtuyen) {
		this.vtungtuyen = vtungtuyen;
	}

	@Override
	public boolean equals(Object obj) {
		TuyenDung td = (TuyenDung)obj;
		return this.ma.equals(td.ma);
	}
}
