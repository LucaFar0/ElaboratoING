package application;
	
import java.io.IOException;

import application.Controller.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

//s0ftw4re3ng


public class Main extends Application {
	public static Stage stage;
	@Override
	public void start(Stage stage) throws Exception {	
		Main.stage = stage;
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Accesso.fxml"));
		Parent root = loader.load();
		
		
		Scene scene = new Scene(root, 1000, 600);
	
		
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void changeScene(String fxml) throws IOException{
	    Parent pane = FXMLLoader.load(Main.class.getResource(fxml));
		

	   Scene scene = new Scene(pane, 1000, 600);
	   try {
		stage.setScene(scene);
	   } catch (Exception e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
