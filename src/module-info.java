module QuanLyNhanSu {
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	
	opens application to javafx.fxml, javafx.graphics;
	opens application.Viewer;
	opens application.Controller;
	opens application.Models;
}