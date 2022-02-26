package application.Models;

import java.io.Serializable;

public class NhanSu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public void setMa(String ma) throws Exception {
		if(ma.trim().equals(""))
			throw new Exception("Ma khong duoc bo trong");
		this.ma = ma;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) throws Exception {
		if(ten.trim().equals(""))
			throw new Exception("Ten khong duoc bo trong");
		this.ten = ten;
	}

	public String getGtinh() {
		return gtinh;
	}

	public void setGtinh(String gtinh) throws Exception {
		if(gtinh.trim().equals(""))
			throw new Exception("Gioi tinh khong duoc bo trong");
		this.gtinh = gtinh;
	}

	public String getNgsinh() {
		return ngsinh;
	}

	public void setNgsinh(String ngsinh) throws Exception {
		if(ngsinh.trim().equals(""))
			throw new Exception("Ngay sinh khong duoc bo trong");
		this.ngsinh = ngsinh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) throws Exception {
		if(sdt.trim().equals(""))
			throw new Exception("So dien thoai khong duoc bo trong");
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		if(email.trim().equals(""))
			throw new Exception("Email khong duoc bo trong");
		this.email = email;
	}

	public String getDchi() {
		return dchi;
	}

	public void setDchi(String dchi) throws Exception {
		if(dchi.trim().equals(""))
			throw new Exception("Dia chi khong duoc bo trong");
		this.dchi = dchi;
	}

	public String getBophan() {
		return bophan;
	}

	public void setBophan(String bophan) throws Exception {
		if(bophan.trim().equals(""))
			throw new Exception("Bo phan khong duoc bo trong");
		this.bophan = bophan;
	}

	public String getCdanh() {
		return cdanh;
	}

	public void setCdanh(String cdanh) throws Exception {
		if(cdanh.trim().equals(""))
			throw new Exception("Chuc danh khong duoc bo trong");
		this.cdanh = cdanh;
	}

	public String getCvu() {
		return cvu;
	}

	public void setCvu(String cvu) throws Exception {
		if(cvu.trim().equals(""))
			throw new Exception("Chuc vu khong duoc bo trong");
		this.cvu = cvu;
	}

	public String getNgvaolam() {
		return ngvaolam;
	}

	public void setNgvaolam(String ngvaolam) throws Exception {
		if(ngvaolam.trim().equals(""))
			throw new Exception("Ngay vao lam khong duoc bo trong");
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
