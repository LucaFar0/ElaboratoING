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


public class homeRagazziController {
	@FXML private TextField textNomeProf;
	@FXML private TextField textCognomeProf;
	@FXML private TextField textCFProf;
	@FXML private DatePicker dateDdNprof;
	@FXML private TextField textEmailProf;
	@FXML private TextField textNrTelProf;
	@FXML private TextField textIndirizzoProf;
	@FXML private Button buttModNome;
	@FXML private Button buttModCognome;
	@FXML private Button buttModCF;
	@FXML private Button buttModDdN;
	@FXML private Button buttModEmail;
	@FXML private Button buttModNrTel;
	@FXML private Button buttModIndirizzo;
	@FXML private Button buttSave;
	
	
	public void modificaCognome(ActionEvent e) {
		textNomeProf.setEditable(true);
		textCognomeProf.setEditable(true);
		textCFProf.setEditable(true);
		dateDdNprof.setEditable(true);
		textEmailProf.setEditable(true);
		textNrTelProf.setEditable(true);
		textIndirizzoProf.setEditable(true);
		
	}
	
	public void salvaModifiche(ActionEvent e) {
		
	}
}
