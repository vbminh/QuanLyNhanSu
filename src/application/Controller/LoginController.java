package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ConnectSQL.ConnectionUtils;
import application.Models.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class LoginController implements Initializable{
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	
	private static Account account;
	
	private List<Account> list = new ArrayList<Account>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void DataQuery() {
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			Statement statement = connection.createStatement();
			String sql = "select*from Account";
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
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
	
	public void login(ActionEvent event) {
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
			
			try {
				Parent root = FXMLLoader.load(this.getClass().getResource("/application/Viewer/Home.fxml"));
				Scene scene = new Scene(root,615,500);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				
				Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (IOException ioe) {
				// TODO Auto-generated catch block
				ioe.printStackTrace();
			}
		}
		else {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Tên đăng nhập hoặc mật khẩu không đúng");
			alert.showAndWait();
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
	
	public static Account getAccount() {
		return account;
	}
	
}
