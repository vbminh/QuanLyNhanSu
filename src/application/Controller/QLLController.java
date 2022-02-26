package application.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import ConnectSQL.ConnectionUtils;
import application.Models.Luong;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class QLLController implements Initializable{
	@FXML private TextField ManvField, HsoField, LuongField, PhucapField, NgaycongField;	
	@FXML private TableColumn<Luong,String> MaColumn, TenColumn, BophanColumn, ChucvuColumn, CdanhColumn, HsLuongColumn, NgaycongColumn;
	@FXML private TableColumn<Luong, Double> LuongColumn, PhucapColumn, TongluongColumn;
	@FXML private TableView<Luong> table;
    private ObservableList<Luong> ListLuong = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DataQuery();
		MaColumn.setCellValueFactory(new PropertyValueFactory<Luong, String>("ma"));
		TenColumn.setCellValueFactory(new PropertyValueFactory<Luong, String>("ten"));
		BophanColumn.setCellValueFactory(new PropertyValueFactory<Luong, String>("bophan"));
		CdanhColumn.setCellValueFactory(new PropertyValueFactory<Luong, String>("chucdanh"));
		ChucvuColumn.setCellValueFactory(new PropertyValueFactory<Luong, String>("chucvu"));
		LuongColumn.setCellValueFactory(new PropertyValueFactory<Luong, Double>("luongcb"));
		HsLuongColumn.setCellValueFactory(new PropertyValueFactory<Luong, String>("heso"));
		PhucapColumn.setCellValueFactory(new PropertyValueFactory<Luong, Double>("phucap"));
		NgaycongColumn.setCellValueFactory(new PropertyValueFactory<Luong, String>("songaycong"));
		TongluongColumn.setCellValueFactory(new PropertyValueFactory<Luong, Double>("tongluong"));
				
		table.setItems(ListLuong);
	}
	
	public void DataQuery() {
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			Statement statement = connection.createStatement();
			String sql = "select Luong.manv, tennv, bophan, chucdanh, chucvu, luongcb, heso, phucap, songaycong " + 
					"from Luong inner join NhanSu on Luong.manv = NhanSu.manv";
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String bophan = rs.getString(3);
				String chucdanh = rs.getString(4);
				String chucvu = rs.getString(5);
				double luongcb = rs.getDouble(6);
				double heso = (double) rs.getDouble(7);
				double phucap = rs.getDouble(8);
				int songaycong = rs.getInt(9);
				String ngaycong;
				String hsl;
				if(heso == 0)
					hsl = "";
				else
					hsl = String.valueOf(heso);
				
				if(songaycong == 0)
					ngaycong = "";
				else
					ngaycong = String.valueOf(songaycong);
				
				Luong luong = new Luong(ma, ten, bophan, chucdanh,chucvu,luongcb,hsl,phucap,ngaycong);
				ListLuong.add(luong);
			}
			connection.close();
		}
		catch (Exception e) {
			System.out.println("Có lỗi: " + e.toString());
		}
	}
	
	public void add(ActionEvent e) throws ClassNotFoundException {
		try {
        Luong newsNhansu = new Luong();
        Connection connection = ConnectionUtils.getMyConnection();
 		Statement statement = connection.createStatement();
        String sql = "select tennv,bophan,chucdanh,chucvu from Nhansu where manv='"+ManvField.getText()+"'";
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
            newsNhansu.setChucdanh(rs.getString(3));
            newsNhansu.setTen(rs.getString(1));
            newsNhansu.setBophan(rs.getString(2));
            newsNhansu.setChucvu(rs.getString(4));  
        }
		rs.close();
        newsNhansu.setMa(ManvField.getText());
        newsNhansu.setHeso(HsoField.getText());
        newsNhansu.setLuongcb(Double.parseDouble(LuongField.getText()));
        newsNhansu.setPhucap(Double.parseDouble(PhucapField.getText()));
        newsNhansu.setSongaycong(NgaycongField.getText());
        ListLuong.add(newsNhansu);
		String add = "insert into Luong values('"+ManvField.getText()+"',"+1490000+","+
        HsoField.getText()+","+PhucapField.getText()+","+NgaycongField.getText()+")";
		statement.executeUpdate(add);
		} catch (SQLException a) {
            a.printStackTrace();
        }
    }
	public void delete(ActionEvent e) throws ClassNotFoundException, SQLException {
		try {
		Luong selected = table.getSelectionModel().getSelectedItem();
		String manv = selected.getMa();
		ListLuong.remove(selected);
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		String Delete = "Delete from Luong where manv = '"+manv+"'";
		statement.executeUpdate(Delete);
		} catch (SQLException a) {
            a.printStackTrace();
        }
	}
	public void update(ActionEvent e)throws ClassNotFoundException, SQLException {
		try {
		Luong selected = table.getSelectionModel().getSelectedItem();
		int vitri = ListLuong.indexOf(selected);
		Luong newsNhansu = new Luong();
        newsNhansu.setMa(ManvField.getText());
        newsNhansu.setHeso(HsoField.getText());
        newsNhansu.setLuongcb(Double.parseDouble(LuongField.getText()));
        newsNhansu.setPhucap(Double.parseDouble(PhucapField.getText()));
        newsNhansu.setSongaycong(NgaycongField.getText());
        String sql = "select tennv,bophan,chucdanh,chucvu from Nhansu where manv='"+ManvField.getText()+"'";
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
            newsNhansu.setChucdanh(rs.getString(3));
            newsNhansu.setTen(rs.getString(1));
            newsNhansu.setBophan(rs.getString(2));
            newsNhansu.setChucvu(rs.getString(4));  
        }
		rs.close();
		ListLuong.set(vitri, newsNhansu);
		String Delete = "update Luong set luongcb="+LuongField.getText()+",heso="+HsoField.getText()+",phucap="+PhucapField.getText()+",songaycong="+NgaycongField.getText()+" where manv ='"+ManvField.getText()+"'";
		statement.executeUpdate(Delete);
		} catch (SQLException a) {
            a.printStackTrace();
        }
	}
	
}
