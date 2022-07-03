package gui.components.tools;

import gui.components.tools.boxes.JFxHBox;
import gui.components.tools.style.STG;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class JFxTextField extends TextField {
	
	public JFxTextField () {
		super();
		geometry();
		control();
		appearance();
	}

    /*------------------------------------------------------------------*|
	|*							Methods Public							*|
	|*------------------------------------------------------------------*/

	/*------------------------------*|
	|*				Get				*|
	|*------------------------------*/

	/*------------------------------------------------------------------*|
	|*							Methods Private		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry() {
		JFxHBox mainLayout = new JFxHBox(5);
		mainLayout.getChildren().add(this);
	}
	
	private void control() {
	
	}
	
	private void appearance() {
		setPadding(STG.MARGINS_INS);
		setBackground(new Background(new BackgroundFill(Color.web("#161a2d"), new CornerRadii(0), Insets.EMPTY)));
		setFont(Font.font("Consolas", FontWeight.LIGHT, 18));
		
		setStyle("-fx-background-color: #18427a, black;" +
		         "-fx-text-fill: #ffffff;" +
		         "-fx-background-radius: 0;" +
				 "-fx-background-insets: 0, 2 0 0 0 ;\n" +
				 "-fx-padding: 2 ;\n" +
				 "-fx-alignment: center-right ;");
	}

	/*------------------------------------------------------------------*|
	|*							Attributes Private						*|
	|*------------------------------------------------------------------*/

	// tools

}
