package application.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import ConnectSQL.ConnectionUtils;
import application.Models.TuyenDung;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class QLTDController implements Initializable{
	private Alert alert;
	@FXML
	private AnchorPane QLTD_GUI, Them_GUI, Sua_GUI;
	@FXML
	private TextField Them_ma, Them_ten, Them_sdt, Them_dchi, Them_email, Them_tdo, Them_truong, Them_cnganh, Them_xeploai, Them_vitri, Them_cmnd, Them_dtoc, Them_tgiao, Them_hnhan, Them_que;
	@FXML
	private TextField Sua_ma, Sua_ten, Sua_sdt, Sua_dchi, Sua_email, Sua_tdo, Sua_cnganh, Sua_truong, Sua_xeploai, Sua_vitri, Sua_cmnd, Sua_dtoc, Sua_tgiao, Sua_hnhan, Sua_que;
	@FXML
	private RadioButton Them_gtnam, Them_gtnu, Sua_gtnam, Sua_gtnu;
	@FXML
	private DatePicker Them_ngsinh, Them_ngcap, Sua_ngsinh, Sua_ngcap;
	@FXML
	private Label infma, inften, infgtinh, infNgsinh, infdchi, infsdt, infemail, inftruong, infcnganh, infxloai;
	@FXML
	private Label infcmnd, infngcap, infdtoc, inftgiao, infqquan, infhnhan;
	@FXML
	private ImageView imgView;
	@FXML
	private TabPane tabpane;
	@FXML
	private TableView<TuyenDung> table;
	@FXML
	private TableColumn<TuyenDung, String> macolumn, tencolumn, gtinhcolumn, ngsinhcolumn, trdocolumn;
	@FXML
	private TableColumn<TuyenDung, String> truongcolumn, cnganhcolumn,xeploaicolumn, vtricolumn;
	
	ObservableList<TuyenDung> listUngVien = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DataQuery();
		macolumn.setCellValueFactory(new PropertyValueFactory<TuyenDung, String>("ma"));
		tencolumn.setCellValueFactory(new PropertyValueFactory<TuyenDung, String>("ten"));
		gtinhcolumn.setCellValueFactory(new PropertyValueFactory<TuyenDung, String>("gtinh"));
		ngsinhcolumn.setCellValueFactory(new PropertyValueFactory<TuyenDung, String>("ngsinh"));
		trdocolumn.setCellValueFactory(new PropertyValueFactory<TuyenDung, String>("trinhdo"));
		truongcolumn.setCellValueFactory(new PropertyValueFactory<TuyenDung, String>("truong"));
		cnganhcolumn.setCellValueFactory(new PropertyValueFactory<TuyenDung, String>("cnganh"));
		xeploaicolumn.setCellValueFactory(new PropertyValueFactory<TuyenDung, String>("xeploai"));
		vtricolumn.setCellValueFactory(new PropertyValueFactory<TuyenDung, String>("vtungtuyen"));
		table.setItems(listUngVien);
		
	}
	
	public void DataQuery() {
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			Statement statement = connection.createStatement();
			String sql = "select*from TuyenDung";
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String gtinh = rs.getString(3);
				String ngsinh = rs.getString(4);
				String sdt = rs.getString(5);
				String email = rs.getString(6);
				String dchi = rs.getString(7);
				String trinhdo = rs.getString(8);
				String truong = rs.getString(9);
				String cnganh = rs.getString(10);
				String xeploai = rs.getString(11);
				String vtri = rs.getString(12);
				
				TuyenDung td = new TuyenDung(ma, ten, gtinh, ngsinh, sdt, email, dchi, trinhdo, truong, cnganh, xeploai, vtri);
				listUngVien.add(td);
			}
			connection.close();
		}
		catch (Exception e) {
			System.out.println("Có lỗi: " + e.toString());
		}
	}
	
	public void Thongtincanhan() {
		tabpane.setVisible(true);
		TuyenDung td =  table.getSelectionModel().getSelectedItem();
		infma.setText(td.getMa());
		inften.setText(td.getTen());
		infgtinh.setText(td.getGtinh());
		infNgsinh.setText(td.getNgsinh());
		infdchi.setText(td.getDchi());
		infsdt.setText(td.getSdt());
		infemail.setText(td.getEmail());
		infcmnd.setText("12345543753");
		infngcap.setText("1970");
		infdtoc.setText("Kinh");
		inftgiao.setText("Không");
		infqquan.setText("Viet Nam");
		infhnhan.setText("Đã kêt hôn");
		inftruong.setText(td.getTruong());
		infcnganh.setText(td.getCnganh());
		infxloai.setText(td.getXeploai());
	}
	
	public void ThemUngVien() {
		Them_GUI.setVisible(true);
	}
	
	public void ThemUngVien_Huy() {
		imgView.setImage(null);
		Them_ma.setText(null);
		Them_ten.setText(null);
		Them_sdt.setText(null);
		Them_dchi.setText(null);
		Them_email.setText(null); 
		Them_tdo.setText(null);
		Them_truong.setText(null);
		Them_cnganh.setText(null); 
		Them_cmnd.setText(null);
		Them_ngsinh.setValue(null);
		Them_xeploai.setText(null);
		Them_gtnu.setSelected(false);
		Them_gtnam.setSelected(false);
		Them_que.setText(null);
		Them_dtoc.setText(null);
		Them_tgiao.setText(null);
		Them_hnhan.setText(null);
		Them_ngcap.setValue(null);
		Them_vitri.setText(null);
		
		Them_GUI.setVisible(false);
	}
	
	public void ThemUngVien_Them() {
		LocalDate ngsinh = ConvertDate(Them_ngsinh).getValue();
		String gtinh = Them_gtnam.isSelected()? "nam" : (Them_gtnu.isSelected()? "nu" : "");
		
		if(Them_ma.getText().trim().equals("") || Them_ten.getText().trim().equals("") || ngsinh.toString().trim().equals("") ||  Them_cnganh.getText().trim().equals("") ||
				Them_sdt.getText().trim().equals("") || gtinh.trim().equals("") || Them_email.getText().trim().equals("") || Them_dchi.getText().trim().equals("")
				|| Them_tdo.getText().trim().equals("") || Them_truong.getText().trim().equals("") || Them_xeploai.getText().trim().equals("") || Them_vitri.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setContentText("Bạn phải nhập đầy đủ các trường");
			alert.show();
		}
		else {
			TuyenDung td = new TuyenDung(Them_ma.getText());
			if(listUngVien.contains(td)) {
				alert = new Alert(AlertType.WARNING);
				alert.setContentText("Mã ứng viên đã tồn tại. Hãy nhập lại");
				alert.show();
			}
			else {
				td.setTen(Them_ten.getText());
				td.setGtinh(gtinh);
				td.setNgsinh(ngsinh.toString());
				td.setSdt(Them_sdt.getText());
				td.setEmail(Them_email.getText());
				td.setDchi(Them_dchi.getText());
				td.setTrinhdo(Them_tdo.getText());
				td.setTruong(Them_truong.getText());
				td.setCnganh(Them_cnganh.getText());
				td.setXeploai(Them_xeploai.getText());
				td.setVtungtuyen(Them_vitri.getText());
				listUngVien.add(td);
				String value = "\'" + Them_ma.getText() + "\',\'" + Them_ten.getText() + "\',\'" + gtinh + "\',\'" + ngsinh.toString()
					+ "\',\'" + Them_sdt.getText() + "\',\'" + Them_email.getText() + "\',\'" + Them_dchi.getText() + "\',\'" + Them_tdo.getText()
					+ "\',\'" + Them_truong.getText() + "\',\'" + Them_cnganh.getText() + "\',\'" + Them_xeploai.getText() + "\',\'" + Them_vitri.getText() + "\'";

				try {
					Connection connection = ConnectionUtils.getMyConnection();
					Statement statement = connection.createStatement();
					String sql = "Insert into TuyenDung(mauv,tenuv,gtinh,ngsinh,sdt,email,dchi,trinhdo,truong,cnganh,xeploai,vtungtuyen)" + "values(" + value + ")";
					statement.executeQuery(sql);
					
					connection.close();
				}
				catch (Exception e) {
					System.out.println("Có lỗi: " + e.toString());
				}
				
				alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Thêm thành công");
				alert.show();
				ThemUngVien_Huy();
			}
		}	
	}
	
	
	public void SuaUngVien() {
		TuyenDung td = table.getSelectionModel().getSelectedItem();
		Sua_ma.setText(td.getMa());
		Sua_ten.setText(td.getTen());
		Sua_sdt.setText(td.getSdt());
		Sua_dchi.setText(td.getDchi());
		Sua_email.setText(td.getEmail()); 
		Sua_tdo.setText(td.getTrinhdo());
		Sua_truong.setText(td.getTruong());
		Sua_cnganh.setText(td.getCnganh()); 
		Sua_cmnd.setText("1298583871");
		Sua_ngsinh.setValue(LocalDate.of(1970, 6, 15));
		Sua_xeploai.setText(td.getXeploai());		
		Sua_que.setText("Ha Noi, Viet Nam");
		Sua_dtoc.setText("Kinh");
		Sua_tgiao.setText("Khong");
		Sua_hnhan.setText("Da ket hon");
		Sua_ngcap.setValue(LocalDate.of(2021, 11, 10));
		Sua_vitri.setText(td.getVtungtuyen());
		Sua_GUI.setVisible(true);
	}
	
	public void SuaUngVien_Sua() {
		LocalDate ngsinh = ConvertDate(Sua_ngsinh).getValue();
		String gtinh = Sua_gtnam.isSelected()? "nam" : (Sua_gtnu.isSelected()? "nu" : "");
		
		if(Sua_ma.getText().trim().equals("") || Sua_ten.getText().trim().equals("") || ngsinh.toString().trim().equals("") ||  Sua_cnganh.getText().trim().equals("") ||
				Sua_sdt.getText().trim().equals("") || gtinh.trim().equals("") || Sua_email.getText().trim().equals("") || Sua_dchi.getText().trim().equals("")
				|| Sua_tdo.getText().trim().equals("") || Sua_truong.getText().trim().equals("") || Sua_xeploai.getText().trim().equals("") || Sua_vitri.getText().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setContentText("Bạn phải nhập đầy đủ các trường");
			alert.show();
		}
		else {		
			try {
				Connection connection = ConnectionUtils.getMyConnection();
				Statement statement = connection.createStatement();
				String sql = "Update TuyenDung set tenuv = \'" + Sua_ten.getText() + "\', gtinh = \'" + gtinh + "\', ngsinh = \'" + ngsinh.toString() + "\', sdt = \'" +
						Sua_sdt.getText() + "\', email = \'" + Sua_email.getText() + "\', dchi = \'" + Sua_dchi.getText() + "\', trinhdo = \'" + Sua_tdo.getText() + 
						"\', truong = \'" + Sua_truong.getText() + "\', cnganh = \'" + Sua_cnganh.getText() + "\', xeploai = \'" + Sua_xeploai.getText() +
						"\', vtungtuyen = \'" + Sua_vitri.getText() + "\' where mauv = \'" + Sua_ma.getText() + "\'";
				statement.executeQuery(sql);
				
				connection.close();
			}
			catch (Exception e) {
				System.out.println("Có lỗi: " + e.toString());
			}
			table.getItems().clear();
			initialize(null, null);
			
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Thông báo");
			alert.setContentText("Cập nhật thành công");
			alert.show();
			Sua_GUI.setVisible(false);
		}
	}
	
	public void SuaUngVien_Huy() {
		Sua_ten.setText(null);
		Sua_sdt.setText(null);
		Sua_dchi.setText(null);
		Sua_email.setText(null); 
		Sua_tdo.setText(null);
		Sua_truong.setText(null);
		Sua_cnganh.setText(null); 
		Sua_cmnd.setText(null);
		Sua_ngsinh.setValue(null);
		Sua_xeploai.setText(null);
		Sua_gtnu.setSelected(false);
		Sua_gtnam.setSelected(false);
		Sua_que.setText(null);
		Sua_dtoc.setText(null);
		Sua_tgiao.setText(null);
		Sua_hnhan.setText(null);
		Sua_ngcap.setValue(null);
		Sua_vitri.setText(null);
		
		Sua_GUI.setVisible(false);
	}
	
	public void XoaUngVien() {
		alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Bạn có chắc chắn muốn xóa không?");
		
		ButtonType btYes = new ButtonType("Đồng ý", ButtonData.YES);
		ButtonType btCancel = new ButtonType("Hủy", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(btYes,btCancel);
		Optional<ButtonType> result = alert.showAndWait();
		
		TuyenDung selected = table.getSelectionModel().getSelectedItem();
		
		if(result.get() == btYes) {
			listUngVien.remove(selected);
			
			String sql = "Delete from TuyenDung where mauv = \'" + selected.getMa() + "\'";
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
			alert.setTitle("Thông báo");
			alert.setContentText("Xóa thành công");
			alert.show();
		}
		
	}
	
	public void back(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("/application/Viewer/Home.fxml"));
			Scene scene = new Scene(root,615,500);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			
			Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
	public void ChooseImage(ActionEvent e) {
		Stage stage = (Stage) QLTD_GUI.getScene().getWindow();
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose a image");
		FileChooser.ExtensionFilter imgFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg","*.png");
		fc.getExtensionFilters().add(imgFilter);
		File file = fc.showOpenDialog(stage);
		if(file != null) {
			Image img = new Image(file.toURI().toString());
			imgView.setImage(img);
		}
	}
	
	public DatePicker ConvertDate(DatePicker dpk) {
		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			
			@Override
			public String toString(LocalDate date) {
				if (date != null) 
					return dtf.format(date);
	            else 
	                return "";
	        }
			
			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty())
                    return LocalDate.parse(string, dtf);
                else 
                    return null;   
			}
		};
		dpk.setConverter(converter);
		dpk.setPromptText("MM/dd/yyyy");
		return dpk;
	}
}
