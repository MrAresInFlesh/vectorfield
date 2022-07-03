package gui.tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TestJFxSettingsPanel extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
	
	    HBox hBox = new HBox();

	    Scene scene = new Scene(hBox);
	    scene.getStylesheets().add(this.getClass().getResource("../tests/stylesheet.css").toExternalForm());
        
        primaryStage.setTitle("java2s.com");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1500);
        primaryStage.setHeight(1000);
        primaryStage.show();
    }

}
