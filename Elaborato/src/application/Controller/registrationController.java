package application.Controller;


import java.net.URL;
import java.util.ResourceBundle ;
import javafx.event.ActionEvent ;
import javafx.fxml.FXML ;
import javafx.fxml.Initializable ;
import javafx.scene.control.Label ;
import javafx.scene.paint.Color ;
import javafx.scene.shape.Rectangle ;
import javafx.stage.Window;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class registrationController {
	//--------------------- RAGAZZO -----------------------
	@FXML private TextField textRegNomeRag;
	@FXML private TextField textRegCognomeRag;
	@FXML private DatePicker dateRegRag;
	@FXML private TextField textRegEmailRag;
	@FXML private TextField textRegCFRag;
	@FXML private TextField textRegTelRag;
	@FXML private TextField textRegIndirizzoRag;
	@FXML private PasswordField passwdReg;
	@FXML private TextField textRegHobby;
	//Allergene
	@FXML private TextField textRegPrecauzioniAllergene;
	@FXML private TextField textRegNomeAllergene;
	@FXML private Button butAggiuntaAllergene;
	
	//--------------------- GENITORE -----------------------
	@FXML private TextField textRegNomeGen;
	@FXML private TextField textRegCognomeGen;
	@FXML private DatePicker dateRegGen;
	@FXML private TextField textRegEmailGen;
	@FXML private TextField textRegCFGen;
	@FXML private TextField textRegTelGen;
	@FXML private Button butAddGen;
	
	@FXML private Button butSaveRegistration;
	
	public void aggiungiAllergene(ActionEvent e) {
		
	}
	
	public void saveRegistration(ActionEvent e) {
		
	}
	
	public void aggiungiGenitore(ActionEvent e) {
		
	}
}


