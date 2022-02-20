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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	private TableColumn<Account, String> tkcolumn;
	@FXML
	private TableColumn<Account, String> mkcolumn;
	@FXML
	private TableColumn<Account, String> quyencolumn;
	@FXML
	private ComboBox<String> combobox;
	
	ObservableList<Account> list2 = FXCollections.observableArrayList();
	
	ObservableList<String> list = FXCollections.observableArrayList("admin","quanly");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Account acc = new Account();
		acc = LoginController.getAccount();
		if(acc.getPermission().equals("admin"))
			sys.setVisible(true);
			
	}
	
	public void QLTD_GUI(ActionEvent e) {
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
	
	public void QLNS_GUI(ActionEvent e) {
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
		combobox.setItems(list);
		
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
				list2.add(acc);
			}
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		tkcolumn.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
		mkcolumn.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));
		quyencolumn.setCellValueFactory(new PropertyValueFactory<Account, String>("permission"));
		table.setItems(list2);
	}
	
	public void Them() {
		System.exit(0);
	}
	
	public void Xoa() {
		System.exit(0);
	}
	
	public void Exit() {
		System.exit(0);
	}
	
	public void X(ActionEvent event) {
		AM.setVisible(false);
	}
}
