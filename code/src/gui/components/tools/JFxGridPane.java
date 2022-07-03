package gui.components.tools;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class JFxGridPane extends GridPane {
	
	/*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
	public JFxGridPane () {
		super();
		geometry();
		appearance();
	}
	
	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry () {
		setPadding(new Insets(20));
		setVgap(5);
		setHgap(5);
	}
	
	private void appearance () {
		setBackground(new Background(new BackgroundFill(Color.web("#3d3f47"), CornerRadii.EMPTY, Insets.EMPTY)));
	}
}
