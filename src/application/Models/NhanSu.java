package application.Models;

public class NhanSu {
	private String ma, ten, gtinh, ngsinh, sdt, email, dchi, bophan,cdanh, cvu, ngvaolam, ghichu;
	
	public NhanSu() {
	}
	
	public NhanSu(String ma) {
		this.ma = ma;
	}

	public NhanSu(String ma, String ten, String gtinh, String ngsinh, String sdt, String email, String dchi,
			String bophan, String cdanh, String cvu,String ngvaolam, String ghichu) {
		super();
		this.ma = ma;
		this.ten = ten;
		this.gtinh = gtinh;
		this.ngsinh = ngsinh;
		this.sdt = sdt;
		this.email = email;
		this.dchi = dchi;
		this.bophan = bophan;
		this.cdanh = cdanh;
		this.cvu = cvu;
		this.ngvaolam = ngvaolam;
		this.ghichu = ghichu;
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

	public String getBophan() {
		return bophan;
	}

	public void setBophan(String bophan) {
		this.bophan = bophan;
	}

	public String getCdanh() {
		return cdanh;
	}

	public void setCdanh(String cdanh) {
		this.cdanh = cdanh;
	}

	public String getCvu() {
		return cvu;
	}

	public void setCvu(String cvu) {
		this.cvu = cvu;
	}

	public String getNgvaolam() {
		return ngvaolam;
	}

	public void setNgvaolam(String ngvaolam) {
		this.ngvaolam = ngvaolam;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	
	@Override
	public boolean equals(Object obj) {
		NhanSu ns = (NhanSu)obj;
		return this.ma.equals(ns.ma);
	}
}
