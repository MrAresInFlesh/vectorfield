package gui.components;

import gui.components.controllers.JFxFieldController;
import gui.components.controllers.JFxParticleController;
import gui.components.controllers.JFxVectorController;
import gui.components.tools.JFxPaneBar;
import gui.components.tools.boxes.JFxVBox;
import gui.components.tools.style.STG;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class JFxSettings extends VBox {
	
	/*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
	public JFxSettings () {
		super();
		geometry();
		control();
		appearance();
	}
	
	public JFxPaneBar getPaneBar () {
		return this.paneBar;
	}
	
	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry () {
		this.paneBar = new JFxPaneBar(new Label("SETTINGS"), STG.BTN_SIZE30, true);
		this.paneBar.focusedProperty();
		
		this.boxPaneBar = new JFxVBox(false);
		this.boxPaneBar.getChildren().addAll(this.paneBar);
		
		this.fieldController = new JFxFieldController();
		this.particleController = new JFxParticleController();
		this.vectorController = new JFxVectorController();
		
		this.boxSettings = new JFxVBox(false);
		this.boxSettings.getChildren().addAll(this.fieldController, this.particleController, this.vectorController);
		
		getChildren().addAll(this.boxPaneBar, this.boxSettings);
	}
	
	private void control() {
		if (this.paneBar.getHasButtons()) {
			this.paneBar.getBtn_minimize().setOnMousePressed(this::minimize);
			this.paneBar.getBtn_default().setOnMousePressed(this::dflt);
		}
	}
	
	private void appearance () {}
	
	/*------------------------------*|
	|*			  Events			*|
	|*------------------------------*/
	
	private void minimize (MouseEvent event) {
		if (this.paneBar.getBtn_minimize().isPressed()) {
			getChildren().remove(this.boxSettings);
		}
		setMaxHeight(this.boxPaneBar.getHeight());
	}
	
	private void dflt (MouseEvent event) {
		if (this.paneBar.getBtn_default().isPressed()) {
			if (!getChildren().contains(this.boxSettings)) getChildren().add(this.boxSettings);
		}
	}
	
	/*------------------------------*|
	|*		  Control Field			*|
	|*------------------------------*/
	
	public JFxFieldController getFieldController () {
		return this.fieldController;
	}
	
	public JFxParticleController getParticleController () {
		return this.particleController;
	}
	
	public JFxVectorController getVectorController () {
		return this.vectorController;
	}
	
	/*------------------------------------------------------------------*|
	|*							Private Attributes						*|
	|*------------------------------------------------------------------*/
	
	
	// tools
	private JFxPaneBar paneBar;
	private JFxFieldController fieldController;
	private JFxParticleController particleController;
	private JFxVectorController vectorController;
	
	// container
	private JFxVBox boxSettings;
	private	JFxVBox boxPaneBar;
}
