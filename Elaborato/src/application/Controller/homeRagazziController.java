package application.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle ;

import javax.swing.event.ChangeEvent;

import application.Main;
import application.Model.PostreSQLJDBC;
import application.Model.Ragazzo;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent ;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML ;
import javafx.fxml.Initializable ;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color ;
import javafx.scene.shape.Rectangle ;
import javafx.stage.Window;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;


public class homeRagazziController {
	Ragazzo user = Main.getUser();
	
	
	
	@FXML private TextField textNomeProf;
	@FXML private TextField textCognomeProf;
	@FXML private TextField textCFProf;
	@FXML private DatePicker dateDdNprof;
	@FXML private TextField textEmailProf;
	@FXML private TextField textNrTelProf;
	@FXML private TextField textIndirizzoProf;
	
	
	
	@FXML private Button buttMod;
	@FXML private Button buttSave;
	
	@FXML private AnchorPane pane;

	@FXML private Tab profilo;
	
	
	
	public void buttMod(ActionEvent e) {
		textNomeProf.setEditable(true);
		textCognomeProf.setEditable(true);
		//textCFProf.setEditable(true);
		dateDdNprof.setEditable(true);
		textEmailProf.setEditable(true);
		textNrTelProf.setEditable(true);
		textIndirizzoProf.setEditable(true);
		buttSave.setDisable(false);
		
	}
	
	public void salvaModifiche(ActionEvent e) {
		System.out.println(user.toString());
		
		Window owner = buttSave.getScene().getWindow();
		//controllo che i campi siano stati compilati correttamente 
		
		if (textNomeProf.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Nome");
			return;
		}
		if (textCognomeProf.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Cognome");
			return;
		}
		if (textCFProf.getText().isEmpty() || CFCheck(textCFProf.getText()) == false) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un Cf Valido");
			return;
		}
		if (dateDdNprof.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una data di partenza");
			return;
		}
		if (textEmailProf.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una E-mail");
			return;
		}
		if (textNrTelProf.getText().isEmpty() || textNrTelProf.getText().length() < 10) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire un numero di telefono valido ");
			return;
		}
		if (textIndirizzoProf.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una indirizzo");
			return;
		}
		
		Ragazzo Ragazzo = new Ragazzo(textNomeProf.getText(), textCognomeProf.getText(), null, textEmailProf.getText(), dateDdNprof.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), textNrTelProf.getText(), null, null, textIndirizzoProf.getText());
		System.out.println(Ragazzo.toString());
		
		
		
		//esecuzione query tramite jdbc
		try {
			PostreSQLJDBC.UpdateUser(user, Ragazzo);
			updateView(Ragazzo);
			textNomeProf.setEditable(false);
			textCognomeProf.setEditable(false);
			//textCFProf.setEditable(true);
			dateDdNprof.setEditable(false);
			textEmailProf.setEditable(false);
			textNrTelProf.setEditable(false);
			textIndirizzoProf.setEditable(false);
			buttSave.setDisable(true);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
	


	public void setPage(MouseEvent e) {
		textNomeProf.setText(user.getNome());
		textCognomeProf.setText(user.getCognome());
		textCFProf.setText(user.getCF());
		
		//convert String to LocalDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		dateDdNprof.setValue(LocalDate.parse(user.getDdN(), formatter));
		
		textEmailProf.setText(user.getEmail());
		textNrTelProf.setText(user.getNrTelefono());
		textIndirizzoProf.setText(user.getIndirizzo());	
	}
	
	
	//metodo che aggiorna i campi dell'utente dopo la modifica
	private void updateView(Ragazzo x) {
		textNomeProf.setText(x.getNome());
		textCognomeProf.setText(x.getCognome());
		
		
		//convert String to LocalDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		dateDdNprof.setValue(LocalDate.parse(x.getDdN(), formatter));
		
		textEmailProf.setText(x.getEmail());
		textNrTelProf.setText(x.getNrTelefono());
		textIndirizzoProf.setText(x.getIndirizzo());	
	}

}
