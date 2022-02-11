module QuanLyNhanSu {
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	requires jtds;
	
	opens application;
	opens application.Viewer;
	opens application.Controller;
}