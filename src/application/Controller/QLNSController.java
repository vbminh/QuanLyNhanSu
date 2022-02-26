package application.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.ComboBox;
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

public class QLNSController implements Initializable{
	private Alert alert;
	@FXML private AnchorPane QLNS_GUI, Them_GUI, Sua_GUI, Tim_GUI;
	@FXML private TextField Them_ma, Them_ten, Them_sdt, Them_dchi, Them_email, Them_bophan, Them_cvu, Them_cdanh, Them_cmnd, Them_dtoc, Them_tgiao, Them_hnhan, Them_que;
	@FXML private TextField Sua_ma, Sua_ten, Sua_sdt, Sua_dchi, Sua_email, Sua_bophan, Sua_cvu, Sua_cdanh, Sua_cmnd, Sua_dtoc, Sua_tgiao, Sua_hnhan, Sua_que;
	@FXML private RadioButton Them_gtnam, Them_gtnu, Sua_gtnam, Sua_gtnu;
	@FXML private DatePicker Them_ngsinh, Them_ngvaolam, Them_ngcap, Sua_ngsinh, Sua_ngvaolam, Sua_ngcap;
	@FXML private Label infma, inften, infgtinh, infNgsinh, infdchi, infsdt, infemail;
	@FXML private Label infcmnd, infngcap, infdtoc, inftgiao, infqquan, infhnhan;
	@FXML private ImageView imgView;
	@FXML private ComboBox<String> combobox;
	@FXML private TextField timkiem;
	@FXML private TabPane tabpane;
	@FXML private TableView<NhanSu> table, Ttable;
	@FXML private TableColumn<NhanSu, String> macolumn, tencolumn, gtinhcolumn, ngsinhcolumn, bophancolumn;
	@FXML private TableColumn<NhanSu, String> cdanhcolumn, cvucolumn,ngvlamcolumn, gchucolumn;
	@FXML private TableColumn<NhanSu, String> Tmacolumn, Ttencolumn, Tgtinhcolumn, Tngsinhcolumn, Tbophancolumn;
	@FXML private TableColumn<NhanSu, String> Tcdanhcolumn, Tcvucolumn,Tngvlamcolumn, Tgchucolumn;
	
	private ObservableList<NhanSu> listNhanSu = FXCollections.observableArrayList();
	private ObservableList<String> listBox = FXCollections.observableArrayList("Ma nhan vien","Ten nhan vien","Gioi tinh","Dia chi","Bo phan","Chuc danh nghe nghiep","Chuc vu");
	
	ListNhanSu list = new ListNhanSu();
	ReadWriteObject rw = new ReadWriteObject();
	String fileName = "NhanSu.bin";
	
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
            list = (ListNhanSu) rw.ReadObject(fileName);
            for(int i = 0; i < list.getSize(); i++) {
            	listNhanSu.add(list.getNhanSu(i));
            }
        } catch (IOException | ClassNotFoundException ex) {
        	alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("Danh sach hien tai rong");
			alert.show();
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
		inftgiao.setText("Khong");
		infqquan.setText("Viet Nam");
		infhnhan.setText("Da ket hon");
	}
	
	public void ThemNhanSu_Them() {
		LocalDate ngsinh = Them_ngsinh.getValue();
		LocalDate ngvaolam = Them_ngvaolam.getValue();
		String gtinh = Them_gtnam.isSelected()? "nam" : (Them_gtnu.isSelected()? "nu" : "");
		
		NhanSu ns = new NhanSu(Them_ma.getText());
		
		if(listNhanSu.contains(ns)) {
			alert = new Alert(AlertType.WARNING);
			alert.setContentText("Ma nhan vien da ton tai. Hay nhap lai");
			alert.show();
		}
		
		else if(ngsinh.compareTo(LocalDate.now()) > 0 || ngvaolam.compareTo(LocalDate.now()) > 0 || ngsinh.compareTo(ngvaolam) >= 0) {
			alert = new Alert(AlertType.WARNING);
			alert.setContentText("Ngay sinh va ngay vao lam khong duoc lon hon ngay hien tai. Ngay sinh phai truoc ngay vao lam");
			alert.show();
		}
		else {
			try {
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
				list.ThemNS(ns);
				luuFile();
					
				alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Them thanh cong");
				alert.show();
				ThemNhanSu_Huy();
			}
			catch (Exception e) {
				alert = new Alert(Alert.AlertType.WARNING);
				alert.setContentText(e.toString());
				alert.show();
			}
		}	
	}
	
	public void SuaNhanSu_Sua() {
		LocalDate ngsinh = Sua_ngsinh.getValue();
		LocalDate ngvaolam = Sua_ngvaolam.getValue();
		String gtinh = Sua_gtnam.isSelected()? "nam" : (Sua_gtnu.isSelected()? "nu" : "");
		
		if(ngsinh.compareTo(LocalDate.now()) > 0 || ngvaolam.compareTo(LocalDate.now()) > 0 || ngsinh.compareTo(ngvaolam) >= 0) {
			alert = new Alert(AlertType.WARNING);
			alert.setContentText("Ngay sinh va ngay vao lam khong duoc lon hon ngay hien tai. Ngay sinh phai truoc ngay vao lam");
			alert.show();
		}
		else {
			try {
				NhanSu ns = new NhanSu(Sua_ma.getText());
				ns.setTen(Sua_ten.getText());
				ns.setGtinh(gtinh);
				ns.setNgsinh(ngsinh.toString());
				ns.setSdt(Sua_sdt.getText());
				ns.setEmail(Sua_email.getText());
				ns.setDchi(Sua_dchi.getText());
				ns.setBophan(Sua_bophan.getText());
				ns.setCdanh(Sua_cdanh.getText());
				ns.setCvu(Sua_cvu.getText());
				ns.setNgvaolam(ngvaolam.toString());
				ns.setGhichu("");
				list.Sua(Sua_ma.getText(), ns);
				luuFile();
				
				table.getItems().clear();
				initialize(null, null);
				
				alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Thong bao");
				alert.setContentText("Cap nhat thanh cong");
				alert.show();
				Sua_GUI.setVisible(false);
			}
			catch (Exception e) {
				alert = new Alert(Alert.AlertType.WARNING);
				alert.setContentText(e.toString());
				alert.show();
			}
		}
		
	}
	
	public void XoaNhanSu() {
		alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Ban co chac chan muon xoa khong?");
		
		ButtonType btYes = new ButtonType("Dong y", ButtonData.YES);
		ButtonType btCancel = new ButtonType("Huy", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(btYes,btCancel);
		Optional<ButtonType> result = alert.showAndWait();
		
		NhanSu selected = table.getSelectionModel().getSelectedItem();
		
		if(result.get() == btYes) {
			listNhanSu.remove(selected);
			list.xoa(selected);
			luuFile();
			
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Thong bao");
			alert.setContentText("Xoa thanh cong");
			alert.show();
		}
		
	}
	
	public void TimNhanSu_Tim() {
		ObservableList<NhanSu> list2 = FXCollections.observableArrayList();
		Ttable.getItems().clear();
		
		if(combobox.getValue().equals("Ma nhan vien")) {
			listNhanSu.forEach(item -> {
				if(item.getMa().equals(timkiem.getText()))
					list2.add(item);
			});
		}
		else if(combobox.getValue().equals("Ten nhan vien")) {
			listNhanSu.forEach(item -> {
				if(item.getTen().equals(timkiem.getText()))
					list2.add(item);
			});
		}
		else if(combobox.getValue().equals("Gioi tinh")){
			listNhanSu.forEach(item -> {
				if(item.getGtinh().equals(timkiem.getText()))
					list2.add(item);
			});
		}	
		if(combobox.getValue().equals("Dia chi")){
			listNhanSu.forEach(item -> {
				if(item.getDchi().equals(timkiem.getText()))
					list2.add(item);
			});
		}
		if(combobox.getValue().equals("Bo phan")){
			listNhanSu.forEach(item -> {
				if(item.getBophan().equals(timkiem.getText()))
					list2.add(item);
			});
		}
		
		if(combobox.getValue().equals("Chuc danh nghe nghiep")){
			listNhanSu.forEach(item -> {
				if(item.getCdanh().equals(timkiem.getText()))
					list2.add(item);
			});
		}
			
		if(combobox.getValue().equals("Chuc vu")){
			listNhanSu.forEach(item -> {
				if(item.getCvu().equals(timkiem.getText()))
					list2.add(item);
			});
		}
		
		Tmacolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("ma"));
		Ttencolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("ten"));
		Tgtinhcolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("gtinh"));
		Tngsinhcolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("ngsinh"));
		Tbophancolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("bophan"));
		Tcdanhcolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("cdanh"));
		Tcvucolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("cvu"));
		Tngvlamcolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("ngvaolam"));
		Tgchucolumn.setCellValueFactory(new PropertyValueFactory<NhanSu, String>("ghichu"));
		
		Ttable.setItems(list2);
	}
	
	private void luuFile() {
        try {
            rw.WriteObject(fileName, list);
        } catch (IOException ex) {
            System.out.println(ex);
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
		if(ns.getGtinh().equals("nam"))
			Sua_gtnam.setSelected(true);
		if(ns.getGtinh().equals("nu"))
			Sua_gtnu.setSelected(true);
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
		
	public void TimNhanSu() {
		Tim_GUI.setVisible(true);
		combobox.setItems(listBox);
	}
		
	public void TimNhanSu_Huy() {
		Tim_GUI.setVisible(false);
		timkiem.setText(null);
		combobox.setValue(null);
	}
	
	public void back(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("/application/Viewer/Home.fxml"));
			Scene scene = new Scene(root,615,500);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			
			Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setX(400);
			stage.setY(50);
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

}
	