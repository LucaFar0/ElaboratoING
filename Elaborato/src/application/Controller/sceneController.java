package application.Controller;

import java.io.IOException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class sceneController {
	private HashMap<String, Pane> screenMap = new HashMap<>();
	private Scene main;

	public void ScreenController(Scene main) {
		this.main = main;
	}

	protected void addScreen(String name, Pane pane){
		screenMap.put(name, pane);
	}

	protected void removeScreen(String name){
		screenMap.remove(name);
	}

	protected void activate(String name){
		main.setRoot( screenMap.get(name) );
	}

}
