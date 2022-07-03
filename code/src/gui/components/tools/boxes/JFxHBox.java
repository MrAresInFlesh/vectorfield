package gui.components.tools.boxes;


import gui.components.tools.style.STG;
import javafx.scene.layout.HBox;

public class JFxHBox extends HBox {

    /*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
	public JFxHBox (double spacing) {
        super(spacing);
	    geometry();
    }
	
	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry () {
		setPadding(STG.MARGINS_INS);
	}
	
}
