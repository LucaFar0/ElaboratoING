package application.Controller;


import java.net.URL;
import java.util.ResourceBundle ;
import javafx.event.ActionEvent ;
import javafx.fxml.FXML ;
import javafx.fxml.Initializable ;
import javafx.scene.paint.Color ;
import javafx.scene.shape.Rectangle ;
import javafx.stage.Window;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;


public class prenotazioneController {
	
	
	//--------------------- PRENOTAZIONE COLLEGE -----------------------
	@FXML private TextField textCodiceVacanzaCollege;
	@FXML private TextField textCFCollege;
	@FXML private Button buttPrenotaCollege;
	
	
	public void prenotazioneVacanzaCollege(ActionEvent e) {
		
	}
	
	
	//--------------------- PRENOTAZIONE COLLEGE -----------------------
	@FXML private TextField textCodiceVacanzaFam;
	@FXML private TextField textCFFam;
	@FXML private TextField textNomeAmicoFam;
	@FXML private TextField textEmailAmicoFam;
	@FXML private Button buttPrenotaFam;
		
		
	public void prenotazioneVacanzaFam(ActionEvent e) {
			
	}
}
