package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle ;

import application.Main;
import application.Model.PostreSQLJDBC;
import application.Model.*;
import javafx.event.ActionEvent ;
import javafx.fxml.FXML ;
import javafx.fxml.Initializable ;
import javafx.scene.control.Label ;
import javafx.scene.paint.Color ;
import javafx.scene.shape.Rectangle ;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class loginController {
	@FXML private TextField textEmail;
	@FXML private PasswordField textPasswd;
	@FXML private Button logButton;
	@FXML private Button logIscrivi;

	//controllo credenziali e correttezza campi inseriti
	public void login(ActionEvent e) throws IOException {
		Window owner = logButton.getScene().getWindow();
		
		if (textEmail.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
					"Please enter your email id");
			return;
		}
		if (textPasswd.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
					"Please enter a password ");
			return;
		}

		System.out.println(textEmail.getText());
		System.out.println(textPasswd.getText());
		
		Ragazzo rag = new Ragazzo(null, null, null, null, null, null, null, null, null);
		Responsabile resp = new Responsabile(null, null, null, null, null, null, null);
		

		try {
			if(PostreSQLJDBC.ValidateUser(textEmail.getText(), textPasswd.getText(), rag) == true ) {
					//System.out.println(rag.toString());
				Main.setUser(rag);
				Main.changeScene("View/HomeRagazzi2.fxml");
				
			}else if(PostreSQLJDBC.ValidateResp(textEmail.getText(), textPasswd.getText(), resp) == true ) {
			
				Main.changeScene("View/HomeResponsabili.fxml");
			
			}else {
				showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Credenziali errate ");
				resetTextFields();
				return;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		resetTextFields();
	}
	
	// lancia la scena della registrazione
	public void iscrizione(ActionEvent e) throws IOException {
		Main.changeScene("View/Registrazione.fxml");
	}

	private void resetTextFields(){
		textEmail.setText("");
		textPasswd.setText("");
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
