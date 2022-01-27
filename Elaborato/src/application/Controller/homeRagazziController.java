package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.event.ChangeEvent;

import application.Main;
import application.Model.*;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent ;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML ;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable ;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color ;
import javafx.scene.shape.Rectangle ;
import javafx.stage.Window;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;


public class homeRagazziController implements Initializable{
	Ragazzo user = Main.getUser();
	ArrayList<Vacanza> vacanze = null;
	ArrayList<College> college = null;
	String out = "";
	String separatoreVacanza = "\n\n\n-----------------------------------V A C A N Z A-----------------------------------------------------------------------------------------------\n";
	
	
	//----------------------------------------------------Vacanze Future--------------------------------------------------------------------------------------------------------------------------------
	
	@FXML private DatePicker dateVisualizza;
	@FXML private TextField textVisualizzaDurata;
	@FXML private TextField textVisualizzaCitta;
	@FXML private Button buttFiltroData;
	@FXML private Button buttFiltroDurata;
	@FXML private Button buttFiltroCitta;
	
	@FXML private ScrollPane scroll;
	@FXML private AnchorPane ancora;
	@FXML private TextArea areaVacanze ;
	
	
	//---------------------	PRENOTAZIONI-------------------------
	//college
	@FXML private ChoiceBox<String> choicePrenotaCollegeVacanza;
	@FXML private ChoiceBox<String> choicePrenotaCollege;
	@FXML private ChoiceBox<String> choicePrenotaCollegePaga;
	@FXML private ChoiceBox<String> choicePrenotaCollegeStanza;
	@FXML private Button buttPrenotaCollege;
	
	private String[] pagamento = {"Carta di credito", "Bonifico"};
	private String[] tipoStanza = {"Singola", "Doppia"};
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		choicePrenotaCollegeVacanza.setOnAction(this::setCollege);
		
		
	}
	
	
	
	
	private void setCollege(ActionEvent e) {
		String vac = choicePrenotaCollegeVacanza.getValue();
		String[] codCollege = new String[100];
		 try {
			ArrayList<College> coll = PostreSQLJDBC.getCollegeVacanza(vac);
			int j = 0;
			for(College i: coll) {
				codCollege[j] = i.getCodice();
				j++;
			}
			choicePrenotaCollege.getItems().addAll(codCollege);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}




	// cancello i valori inseriti nei filtri
	private void resetFiltri() {
		dateVisualizza.setValue(null);
		textVisualizzaDurata.setText(null);
		textVisualizzaCitta.setText(null);
		
	}
	
	private void clearBox() {
		choicePrenotaCollegeVacanza.getItems().clear();
		choicePrenotaCollege.getItems().clear();
	}
	
	
	
	// Eseguo la selezione per data
	public void FiltraData(ActionEvent e) throws IOException {
		clearBox();
		out = "";
		String[] codVacanze = new String[100];
		String[] codCollege;
		if(college != null) college.clear();
		if(vacanze != null) vacanze.clear();
		
		
		Window owner = buttFiltroData.getScene().getWindow();

		if (dateVisualizza.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una data di partenza");
			return;
		}
		String data = dateVisualizza.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));	
		try {
			vacanze = PostreSQLJDBC.getVacanzaData(data);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int j = 0;
		for(Vacanza i: vacanze) {
			codVacanze[j] = i.getCodice();// + " | " + i.getCitta();
			System.out.println(i.toString2());
			System.out.println(getGite(i.getCodice()));
			out += separatoreVacanza;
			out += i.toString2() + getGite(i.getCodice()) + getCollege(i.getCodice()) + getFam(i.getCodice());
			j++;
		}
	
		choicePrenotaCollegeVacanza.getItems().addAll(codVacanze);
		choicePrenotaCollegePaga.getItems().addAll(pagamento);
		choicePrenotaCollegeStanza.getItems().addAll(tipoStanza);
		
		
		areaVacanze.replaceText(0, areaVacanze.getLength(), out);
		
		resetFiltri();
	}
	
	
	// Eseguo la selezione per durata
	public void FiltraDurata(ActionEvent e) {
		clearBox();
		out = "";
		String[] codVacanze = new String[100];
		String[] codCollege;
		if(college != null) college.clear();
		if(vacanze != null) vacanze.clear();
		
		
		Window owner = buttFiltroDurata.getScene().getWindow();


		if (textVisualizzaDurata.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire la durata preferita");
			return;
		}
		String durata = textVisualizzaDurata.getText();	
		
		try {
			vacanze = PostreSQLJDBC.getVacanzaDurata(durata);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int j = 0;
		for(Vacanza i: vacanze) {
			codVacanze[j] = i.getCodice();// + " | " + i.getCitta();
			System.out.println(i.toString2());
			System.out.println(getGite(i.getCodice()));
			out += separatoreVacanza;
			out += i.toString2() + getGite(i.getCodice()) + getCollege(i.getCodice()) + getFam(i.getCodice());
			j++;
		}
		choicePrenotaCollegeVacanza.getItems().addAll(codVacanze);
		choicePrenotaCollegePaga.getItems().addAll(pagamento);
		choicePrenotaCollegeStanza.getItems().addAll(tipoStanza);
		
		areaVacanze.replaceText(0, areaVacanze.getLength(), out);
		
		resetFiltri();
	}
	
	
	// Eseguo la selezione per Città
	public void FiltraCitta(ActionEvent e) {
		clearBox();
		out = "";
		String[] codVacanze = new String[100];
		String[] codCollege;
		if(college != null) college.clear();
		if(vacanze != null) vacanze.clear();
		
		
		
		Window owner = buttFiltroDurata.getScene().getWindow();


		if (textVisualizzaCitta.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Inserire una Città");
			return;
		}
		String citta = textVisualizzaCitta.getText();	

		try {
			vacanze = PostreSQLJDBC.getVacanzaCitta(citta);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int j = 0;
		for(Vacanza i: vacanze) {
			
			codVacanze[j] = i.getCodice();// + " | " + i.getCitta();
			System.out.println(i.toString2());
			System.out.println(getGite(i.getCodice()));
			out += separatoreVacanza;
			out += i.toString2() + getGite(i.getCodice()) + getCollege(i.getCodice()) + getFam(i.getCodice());
			j++;
		}
		choicePrenotaCollegeVacanza.getItems().addAll(codVacanze);
		choicePrenotaCollegePaga.getItems().addAll(pagamento);
		choicePrenotaCollegeStanza.getItems().addAll(tipoStanza);
		
		areaVacanze.setText(out);
		
		resetFiltri();
	}

	
	
	public String getGite(String codice) {
		String g = "\n		--------------------Gite---------------------------------";
		try {
			ArrayList<Gita> gite = PostreSQLJDBC.getGitaVacanza(codice);
			for(Gita i: gite) {
				g += i.toString2();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if(g == null) g = "";
		return g;
		
	}
	
	public String getCollege(String codice) {
		String g = "\n		--------------------College---------------------------------";
		try {
			 college = PostreSQLJDBC.getCollegeVacanza(codice);
			
		
			for(College i: college) {
				
				g += i.toString2();
				g += "\n 			------------------------Attività---------------------------   " + getAttivita(i.getCodice());
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if(g == null) g = "";
		return g;
		
	}
	

	public String getAttivita(String codice) {
		String g = null;
		try {
			ArrayList<Attivita> a = PostreSQLJDBC.getAttivitaCollegeVacanza(codice);
			for(Attivita i: a) {
				g += i.toString2();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(g == null) g = "";
		return g;
		
	}
	
	public String getFam(String codice) {
		String g = "\n		--------------------Famiglie---------------------------------";
		ArrayList<CapoFamiglia> capo = new ArrayList<CapoFamiglia>();
		ArrayList<Famiglia> fam = new ArrayList<Famiglia>();
		try {
			PostreSQLJDBC.getFamigliaVacanza(codice, capo, fam);
			int x = 0;
			for(CapoFamiglia i: capo) {
				g += i.toString2() + fam.get(x).toString2();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if(g == null) g = "";
		return g;
		
	}
	
	
	
	
	// prenotazione College
	public void PrenotaCollege(ActionEvent e) {
		PrenotazioneCollege prenotazione =  new PrenotazioneCollege(choicePrenotaCollegeVacanza.getValue(), user.getCF(), choicePrenotaCollege.getValue(), choicePrenotaCollegeStanza.getValue(), choicePrenotaCollegePaga.getValue());
		
		try {
			PostreSQLJDBC.addPrenotazioneCollege(prenotazione);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	
	}

	
	
	
	
	
	
	//----------------------------------------------------------Profilo-----------------------------------------------------------------------------------------------------------------------
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
	
	
	
	// metodo che consente di modificare i dati
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
	

	//metodo che carica i dati dell'utente nei relativi spazi
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
