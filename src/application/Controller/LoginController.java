package application.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ConnectSQL.ConnectionUtils;
import application.Models.Account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class LoginController implements Initializable{
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	
	private Account account;
	
	private List<Account> list;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void login() {
		Alert alert;
		
		if(username.getText().trim().equals("") || password.getText().trim().equals("")) {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Tên đăng nhập hoặc mật khẩu không được để trống");
			alert.showAndWait();
		}
		
		if(username.getText().contains(" ") || password.getText().contains(" ")) {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Tên đăng nhập hoặc mật khẩu không được chứa dấu cách");
			alert.showAndWait();
		}
		
		if(CheckAccount()) {
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Đăng nhập thành công với quyền " + account.getPermission());
			alert.showAndWait();
		}
		else {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Tên đăng nhập hoặc mật khẩu không đúng");
			alert.showAndWait();
		}
	}
	 
	public void DataQuery() {
		list = new ArrayList<Account>();
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			Statement statement = connection.createStatement();
			String sql = "Select * from BGH";
			ResultSet rs = statement.executeQuery(sql);
			
			if(rs.next()) {
				String name = rs.getString(1);
				String pass = rs.getString(2);
				String permission = rs.getString(3);
				Account acc = new Account(name, pass, permission);
				list.add(acc);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean CheckAccount() {
		account = new Account(username.getText(), password.getText());
		DataQuery();
		int index = list.indexOf(account);
		if(index != -1) {
			String permission = list.get(index).getPermission();
			account.setPermission(permission);
			return true;
		}
		return false;
	}

	public Account getAccount() {
		return account;
	}
	
}
