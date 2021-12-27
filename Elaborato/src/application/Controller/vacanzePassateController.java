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


public class vacanzePassateController {
	
	//--------------------- VACANZA -----------------------
	@FXML private TextField textCodiceVacanza;
	@FXML private TextField textCittaVacanza;
	@FXML private TextField textDataVacanza;
	@FXML private TextField textDurataVacanza;
	@FXML private TextField textLinguaVacanza;
	//-----Questionario
	@FXML private TextField textVotoQuestionario;
	@FXML private TextArea areaCommentoQuestionario;
	//----Certificazione
	@FXML private TextArea areaCertificazioneVacanza;
}
