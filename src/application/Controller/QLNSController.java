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
import application.Models.NhanSu;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class QLNSController implements Initializable{
	private Alert alert;
	@FXML
	private AnchorPane QLNS_GUI, Them_GUI, Sua_GUI;
	@FXML
	private TextField Them_ma, Them_ten, Them_sdt, Them_dchi, Them_email, Them_bophan, Them_cvu, Them_cdanh, Them_cmnd, Them_dtoc, Them_tgiao, Them_hnhan, Them_que;
	@FXML
	private TextField Sua_ma, Sua_ten, Sua_sdt, Sua_dchi, Sua_email, Sua_bophan, Sua_cvu, Sua_cdanh, Sua_cmnd, Sua_dtoc, Sua_tgiao, Sua_hnhan, Sua_que;
	@FXML
	private RadioButton Them_gtnam, Them_gtnu, Sua_gtnam, Sua_gtnu;
	@FXML
	private DatePicker Them_ngsinh, Them_ngvaolam, Them_ngcap, Sua_ngsinh, Sua_ngvaolam, Sua_ngcap;
	@FXML
	private Label infma, inften, infgtinh, infNgsinh, infdchi, infsdt, infemail;
	@FXML
	private Label infcmnd, infngcap, infdtoc, inftgiao, infqquan, infhnhan;
	@FXML
	private ImageView imgView;
	@FXML
	private TabPane tabpane;
	@FXML
	private TableView<NhanSu> table;
	@FXML
	private TableColumn<NhanSu, String> macolumn, tencolumn, gtinhcolumn, ngsinhcolumn, bophancolumn;
	@FXML
	private TableColumn<NhanSu, String> cdanhcolumn, cvucolumn,ngvlamcolumn, gchucolumn;
	
	ObservableList<NhanSu> listNhanSu = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DataQuery();
		macolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("ma"));
		tencolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("ten"));
		gtinhcolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("gtinh"));
		ngsinhcolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("ngsinh"));
		bophancolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("bophan"));
		cdanhcolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("cdanh"));
		cvucolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("cvu"));
		ngvlamcolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("ngvaolam"));
		gchucolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("ghichu"));
		table.setItems(listNhanSu);
		
	}
	
	public void DataQuery() {
		try {
			Connection connection = ConnectionUtils.getMyConnection();
			Statement statement = connection.createStatement();
			String sql = "select*from NhanSu";
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String gtinh = rs.getString(3);
				String ngsinh = rs.getString(4);
				String sdt = rs.getString(5);
				String email = rs.getString(6);
				String dchi = rs.getString(7);
				String bphan = rs.getString(8);
				String cdanh = rs.getString(9);
				String cvu = rs.getString(10);
				String ngvaolam = rs.getString(11);
				String gchu = rs.getString(12);
				
				NhanSu ns = new NhanSu(ma, ten, gtinh, ngsinh, sdt, email, dchi, bphan, cdanh, cvu, ngvaolam, gchu);
				listNhanSu.add(ns);
			}
			connection.close();
		}
		catch (Exception e) {
			System.out.println("Có lỗi: " + e.toString());
		}
	}
	
	public void Thongtincanhan() {
		tabpane.setVisible(true);
		NhanSu ns =  table.getSelectionModel().getSelectedItem();
		infma.setText(ns.getMa());
		inften.setText(ns.getTen());
		infgtinh.setText(ns.getGtinh());
		infNgsinh.setText(ns.getNgsinh());
		infdchi.setText(ns.getDchi());
		infsdt.setText(ns.getSdt());
		infemail.setText(ns.getEmail());
		infcmnd.setText("12345543753");
		infngcap.setText("1970");
		infdtoc.setText("Kinh");
		inftgiao.setText("Không");
		infqquan.setText("Viet Nam");
		infhnhan.setText("Đã kêt hôn");
	}
	
	public void ThemNhanSu() {
		Them_GUI.setVisible(true);
	}
	
	public void ThemNhanSu_Huy() {
		imgView.setImage(null);
		Them_ma.setText(null);
		Them_ten.setText(null);
		Them_sdt.setText(null);
		Them_dchi.setText(null);
		Them_email.setText(null); 
		Them_bophan.setText(null);
		Them_cvu.setText(null);
		Them_cdanh.setText(null); 
		Them_cmnd.setText(null);
		Them_ngsinh.setValue(null);
		Them_ngvaolam.setValue(null);
		Them_gtnu.setSelected(false);
		Them_gtnam.setSelected(false);
		Them_que.setText(null);
		Them_dtoc.setText(null);
		Them_tgiao.setText(null);
		Them_hnhan.setText(null);
		Them_ngcap.setValue(null);
		
		Them_GUI.setVisible(false);
	}
	
	public void ThemNhanSu_Them() {
		LocalDate ngsinh = ConvertDate(Them_ngsinh).getValue();
		LocalDate ngvaolam = ConvertDate(Them_ngvaolam).getValue();
		String gtinh = Them_gtnam.isSelected()? "nam" : (Them_gtnu.isSelected()? "nu" : "");
		
		if(Them_ma.getText().trim().equals("") || Them_ten.getText().trim().equals("") || ngsinh.toString().trim().equals("") || 
				Them_sdt.getText().trim().equals("") || gtinh.trim().equals("") || Them_email.getText().trim().equals("") || Them_dchi.getText().trim().equals("")
				|| Them_bophan.getText().trim().equals("") || Them_cdanh.getText().trim().equals("") || ngvaolam.toString().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setContentText("Bạn phải nhập đầy đủ các trường");
			alert.show();
		}
		else {
			NhanSu ns = new NhanSu(Them_ma.getText());
			if(listNhanSu.contains(ns)) {
				alert = new Alert(AlertType.WARNING);
				alert.setContentText("Mã nhân viên đã tồn tại. Hãy nhập lại");
				alert.show();
			}
			else {
				ns.setTen(Them_ten.getText());
				ns.setGtinh(gtinh);
				ns.setNgsinh(ngsinh.toString());
				ns.setSdt(Them_sdt.getText());
				ns.setEmail(Them_email.getText());
				ns.setDchi(Them_dchi.getText());
				ns.setBophan(Them_bophan.getText());
				ns.setCdanh(Them_cdanh.getText());
				ns.setCvu(Them_cvu.getText());
				ns.setNgvaolam(ngvaolam.toString());
				ns.setGhichu("");
				listNhanSu.add(ns);
				String value = "\'" + Them_ma.getText() + "\',\'" + Them_ten.getText() + "\',\'" + gtinh + "\',\'" + ngsinh.toString()
					+ "\',\'" + Them_sdt.getText() + "\',\'" + Them_email.getText() + "\',\'" + Them_dchi.getText() + "\',\'" + Them_bophan.getText()
					+ "\',\'" + Them_cdanh.getText() + "\',\'" + Them_cvu.getText() + "\',\'" + ngvaolam.toString() + "\',\'\'";

				try {
					Connection connection = ConnectionUtils.getMyConnection();
					Statement statement = connection.createStatement();
					String sql = "Insert into NhanSu(manv,tennv,gtinh,ngsinh,sdt,email,dchi,bophan,chucdanh,chucvu,ngvaolam,ghichu)" + "values(" + value + ")";
					statement.executeQuery(sql);
					
					connection.close();
				}
				catch (Exception e) {
					System.out.println("Có lỗi: " + e.toString());
				}
				
				alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Thêm thành công");
				alert.show();
				ThemNhanSu_Huy();
			}
		}	
	}
	
	
	public void SuaNhanSu() {
		NhanSu ns = table.getSelectionModel().getSelectedItem();
		Sua_ma.setText(ns.getMa());
		Sua_ten.setText(ns.getTen());
		Sua_sdt.setText(ns.getSdt());
		Sua_dchi.setText(ns.getDchi());
		Sua_email.setText(ns.getEmail()); 
		Sua_bophan.setText(ns.getBophan());
		Sua_cvu.setText(ns.getCvu());
		Sua_cdanh.setText(ns.getCdanh()); 
		Sua_cmnd.setText("1298583871");
		Sua_ngsinh.setValue(LocalDate.of(1970, 6, 15));
		Sua_ngvaolam.setValue(LocalDate.of(2018, 4,1));		
		Sua_que.setText("Ha Noi, Viet Nam");
		Sua_dtoc.setText("Kinh");
		Sua_tgiao.setText("Khong");
		Sua_hnhan.setText("Da ket hon");
		Sua_ngcap.setValue(LocalDate.of(2021, 11, 10));
		
		Sua_GUI.setVisible(true);
	}
	
	public void SuaNhanSu_Sua() {
		LocalDate ngsinh = ConvertDate(Sua_ngsinh).getValue();
		LocalDate ngvaolam = ConvertDate(Sua_ngvaolam).getValue();
		String gtinh = Sua_gtnam.isSelected()? "nam" : (Sua_gtnu.isSelected()? "nu" : "");
		
		if(Sua_ten.getText().trim().equals("") || ngsinh.toString().trim().equals("") || 
				Sua_sdt.getText().trim().equals("") || gtinh.trim().equals("") || Sua_email.getText().trim().equals("") || Sua_dchi.getText().trim().equals("")
				|| Sua_bophan.getText().trim().equals("") || Sua_cdanh.getText().trim().equals("") || ngvaolam.toString().trim().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setContentText("Bạn phải nhập đầy đủ các trường");
			alert.show();
		}
		else {		
			try {
				Connection connection = ConnectionUtils.getMyConnection();
				Statement statement = connection.createStatement();
				String sql = "Update NhanSu set tennv = \'" + Sua_ten.getText() + "\', gtinh = \'" + gtinh + "\', ngsinh = \'" + ngsinh.toString() + "\', sdt = \'" +
						Sua_sdt.getText() + "\', email = \'" + Sua_email.getText() + "\', dchi = \'" + Sua_dchi.getText() + "\', bophan = \'" + Sua_bophan.getText() + 
						"\', chucdanh = \'" + Sua_cdanh.getText() + "\', chucvu = \'" + Sua_cvu.getText() + "\', ngvaolam = \'" + ngvaolam.toString() + "\' where manv = \'" + Sua_ma.getText() + "\'";
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
	
	public void SuaNhanSu_Huy() {
		Sua_ten.setText(null);
		Sua_sdt.setText(null);
		Sua_dchi.setText(null);
		Sua_email.setText(null); 
		Sua_bophan.setText(null);
		Sua_cvu.setText(null);
		Sua_cdanh.setText(null); 
		Sua_cmnd.setText(null);
		Sua_ngsinh.setValue(null);
		Sua_ngvaolam.setValue(null);
		Sua_gtnu.setSelected(false);
		Sua_gtnam.setSelected(false);
		Sua_que.setText(null);
		Sua_dtoc.setText(null);
		Sua_tgiao.setText(null);
		Sua_hnhan.setText(null);
		Sua_ngcap.setValue(null);
		
		Sua_GUI.setVisible(false);
	}
	
	public void XoaNhanSu() {
		alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Bạn có chắc chắn muốn xóa không?");
		
		ButtonType btYes = new ButtonType("Đồng ý", ButtonData.YES);
		ButtonType btCancel = new ButtonType("Hủy", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(btYes,btCancel);
		Optional<ButtonType> result = alert.showAndWait();
		
		NhanSu selected = table.getSelectionModel().getSelectedItem();
		
		if(result.get() == btYes) {
			listNhanSu.remove(selected);
			
			String sql = "Delete from NhanSu where manv = \'" + selected.getMa() + "\'";
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
		Stage stage = (Stage) QLNS_GUI.getScene().getWindow();
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
	