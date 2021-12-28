package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {	
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../View/Accesso.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root, 800, 500);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
