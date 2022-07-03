package gui.components.controllers.commandpanel;

import gui.components.tools.buttons.JFxIconButton;
import gui.icons.IconsStore;
import gui.components.tools.JFxGridPane;
import gui.components.tools.JFxLabel;
import gui.components.tools.JFxSpinner;
import gui.components.tools.boxes.JFxVBox;
import gui.components.tools.buttons.JFxStdButtons;
import gui.components.tools.buttons.JFxToggleSwitch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

public class JFxFieldCommandPanel extends VBox {

    /*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
	public JFxFieldCommandPanel () {
		super();
		geometry();
		control();
		appearance();
	}
	
	public JFxSpinner getSpinner () {
		return spinner;
	}
	
	public JFxToggleSwitch getToggleSwitch () {
		return toggleSwitch;
	}
	
	public JFxStdButtons getStdButtons () {
		return stdButtons;
	}
	
	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry() {
		this.gridPane = new JFxGridPane();
		
		/*------------------------------------------------------------------*/
		
		this.toggleSwitch = new JFxToggleSwitch();
		this.lbl_gravity = new JFxLabel("Show cells : ", this.toggleSwitch);
		this.gridPane.add(this.lbl_gravity, 0, 0);
		
		/*------------------------------------------------------------------*/
		
		this.spinner = new JFxSpinner(30, 150, 80, 1);
		this.spinner.setValueFactory(new IntegerSpinnerValueFactory(30, 150, 80));
		this.spinner.setEditable(true);
		
		this.lbl_density = new JFxLabel("density : ", this.spinner);
		this.gridPane.add(this.lbl_density, 0, 1);
		
		/*------------------------------------------------------------------*/
		
		this.stdButtons = new JFxStdButtons(0, new IconsStore(IconsStore.start_url, 50), new IconsStore(IconsStore.pause_url, 50), new IconsStore(IconsStore.reset_url, 50));
		this.gridPane.add(this.stdButtons, 0, 2);
		
		/*------------------------------------------------------------------*/

		this.gridPane.setAlignment(Pos.CENTER);
		this.mainLayout = new JFxVBox(2);
		this.mainLayout.getChildren().add(this.gridPane);
		this.mainLayout.setAlignment(Pos.CENTER);
		
		getChildren().add(this.mainLayout);
	}
	
	private void control() {
	
	}
	
	private void appearance() {
		setBackground(new Background(new BackgroundFill(Color.web("#161a2d"), CornerRadii.EMPTY, Insets.EMPTY)));
	}

	/*------------------------------------------------------------------*|
	|*						  Private Attributes	    				*|
	|*------------------------------------------------------------------*/
	
	// tools
	private JFxGridPane gridPane;
	private JFxSpinner spinner;
	private JFxToggleSwitch toggleSwitch;
	private JFxLabel lbl_gravity;
	private JFxLabel lbl_density;
	private JFxStdButtons stdButtons;
	private JFxIconButton btn_resetZoom;
	private JFxVBox mainLayout;
}
