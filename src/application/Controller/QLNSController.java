package application.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;
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
	private Label infma, inften, infgtinh, infNgsinh, infdchi, infsdt, infemail, infcmnd;
	@FXML
	private Label infngcap, infdtoc, inftgiao, infqquan, infhnhan, infdtao, infchnganh, infhvi;
	@FXML
	private ImageView imgView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
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
	