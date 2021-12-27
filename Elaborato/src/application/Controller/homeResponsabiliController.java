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

public class homeResponsabiliController {

	//--------------------- VACANZA -----------------------
	@FXML private TextField textCodiceVacanza;
	@FXML private TextField textCittaVacanza;
	@FXML private DatePicker dateDataVacanza;
	@FXML private TextField textDurataVacanza;
	@FXML private TextField textLinguaVacanza;
	@FXML private Button buttSalvaVacanza;
	
	public void salvaVacanza() {
		
	}
	
	
	//--------------------- GITA -----------------------
	@FXML private TextField textCodiceVacanzaGita;
	@FXML private TextField textDestinazioneGita;
	@FXML private TextField textCostoGita;
	@FXML private TextField textDurataGita;
	@FXML private TextField textDescrizioneGita;
	@FXML private Button buttSalvaGita;
	
	public void salvaGita() {
		
	}
	
	
	//--------------------- COLLEGE -----------------------
	@FXML private TextField textCodiceVacanzaCollege;
	@FXML private TextField textNomeCollege;
	@FXML private TextField textIndirizzoCollege;
	@FXML private Button buttSalvaCollege;
	
	public void salvaCollege() {
		
	}
	
	
	//--------------------- ATTIVITA' COLLEGE -----------------------
	@FXML private TextField textCodiceVacanzaCollege_A;
	@FXML private TextField textNomeCollege_A;
	@FXML private TextField textNomeAttivita;
	@FXML private TextField textDescrizioneAttivita;
	@FXML private Button buttSalvaAttivita;
	
	public void salvaAttivita() {
		
	}
	
	//--------------------- ATTIVITA' COLLEGE -----------------------
	@FXML private TextField textNomeCapoFam;
	@FXML private TextField textCognomeCapoFam;
	@FXML private TextField textCFCapoFam;
	@FXML private DatePicker dateDdNCapoFam;
	@FXML private TextField textEmailCapoFam;
	@FXML private TextField textCodiceVacanzaFam;
	@FXML private TextField textNrComponentiFam;
	@FXML private CheckBox checkAnimaliFam;
	@FXML private TextField textNrCamereFam;
	@FXML private TextField textNrBagniFam;
	@FXML private TextField textDistanzaFam;
	@FXML private Button buttSalvaFamiglia;
		
	public void salvaFamiglia() {
			
	}

	
	
	
}
