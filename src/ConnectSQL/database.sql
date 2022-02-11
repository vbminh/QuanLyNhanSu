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
