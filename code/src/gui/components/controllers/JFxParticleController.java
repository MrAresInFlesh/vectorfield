package gui.components.controllers;

import gui.components.controllers.commandpanel.JFxParticleCommandPanel;
import gui.components.tools.JFxPaneBar;
import gui.components.tools.boxes.JFxVBox;
import gui.components.tools.style.STG;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class JFxParticleController extends VBox {
	
    /*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
	public JFxParticleController () {
		super();
		isMovable = false;
		geometry();
		control();
		appearance();
	}
	
	public JFxPaneBar getPaneBar() {
		return paneBar;
	}
	
	public JFxParticleCommandPanel getPanel() {
		return panel;
	}

	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry() {
		setPrefWidth(STG.SETTINGS_PANE_WIDTH);
		paneBar = new JFxPaneBar(new Label("PARTICULE"), STG.BTN_SIZE20);
		paneBar.focusedProperty();
		
		panel = new JFxParticleCommandPanel();
		
		JFxVBox boxPaneBar = new JFxVBox(false);
		boxPaneBar.getChildren().addAll(paneBar);
		
		boxSettings = new JFxVBox(false);
		boxSettings.getChildren().addAll(panel);
		
		mainLayout = new JFxVBox(false);
		mainLayout.getChildren().addAll(boxPaneBar, boxSettings);
		
		getChildren().add(mainLayout);
	}
	
	private void control() {
		if (isMovable)
		{
			setOnMouseDragged(this::generalPaneModification);
			setOnMouseReleased(this::placePaneToOrigin);
		}
		
		if (paneBar.getHasButtons()) {
			paneBar.getBtn_minimize().setOnMousePressed(this::minimize);
			paneBar.getBtnInfos().setOnMousePressed(this::maximize);
			paneBar.getBtn_default().setOnMousePressed(this::dflt);
		}
	}
	
	private void appearance() {
	}
	
	/*------------------------------*|
	|*			  Events			*|
	|*------------------------------*/
	private void generalPaneModification (MouseEvent event) {
		if (paneBar.isPressed() && isMovable) {
			setCursor(Cursor.CLOSED_HAND);
			dragPaneToNewLocation(event);
		}
		else if (isInResizableZone(event)) {
			setCursor(Cursor.E_RESIZE);
			resize(event.getX(), event.getY());
		}
		event.consume();
	}
	private void dragPaneToNewLocation (MouseEvent event) {
		setManaged(false);
		setTranslateX(event.getSceneX());
		setTranslateY(event.getSceneY());
		event.consume();
	}
	private boolean isInResizableZone(MouseEvent event) {
		return (event.getX() <= getWidth() + 20 && event.getX() >= getWidth() -20);
	}
	private void placePaneToOrigin (MouseEvent event) {
		if (getTranslateX() < STG.ORIGIN && getTranslateY() < STG.ORIGIN) {
			setTranslateY(STG.ORIGIN);
			setTranslateX(STG.ORIGIN);
		}
		else if (getTranslateX() < STG.ORIGIN) setTranslateX(STG.ORIGIN);
		else if (getTranslateY() < STG.ORIGIN) setTranslateY(STG.ORIGIN);
		setCursor(Cursor.DEFAULT);
	}
	private void minimize (MouseEvent event) {
		if (paneBar.getBtn_minimize().isPressed()) {
			panel.setVisible(false);
			setWidth(STG.SETTINGS_PANE_WIDTH);
			setHeight(paneBar.getHeight());
		}
	}
	private void maximize (MouseEvent event) {
		if (paneBar.getBtnInfos().isPressed()) {
			panel.setVisible(true);
			setWidth(getScene().getWidth() - paneBar.localToScene(paneBar.getBoundsInLocal()).getMinX());
			setHeight(getScene().getHeight() - paneBar.localToScene(paneBar.getBoundsInLocal()).getMinY());
		}
	}
	private void dflt (MouseEvent event) {
		if (paneBar.getBtn_default().isPressed()) {
			panel.setVisible(true);
			setWidth(STG.SETTINGS_PANE_WIDTH);
		}
	}

	/*------------------------------------------------------------------*|
	|*							Attributes Private						*|
	|*------------------------------------------------------------------*/
	
	// tools
	private JFxVBox mainLayout;
	private JFxPaneBar paneBar;
	private boolean isMovable;
	
	// container
	private JFxParticleCommandPanel panel;
	private JFxVBox boxSettings;

}
