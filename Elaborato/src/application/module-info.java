module Elaborato {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens Elaborato to javafx.graphics, javafx.fxml;
}
