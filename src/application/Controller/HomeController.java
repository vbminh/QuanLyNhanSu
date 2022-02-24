package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import ConnectSQL.ConnectionUtils;
import application.Models.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController implements Initializable{
	@FXML
	private AnchorPane AM;
	@FXML
	private TextField tk;
	@FXML
	private PasswordField mk, mk2;
	@FXML
	private Menu sys;
	@FXML
	private TableView<Account> table;
	@FXML
	private TableColumn<Account, String> tkcolumn, mkcolumn, quyencolumn;
	@FXML
	private ComboBox<String> combobox;
	
	ObservableList<Account> listAccounts = FXCollections.observableArrayList();
	
	ObservableList<String> listComboBox = FXCollections.observableArrayList("admin","quanly");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Account acc = new Account();
		acc = LoginController.getAccount();
		if(acc.getPermission().equals("admin"))
			sys.setVisible(true);
			
	}
	
	public void QLTD_GUI(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("/application/Viewer/QLTD.fxml"));
			Scene scene = new Scene(root,1300,650);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			
			Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
	public void QLNS_GUI(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("/application/Viewer/QLNS.fxml"));
			Scene scene = new Scene(root, 1300, 650);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			
			Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setX(20);
			stage.setY(15);
			stage.show();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
	public void QLL_GUI(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("/application/Viewer/QLNS.fxml"));
			Scene scene = new Scene(root,1300,650);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			
			Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
	public void QLTaiKhoan(ActionEvent event) {
		AM.setVisible(true);
		table.getItems().clear();
		combobox.setItems(listComboBox);
		DataQuery();
		
		tkcolumn.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
		mkcolumn.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));
		quyencolumn.setCellValueFactory(new PropertyValueFactory<Account, String>("permission"));
		table.setItems(listAccounts);
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
				listAccounts.add(acc);
			}
			connection.close();
		}
		catch (Exception e) {
			System.out.println("Có lỗi: " + e.toString());
		}
	}
	
	public void QLTaiKhoan_Them() {
		Alert alert;
		int check = 0;
		
		for(Account item : listAccounts) {
			if(item.getUsername().equals(tk.getText())) check++;
		}
		
		if(tk.getText().trim().equals("") || mk.getText().trim().equals("") || mk2.getText().trim().equals("") || combobox.getValue().isBlank()) {			
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Bạn phải nhập đầy đủ các trường");
			alert.show();
		}
		else if(check != 0) {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Tên tài khoản đã tồn tại");
			alert.show();
		}
		else if(!mk.getText().equals(mk2.getText())) {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Mật khẩu không trùng khớp");
			alert.show();
		}
		else {
			Account newAcc = new Account();
			newAcc.setUsername(tk.getText());
			newAcc.setPassword(mk.getText());
			newAcc.setPermission(combobox.getValue());
			listAccounts.add(newAcc);
			
			String sql = "Insert into Account(username,pass,permission)" + "values(\'" + tk.getText() + "',\'" + mk.getText() +"\',\'" + combobox.getValue() + "\')";
			try {
				Connection connection = ConnectionUtils.getMyConnection();
				Statement statement = connection.createStatement();
				statement.executeQuery(sql);
				
				connection.close();
			}
			catch (Exception e) {
				System.out.println("Có lỗi: " + e.toString());
			}
			
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Thêm thành công");
			alert.show();
			
			tk.setText(null);
			mk.setText(null);
			mk2.setText(null);
			combobox.setValue(null);
		}
	}
	
	public void QLTaiKhoan_Xoa() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Bạn có muốn xóa tài khoản này không?");
		
		ButtonType btYes = new ButtonType("Đồng ý", ButtonData.YES);
		ButtonType btCancel = new ButtonType("Hủy", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(btYes,btCancel);
		Optional<ButtonType> result = alert.showAndWait();
		
		Account selected = table.getSelectionModel().getSelectedItem();
		
		if(result.get() == btYes) {
			listAccounts.remove(selected);
		}
		
		String sql = "Delete from Account where username = \'" + selected.getUsername() + "\'";
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			Statement statement = connection.createStatement();
			statement.executeQuery(sql);
			
			connection.close();
		}
		catch (Exception e) {
			System.out.println("Có lỗi: " + e.toString());
		}
	}
	
	public void QLTaiKhoan_X(ActionEvent event) {
		AM.setVisible(false);
	}
	
	public void Exit() {
		System.exit(0);
	}
}
