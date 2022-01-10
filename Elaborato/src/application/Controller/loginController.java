package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle ;

import application.Main;
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
	@FXML
	private TextField textEmail;
	@FXML private PasswordField textPasswd;
	@FXML private Button logButton;
	@FXML private Button logIscrivi;

	//controllo credenziali e correttezza campi inseriti
	public void login(ActionEvent e) throws IOException {
		Window owner = logButton.getScene().getWindow();
		
		Main.changeScene("View/HomeRagazzi.fxml");
		

		

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
