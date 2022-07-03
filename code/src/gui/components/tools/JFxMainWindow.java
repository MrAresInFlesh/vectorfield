package gui.components.tools;

import gui.components.JFxSettings;
import gui.components.JFxVectorField;
import gui.components.tools.style.STG;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import prog.tools.VF_Visualizer;

public class JFxMainWindow extends BorderPane {
	
	public JFxMainWindow (JFxSettings settings, JFxVectorField vectorField) {
		super();
		this.windowBar = new JFxWindowBar(new Label("Project P2"), STG.BTN_SIZE20);
		this.jfx_vectorField = vectorField;
		this.jfx_settings = settings;
		geometry();
		control();
		appearance();
	}
	
	public JFxWindowBar getWindowBar () {
		return windowBar;
	}
	
	/*------------------------------*|
	|*			  Structure			*|
	|*------------------------------*/
	
	private void geometry () {
		setMargin(jfx_vectorField, STG.MARGINS_INS);
		setAlignment(jfx_vectorField, Pos.CENTER);
		windowBar.setMinWidth(this.getWidth());
		setCenter(jfx_vectorField);
		setRight(jfx_settings);
		setTop(windowBar);
	}
	
	private void control () {
		// basic on window
		setOnMouseDragged(this::dragPaneToNewLocation);
		windowBar.getBtn_close().setOnMousePressed(this::close);
		windowBar.getBtn_minimize().setOnMousePressed(this::minimize);
		windowBar.getBtn_default().setOnMousePressed(this::default_view);
		}
	
	private void appearance () {
		setBackground(new Background(new BackgroundFill(STG.GREY_G, new CornerRadii(5), Insets.EMPTY)));
	}
	
	/*------------------------------*|
	|*			  Events			*|
	|*------------------------------*/
	
	private void dragPaneToNewLocation (MouseEvent event) {
		setManaged(false);
		if (windowBar.isPressed()) {
			setTranslateX(event.getSceneX() - windowBar.getxOffset());
			setTranslateY(event.getSceneY() - windowBar.getyOffset());
		}
		event.consume();
	}
	
	private void minimize (MouseEvent event) {
		if (windowBar.getBtn_minimize().isPressed()) {
			rightProperty().get().setVisible(false);
			centerProperty().get().setVisible(false);
			setWidth(STG.SETTINGS_PANE_WIDTH);
			setHeight(windowBar.getHeight());
		}
	}
	
	private void close (MouseEvent event) {
		if (windowBar.getBtn_close().isPressed()) {
			Stage stage = (Stage) windowBar.getBtn_close().getScene().getWindow();
			stage.close();
		}
	}
	
	private void default_view (MouseEvent event) {
		if (windowBar.getBtn_default().isPressed()) {
			if (jfx_vectorField != null) {
				for (Node n: this.getChildren()) {
					n.setVisible(true);
				}
				setWidth(jfx_vectorField.getWIDTH());
				setHeight(jfx_vectorField.getHEIGHT());
			}
		}
	}
	
	/*------------------------------------------------------------------*|
	|*							Private Attributes						*|
	|*------------------------------------------------------------------*/

	// tools
	JFxWindowBar windowBar;
	JFxVectorField jfx_vectorField;
	JFxSettings jfx_settings;
	
}
