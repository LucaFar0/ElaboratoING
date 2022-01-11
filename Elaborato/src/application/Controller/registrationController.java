package application.Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle ;

import application.Main;
import application.Model.Allergene;
import application.Model.Genitore;
import application.Model.Persona;
import application.Model.Ragazzo;
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
	
	
	private ArrayList <Allergene> A = new ArrayList<Allergene>();
	private ArrayList <Genitore> G = new ArrayList<Genitore>();
	private Window owner = butSaveRegistration.getScene().getWindow();
	
	public void aggiungiAllergene(ActionEvent e) {
		//controllo che i campi siano stati riempiti
		if (textRegNomeAllergene.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Nome");
			return;
		}

		if (textRegPrecauzioniAllergene.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire le precauzioni");
			return;
		}
		
		A.add(new Allergene(textRegNomeAllergene.getText(), textRegPrecauzioniAllergene.getText()));
		
		resetAllergeneFields();
	}

	public void saveRegistration(ActionEvent e) throws IOException {
		
		//controllo che i campi siano stati compilati correttamente 

		if (textRegNomeRag.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Nome");
			return;
		}
		if (textRegCognomeRag.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Cognome");
			return;
		}
		if (textRegCFRag.getText().isEmpty() || CFCheck(textRegCFRag.getText()) == false) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Cf Valido");
			return;
		}
		if (textRegEmailRag.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una E-mail");
			return;
		}
		if (textRegTelRag.getText().isEmpty() || textRegCFRag.getText().length() > 10) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un numero di telefono valido ");
			return;
		}
		if (textRegIndirizzoRag.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una indirizzo");
			return;
		}
		if (passwdReg.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una Password");
			return;
		}

		//Persona Ragazzo = new Persona(textRegNomeRag.getText(), textRegCognomeRag.getText(), textRegCFRag.getText(), textRegEmailRag.getText(), dateRegRag.getValue());
		Ragazzo Ragazzo = new Ragazzo(textRegNomeRag.getText(), textRegCognomeRag.getText(), textRegCFRag.getText(), textRegEmailRag.getText(), dateRegRag.getValue(), textRegTelRag.getText(), textRegHobby.getText(), passwdReg.getText(), textRegIndirizzoRag.getText());
		
		
		//controllo che sia stato messo almeno un genitore
		if(G.size() == 0 );
		
		//connessione Db + Insert Statement
		
		
		
		Main.changeScene("View/Accesso.fxml");
	}


	public void aggiungiGenitore(ActionEvent e) {
		if(G.size() < 2) {
			
			//controllo che i campi siano stati compilati correttamente 
			if (textRegNomeGen.getText().isEmpty()) {
				showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Nome");
				return;
			}
			if (textRegCognomeGen.getText().isEmpty()) {
				showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Cognome");
				return;
			}
			if (textRegCFGen.getText().isEmpty() || CFCheck(textRegCFRag.getText()) == false) {
				showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Cf Valido");
				return;
			}
			if (textRegEmailGen.getText().isEmpty()) {
				showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una E-mail");
				return;
			}
			if (textRegTelGen.getText().isEmpty() || textRegCFRag.getText().length() > 10) {
				showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un numero di telefono valido ");
				return;
			}
			
			
			G.add(new Genitore(textRegNomeGen.getText(), textRegCognomeGen.getText(), textRegCFGen.getText(), textRegEmailGen.getText(), dateRegGen.getValue(), textRegTelGen.getText()));
			resetGenitoreFields();
		}else {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Sono già stati inseriti 2 genitori!");
		}
		
	}
	
	private void resetAllergeneFields(){
		textRegNomeAllergene.setText("");
		textRegPrecauzioniAllergene.setText("");
	}
	private void resetGenitoreFields(){
		textRegNomeGen.setText("");
		textRegCognomeGen.setText("");
		textRegEmailGen.setText("");
		textRegCFGen.setText("");
		textRegTelGen.setText("");
	}
	
	
	private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
	
	
	private boolean CFCheck(String text) {
		if(text.length() != 16) return false;
		return true;
	}
}


