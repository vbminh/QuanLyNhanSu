package application.Models;

public class Luong {
	private String ma, ten, bophan,chucdanh, chucvu, songaycong, heso;
	private double luongcb, phucap;
	private long tongluong;
	
	public Luong() {
		// TODO Auto-generated constructor stub
	}

	public Luong(String ma) {
		super();
		this.ma = ma;
	}

	public Luong(String ma, String ten, String bophan,String chucdanh, String chucvu, double luongcb, String heso, double phucap,
			String songaycong) {
		super();
		this.ma = ma;
		this.ten = ten;
		this.bophan = bophan;
		this.chucvu = chucvu;
		this.luongcb = luongcb;
		this.heso = heso;
		this.phucap = phucap;
		this.songaycong = songaycong;
		this.chucdanh = chucdanh;
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

	public String getBophan() {
		return bophan;
	}

	public void setBophan(String bophan) {
		this.bophan = bophan;
	}

	public String getChucvu() {
		return chucvu;
	}

	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}

	public double getLuongcb() {
		return luongcb;
	}

	public void setLuongcb(double luongcb) {
		this.luongcb = luongcb;
	}

	public String getHeso() {
		return heso;
	}

	public void setHeso(String heso) {
		this.heso = heso;
	}

	public double getPhucap() {
		return phucap;
	}

	public void setPhucap(double phucap) {
		this.phucap = phucap;
	}

	public String getSongaycong() {
		return songaycong;
	}

	public void setSongaycong(String songaycong) {
		this.songaycong = songaycong;
	}

	public String getChucdanh() {
		return chucdanh;
	}

	public void setChucdanh(String chucdanh) {
		this.chucdanh = chucdanh;
	}

	public long getTongluong() {
		if(chucdanh.equals("Nhan vien"))
			return (long) (Math.round((luongcb * Integer.parseInt(songaycong) / 30 + phucap) * 100.0)/100.0);
		return (long) (Math.round((luongcb * Double.parseDouble(heso) + phucap)*100.0)/100.0);
	}
	
}
