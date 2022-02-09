module Elaborato {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens Elaborao to javafx.graphics, javafx.fxml;
}
