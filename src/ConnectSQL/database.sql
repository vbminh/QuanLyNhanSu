create database QuanLyNhanSu
go
use QuanLyNhanSu
go
create table Account (
	username varchar(20) not null primary key,
	pass varchar(20) not null,
	permission varchar(20) not null
)
go
insert into Account values('admin','admin','admin'),('quanly1','quanly1','quanly'),('quanly2','quanly2','quanly')

select*from Account

go
create table NhanSu (
	manv varchar(10) not null primary key,
	tennv nvarchar(30) not null,
	gtinh char(5) not null,
	ngsinh date not null,
	sdt char(10) not null,
	email varchar(30) not null,
	dchi nvarchar(30) not null,
	bophan nvarchar(30) not null,
	chucdanh nvarchar(20) not null,
	chucvu nvarchar(20),
	ngvaolam date,
	ghichu nvarchar(30)
)
go

insert into NhanSu values('NV0001','Nguyen Van A','nam','04/22/1968','0987654321','anguyenv@gmail.com','Ha Noi, Viet Nam','Ban Giam Hieu','Hieu truong','Hieu truong','1/1/2010',''),
						('NV0002','Hoang Thi B','nu','12/02/1973','0345678987','bhoangt@gmail.com','Bac Ninh, Viet Nam','Ban Giam Hieu','Pho hieu truong','Pho hieu truong','12/11/2015',''),
						('NV0003','Pham Thi C','nu','01/01/1970','0835025682','cphamt@gmail.com','Hai Phong, Viet Nam','Phong To chuc Hanh chinh','Nhan vien','Truong phong','06/05/2012',''),
						('NV0004','Chu Van D','nam','06/15/1970','0125283745','dchuv@gmail.com','Phu Tho, Viet Nam','Phong Tai chinh Ke toan','Nhan vien','Pho phong','04/01/2021',''),
						('NV0005','Nong Duc E','nam','05/13/1969','0739579369','enongd@gmail.com','Ha Noi, Viet Nam','Bao ve','Nhan vien','','05/11/2017',''),
						('NV0006','Hoang Hoa T','nu','09/27/1975','0857269401','thoangh@gmail.com','Yen Bai, Viet Nam','Khoa Cong nghe thong tin','Giang vien','Pho khoa','11/11/2020',''),
						('NV0007','Le Duc M','nam','10/22/1981','0846793683','mled@gmail.com','Ha Nam, Viet Nam','Khoa Co khi','Giang vien','Truong Khoa','05/16/2016',''),
						('NV0008','De La F','nam','02/06/1978','0269670325','fdel@gmail.com','Ha Noi, Viet Nam','Phong Dao tao','Nhan vien','','07/28/2018',''),
						('NV0009','Pham Van Y','nam','11/11/1973','0584936828','yphamv@gmail.com','Phu Tho, Viet Nam','Bao ve','Nhan vien','','02/14/2013',''),
						('NV0010','Mai Tien G','nam','03/10/1972','0987654321','gmait@gmail.com','Hoa Binh, Viet Nam','Ve sinh','Nhan vien','','04/05/2017',''),
						('NV0011','Vo Thi V','nu','07/29/1977','0124738470','vvot@gmail.com','Thai Nguyen, Viet Nam','Phong Cong tac sinh vien','Nhan vien','Truong phong','01/12/2011',''),
						('NV0012','Chu Linh L','nu','12/14/1970','0974783643','lchul@gmail.com','Hoa Binh, Viet Nam','Khoa Dien tu','Giang vien','Truong khoa','06/05/2012',''),
						('NV0013','Dang Van Ng','nam','08/26/1977','0657942680','ngdangv@gmail.com','Phu Tho, Viet Nam','Khoa Cong nghe thong tin','Giang vien','','04/01/2021','')

select*from NhanSu
