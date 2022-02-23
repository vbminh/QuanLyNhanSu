package application.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import ConnectSQL.ConnectionUtils;
import application.Models.Account;
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

public class QLNSController implements Initializable{
	@FXML
	private AnchorPane QLNS_GUI, Them_GUI, Sua_GUI;
	@FXML
	private TextField Them_ma, Them_ten, Them_sdt, Them_dchi, Them_email, Them_bophan, Them_cvu;
	@FXML
	private TextField Them_cdanh, Them_cmnd, Them_dtoc, Them_tgiao, Them_hnhan, Them_que;
	@FXML
	private RadioButton Them_gtnam, Them_gtnu;
	@FXML
	private DatePicker Them_ngsinh, Them_ngvaolam, Them_ngcap;
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
				
				NhanSu ns = new NhanSu(ma, ten, gtinh, ngsinh, sdt, email, dchi, bphan, cdanh, cvu, gchu, ngvaolam);
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
		Sua_GUI.setVisible(false);
	}
	
	public void ThemNhanSu_Them() {
		ThemNhanSu_Huy();
	}
	
	
	public void SuaNhanSu() {
		Them_ma.setText("dddd");
		Sua_GUI.setVisible(true);
	}
	
	public void SuaNhanSu_Sua() {
		
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
}
	