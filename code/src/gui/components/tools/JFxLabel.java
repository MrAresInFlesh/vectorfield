package gui.components.tools;

import gui.components.tools.boxes.JFxHBox;
import gui.components.tools.style.STG;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class JFxLabel extends HBox {

	/*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
	public JFxLabel(String text) {
		super();
		this.hasLabel = true;
		this.lbl_text = new Label(text);
		this.component = null;
		geometry();
		control();
		appearance();
	}
	
	public JFxLabel(Node component) {
		super();
		hasNode = true;
		this.lbl_text = null;
		this.component = component;
		geometry();
		control();
		appearance();
	}
	
	public JFxLabel(String text, Node component) {
		super();
		hasLabel = false;
		hasNode = false;
		this.lbl_text = new Label(text);
		this.component = component;
		geometry();
		control();
		appearance();
	}

	/*------------------------------------------------------------------*|
	|*							Methods Private		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry() {
		JFxHBox mainLayout = new JFxHBox(10);
		if (hasNode) {
			mainLayout.getChildren().add(component);
		}
		else if(hasLabel) {
			mainLayout.getChildren().add(lbl_text);
		}
		else {
			mainLayout.getChildren().addAll(lbl_text, component);
		}
		getChildren().add(mainLayout);
	}
	
	private void control() {
		// nothing : in case of implementing listeners.
	}
	
	private void appearance() {
		setPadding(STG.MARGINS_INS);
		setBackground(new Background(new BackgroundFill(Color.web("#3d3f47"), new CornerRadii(0), Insets.EMPTY)));
		if (hasLabel) {
			lbl_text.setFont(Font.font("Consolas", FontWeight.LIGHT, 14));
			lbl_text.setTextFill(Color.WHITESMOKE);
		}
		else if (!hasNode) {
			lbl_text.setFont(Font.font("Consolas", FontWeight.LIGHT, 14));
			lbl_text.setTextFill(Color.WHITESMOKE);
		}
	}

	/*------------------------------------------------------------------*|
	|*							Attributes Private						*|
	|*------------------------------------------------------------------*/
	
	public Label getLbl_text () {
		return lbl_text;
	}
	
	// tools
	private final Label lbl_text;
	private final Node component;
	private	boolean hasLabel;
	private	boolean hasNode;
	
}
