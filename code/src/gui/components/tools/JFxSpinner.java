package gui.components.tools;

import gui.components.tools.style.STG;
import javafx.scene.control.Spinner;

public class JFxSpinner extends Spinner {
	
	/*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/

	public JFxSpinner (int min, int max, int initialValue, int amountToStepBy) {
		super(min, max, initialValue, amountToStepBy);
		geometry();
		control();
		appearance();
	}
	
	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry () {
	}
	
	private void control () {
	
	}
	
	private void appearance () {
		setPadding(STG.MARGINS_INS);
		setStyle("-fx-background-color: transparent;" +
				 "-fx-text-fill: #ffffff;" +
				 "-fx-body-color: transparent;");
		setStyle(".spinner .increment-arrow-button .increment-arrow" +
		         "{ -fx-background-color: -fx-mark-highlight-color, -fx-mark-color; " +
		          "-fx-background-insets: 0 0 -1 0, 0;" +
		          "-fx-padding: 0.166667em 0.333333em 0.166667em 0.333333em;" +
		          "}");
	}
	
	/*------------------------------------------------------------------*|
	|*						 Private Attributes 	 					*|
	|*------------------------------------------------------------------*/
	
	// tools
	
}
