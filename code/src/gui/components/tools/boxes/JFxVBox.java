package gui.components.tools.boxes;

import gui.components.tools.style.STG;
import javafx.scene.layout.VBox;

public class JFxVBox extends VBox {
	
	/*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
	public JFxVBox (boolean hasMargin) {
		super();
		this.hasMargin = hasMargin;
		geometry();
		control();
		appearance();
	}
	
	public JFxVBox (double spacing) {
		super(spacing);
		this.hasMargin = true;
		geometry();
		control();
		appearance();
	}
	
	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry () {
		if (this.hasMargin) {
			setPadding(STG.MARGINS_INS);
		}
	}
	
	private void control () {

	}
	
	private void appearance () {

	}
	
	/*------------------------------------------------------------------*|
	|*						 Private Attributes 	 					*|
	|*------------------------------------------------------------------*/
	
	// tools
	private final boolean hasMargin;
	
}
