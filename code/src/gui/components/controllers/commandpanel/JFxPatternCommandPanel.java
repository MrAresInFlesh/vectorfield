package gui.components.controllers.commandpanel;

import gui.components.tools.JFxGridPane;
import gui.components.tools.boxes.JFxVBox;
import gui.components.tools.buttons.JFxIconButton;
import gui.icons.IconsStore;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class JFxPatternCommandPanel extends VBox {
	
	/*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
	public JFxPatternCommandPanel () {
		super();
		geometry();
		control();
		appearance();
	}
	
	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry() {
		gridPane = new JFxGridPane();
		
		/*------------------------------------------------------------------*/
		this.btn_allZeros = new JFxIconButton(new IconsStore(IconsStore.all_zeros_url, PATTERN_BTN_WIDTH), PATTERN_BTN_WIDTH);
		this.gridPane.add(this.btn_allZeros, 0, 0);
		
		/*------------------------------------------------------------------*/
		this.btn_rotation = new JFxIconButton(new IconsStore(IconsStore.rotation_url, PATTERN_BTN_WIDTH), PATTERN_BTN_WIDTH);
		this.gridPane.add(this.btn_rotation, 1, 0);
		
		/*------------------------------------------------------------------*/
		this.btn_horizontalCut = new JFxIconButton(new IconsStore(IconsStore.horizontal_cut_url, PATTERN_BTN_WIDTH), PATTERN_BTN_WIDTH);
		this.gridPane.add(this.btn_horizontalCut, 2, 0);
		
		/*------------------------------------------------------------------*/
		this.btn_verticalCut = new JFxIconButton(new IconsStore(IconsStore.vertical_cut_url, PATTERN_BTN_WIDTH), PATTERN_BTN_WIDTH);
		this.gridPane.add(this.btn_verticalCut, 0, 1);
		
		/*------------------------------------------------------------------*/
		this.btn_alternate = new JFxIconButton(new IconsStore(IconsStore.alternate_url, PATTERN_BTN_WIDTH), PATTERN_BTN_WIDTH);
		this.gridPane.add(this.btn_alternate, 1, 1);
		
		/*------------------------------------------------------------------*/
		
		gridPane.setAlignment(Pos.CENTER);
		GridPane.setHalignment(this.btn_alternate, HPos.CENTER);
		gridPane.setPrefSize(400, 200);
		JFxVBox mainLayout = new JFxVBox(2);
		mainLayout.getChildren().add(gridPane);
		mainLayout.setAlignment(Pos.CENTER);
		
		getChildren().add(mainLayout);
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
	private JFxIconButton btn_allZeros;
	private JFxIconButton btn_rotation;
	private JFxIconButton btn_horizontalCut;
	private JFxIconButton btn_verticalCut;
	private JFxIconButton btn_alternate;
	private double PATTERN_BTN_WIDTH = 100;

}
