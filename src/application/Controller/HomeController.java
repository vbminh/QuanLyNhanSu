package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Models.Account;
import application.Models.ListAccount;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController implements Initializable{
	@FXML private AnchorPane AM;
	@FXML private TextField tk;
	@FXML private PasswordField mk, mk2;
	@FXML private TableView<Account> table;
	@FXML private TableColumn<Account, String> tkcolumn, mkcolumn, quyencolumn;
	@FXML private ComboBox<String> combobox;
	private Alert alert;
	
	ReadWriteObject rw = new ReadWriteObject();
	String fileName = "Account.bin";
	ListAccount list = new ListAccount();
	
	private ObservableList<Account> listAccounts = FXCollections.observableArrayList();
	
	private ObservableList<String> listComboBox = FXCollections.observableArrayList("admin","quanly");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
			Parent root = FXMLLoader.load(this.getClass().getResource("/application/Viewer/QLL.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			
			Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException ioe) {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Co loi: " + e.toString());
			alert.show();
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
            list = (ListAccount) rw.ReadObject(fileName);
            for(int i = 0; i < list.getSize(); i++) {
            	listAccounts.add(list.getAccount(i));
            }
        } catch (IOException | ClassNotFoundException ex) {
        	alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Danh sach hien tai rong");
			alert.show();
        }
	}
	
	public void QLTaiKhoan_Them() {
		try {
			Account acc = new Account();
			acc.setUsername(tk.getText());
			
			if(list.IsExist(acc)) {
				alert = new Alert(Alert.AlertType.WARNING);
				alert.setContentText("Ten tai khoan da ton tai");
				alert.show();
			}
			else if(!mk.getText().equals(mk2.getText())) {
				alert = new Alert(Alert.AlertType.WARNING);
				alert.setContentText("Mat khau khong trung khop");
				alert.show();
			}
			else {
					acc.setPassword(mk.getText());
					acc.setPermission(combobox.getValue());
					
					listAccounts.add(acc);
					list.ThemSV(acc);
					luuFile();
					
					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("Them thanh cong");
					alert.show();
					
					tk.setText(null);
					mk.setText(null);
					mk2.setText(null);
					combobox.setValue(null);
				
			}
		}
		catch (Exception e) {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText(e.toString());
			alert.show();
		}
	}
	
	public void QLTaiKhoan_Xoa() {
		alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Ban co muon xoa tai khoan nay khong?");
		
		ButtonType btYes = new ButtonType("Dong y", ButtonData.YES);
		ButtonType btCancel = new ButtonType("Huy", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(btYes,btCancel);
		Optional<ButtonType> result = alert.showAndWait();
		
		Account selected = table.getSelectionModel().getSelectedItem();
		
		if(selected == null) {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Hay chon 1 tai khoan");
			alert.show();
		}
		
		if(result.get() == btYes) {
			listAccounts.remove(selected);
			
			list.xoa(selected);
			luuFile();
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Xoa thanh cong");
			alert.show();
		}
		
	}
	
	private void luuFile() {
        try {
            rw.WriteObject(fileName, list);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
	
	public void QLTaiKhoan_X(ActionEvent event) {
		AM.setVisible(false);
	}
	
	public void Exit() {
		System.exit(0);
	}
}
