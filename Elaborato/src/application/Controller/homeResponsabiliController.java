package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle ;

import application.Model.*;
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
	
	public void resetFieldsVacanza() {
		textCodiceVacanza.setText(null);
		textCittaVacanza.setText(null);
		dateDataVacanza.setValue(null);
		textDurataVacanza.setText(null);
		textLinguaVacanza.setText(null);
	}



	//--------------------- GITA -----------------------
	@FXML private TextField textCodiceVacanzaGita;
	@FXML private TextField textDestinazioneGita;
	@FXML private TextField textCostoGita;
	@FXML private TextField textDurataGita;
	@FXML private TextField textDescrizioneGita;
	@FXML private Button buttSalvaGita;


	public void resetFieldsGita() {
		textCodiceVacanzaGita.setText(null);
		textDestinazioneGita.setText(null);
		textCostoGita.setText(null);
		textDurataGita.setText(null);
		textDescrizioneGita.setText(null);
	}



	//--------------------- COLLEGE -----------------------
	@FXML private TextField textCodiceVacanzaCollege;
	@FXML private TextField textNomeCollege;
	@FXML private TextField textIndirizzoCollege;
	@FXML private TextField textStanzeCollege;
	@FXML private Button buttSalvaCollege;

	public void resetFieldsCollege() {
		textCodiceVacanzaCollege.setText(null);
		textNomeCollege.setText(null);
		textIndirizzoCollege.setText(null);
		textStanzeCollege.setText(null);
	}


	//--------------------- ATTIVITA' COLLEGE -----------------------
	@FXML private TextField textCodiceVacanzaCollege_A;
	@FXML private TextField textNomeCollege_A;
	@FXML private TextField textNomeAttivita;
	@FXML private TextField textDescrizioneAttivita;
	@FXML private Button buttSalvaAttivita;


	public void resetFieldsAttivita() {
		textCodiceVacanzaCollege_A.setText(null);
		textNomeCollege_A.setText(null);
		textNomeAttivita.setText(null);
		textDescrizioneAttivita.setText(null);
	}

	//--------------------- FAMIGLIA -----------------------
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
	
	
	
	public void resetFieldsFamiglia() {
		textNomeCapoFam.setText(null);
		textCognomeCapoFam.setText(null);
		textCFCapoFam.setText(null);
		dateDdNCapoFam.setValue(null);
		textEmailCapoFam.setText(null);
		
		textCodiceVacanzaFam.setText(null);
		textNrComponentiFam.setText(null);
		checkAnimaliFam.setSelected(false);
		textNrCamereFam.setText(null);
		textNrBagniFam.setText(null);
		textDistanzaFam.setText(null);
	}
	
	

	//--------------------- VACANZA -----------------------


	public void salvaVacanza(ActionEvent e) throws IOException{
		Window owner = buttSalvaVacanza.getScene().getWindow();

		//controllo che i campi siano stati compilati correttamente 


		if (textCodiceVacanza.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Codice valido");
			return;
		}
		if (textCittaVacanza.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una Città di destinazione");
			return;
		}
		if (dateDataVacanza.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una data di partenza");
			return;
		}
		if (textDurataVacanza.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire la durata della vacanza");
			return;
		}
		if (textLinguaVacanza.getText().isEmpty() ) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire la lingua studiata ");
			return;
		}

		Vacanza vacanza = new Vacanza(textCodiceVacanza.getText(), textCittaVacanza.getText(), dateDataVacanza.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), textDurataVacanza.getText(), textLinguaVacanza.getText());
		
		
		System.out.println(vacanza.toString());

		//chiamata insert del jdbc
		
		try {
			PostreSQLJDBC.addVacanza(vacanza);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		resetFieldsVacanza();
	}


	//--------------------- GITA -----------------------
	public void salvaGita(ActionEvent e) {
		Window owner = buttSalvaGita.getScene().getWindow();

		//controllo che i campi siano stati compilati correttamente 


		if (textCodiceVacanzaGita.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Codice valido");
			return;
		}
		if (textDestinazioneGita.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una Città di destinazione");
			return;
		}
		if (textCostoGita.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire il costo della gita");
			return;
		}
		if (textDurataGita.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire la durata della gita");
			return;
		}
		if (textDescrizioneGita.getText().isEmpty() ) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire descrizione della gita ");
			return;
		}

		Gita gita = new Gita(textCodiceVacanzaGita.getText(), textDestinazioneGita.getText(), textCostoGita.getText(), textDurataGita.getText(), textDescrizioneGita.getText());

		System.out.println(gita.toString());
		
		//chiamata insert del jdbc
		try {
			PostreSQLJDBC.addGita(gita);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		
		resetFieldsGita();
	}


	//--------------------- COLLEGE -----------------------
	public void salvaCollege(ActionEvent e) {
		Window owner = buttSalvaCollege.getScene().getWindow();

		//controllo che i campi siano stati compilati correttamente 

		if (textCodiceVacanzaCollege.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Codice Vacanza valido");
			return;
		}
		if (textNomeCollege.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserireil nome del College");
			return;
		}
		if (textIndirizzoCollege.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire l'indirizzo");
			return;
		}
		if (textStanzeCollege.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire il numero di stanze disponibili");
			return;
		}


		College college = new College( textNomeCollege.getText(), textIndirizzoCollege.getText(), textCodiceVacanzaCollege.getText(), textStanzeCollege.getText(), false, null);
		
		System.out.println(college.toString());
		//chiamata insert del jdbc
		
		try {
			PostreSQLJDBC.addCollege(college);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		resetFieldsCollege();
	}


	//--------------------- ATTIVITA' COLLEGE -----------------------
	public void salvaAttivita(ActionEvent e) {
		Window owner = buttSalvaAttivita.getScene().getWindow();
	
		if (textNomeCollege_A.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire il nome del reltivo college");
			return;
		}
		if (textNomeAttivita.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire il Nome dell'attività");
			return;
		}
		if (textDescrizioneAttivita.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire la  descrizione dell'attività");
			return;
		}
		
		
		Attivita attivita = new Attivita(textNomeCollege_A.getText(), textNomeAttivita.getText(), textDescrizioneAttivita.getText());
		
		//chiamata insert del jdbc
		
		try {
			PostreSQLJDBC.addAttivita(attivita);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		resetFieldsAttivita();
	}




	//--------------------- FAMIGLIA -----------------------
	
	

	public void salvaFamiglia(ActionEvent e) {
		Window owner = buttSalvaFamiglia.getScene().getWindow();
		boolean flag = false;
		//controllo che i campi siano stati compilati correttamente 
		
		//capofam
		if (textNomeCapoFam.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Nome");
			return;
		}
		if (textCognomeCapoFam.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Cognome");
			return;
		}
		if (textCFCapoFam.getText().isEmpty() || CFCheck(textCFCapoFam.getText()) == false) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Cf Valido");
			return;
		}
		if (dateDdNCapoFam.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una data di nascita");
			return;
		}
		if (textEmailCapoFam.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una E-mail");
			return;
		}
		
		
		//info famn
		if (textCodiceVacanzaFam.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Codice vacanza valido");
			return;
		}
		if (textNrComponentiFam.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire il numero di componenti della Famiglia");
			return;
		}
		if (textNrCamereFam.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire il numero di camere");
			return;
		}
		if (textNrBagniFam.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire il numero di bagni");
			return;
		}
		if (textDistanzaFam.getText().isEmpty() ) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire la distanza dal centro ");
			return;
		}
		
		if(checkAnimaliFam.isSelected()) flag = true;
		
		CapoFamiglia capoFam = new CapoFamiglia(textNomeCapoFam.getText(), textCognomeCapoFam.getText(), textCFCapoFam.getText(), dateDdNCapoFam.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), textEmailCapoFam.getText());
		Famiglia fam = new Famiglia(textCFCapoFam.getText(), textCodiceVacanzaFam.getText(), textNrComponentiFam.getText(), textNrCamereFam.getText(), textNrBagniFam.getText(), flag, textDistanzaFam.getText());
	
		//chiamata insert del jdbc
		
		try {
			PostreSQLJDBC.addFamiglia(capoFam, fam);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		resetFieldsFamiglia();
	}






	private boolean CFCheck(String text) {
		if(text.length() != 16) return false;
		return true;
	}
	
	
	private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
	
	
	
	
}
