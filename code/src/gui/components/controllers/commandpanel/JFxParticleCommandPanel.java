package gui.components.controllers.commandpanel;

import gui.components.tools.JFxGridPane;
import gui.components.tools.JFxLabel;
import gui.components.tools.JFxSpinner;
import gui.components.tools.boxes.JFxVBox;
import gui.components.tools.buttons.JFxIconButton;
import gui.components.tools.buttons.JFxToggleSwitch;
import gui.icons.IconsStore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class JFxParticleCommandPanel extends VBox {

	/*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
	public JFxParticleCommandPanel () {
		super();
		geometry();
		control();
		appearance();
	}
	
	public Slider getSld_speedScale () {
		return sld_speedScale;
	}
	public Slider getSld_inputOpacity () {
		return this.sld_inputOpacity;
	}
	public Slider getSld_inputSize () {
		return sld_inputSize;
	}
	public JFxLabel getLbl_size () {
		return lbl_size;
	}
	public JFxLabel getLbl_speedScale () {
		return lbl_speedScale;
	}
	public JFxLabel getLbl_opacity () {
		return lbl_opacity;
	}
	public JFxIconButton getBtn_resetZoom () {
		return btn_resetZoom;
	}
	public JFxSpinner getSpinner () {
		return spinner;
	}
	public JFxLabel getLbl_dispersion () {
		return lbl_dispersion;
	}
	public JFxToggleSwitch getToggleShadow () {
		return toggleShadow;
	}
	public JFxIconButton getBtn_resetParticles () {
		return btn_resetParticles;
	}
	
	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	
	private void geometry() {
		this.gridPane = new JFxGridPane();
		this.gridPane.setVgap(5);
		this.gridPane.setHgap(5);
		this.gridPane.setAlignment(Pos.CENTER);
		
		/*------------------------------------------------------------------*/
		this.sld_speedScale = new Slider(-100, 100, 1);
		this.lbl_speedScale = new JFxLabel("speed:" + sld_speedScale.getValue());
		this.gridPane.add(this.lbl_speedScale, 0, 0);
		this.gridPane.add(this.sld_speedScale, 1, 0);
		
		/*------------------------------------------------------------------*/
		this.sld_inputOpacity = new Slider(0.1, 1.0, 0.01);
		this.sld_inputOpacity.setValue(1.0);
		this.lbl_opacity = new JFxLabel("opacity:" + sld_inputOpacity.getValue());
		this.gridPane.add(this.lbl_opacity, 0, 1);
		this.gridPane.add(this.sld_inputOpacity, 1, 1);
		
		/*------------------------------------------------------------------*/
		this.sld_inputSize = new Slider(1, 10, 1);
		this.lbl_size = new JFxLabel("size: " + sld_inputSize.getValue());
		this.gridPane.add(this.lbl_size, 0, 2);
		this.gridPane.add(this.sld_inputSize, 1, 2);
		
		/*------------------------------------------------------------------*/
		this.btn_resetZoom = new JFxIconButton(new IconsStore(IconsStore.reset_zoom_url, 40), 50);
		this.btn_resetZoom.setLayoutX(5);
		this.btn_resetParticles = new JFxIconButton(new IconsStore(IconsStore.reset, 40), 50);
		this.gridPane.add(this.btn_resetZoom, 0, 3);
		this.gridPane.add(this.btn_resetParticles, 1, 3);
		
		/*------------------------------------------------------------------*/
		this.spinner = new JFxSpinner(5, 20, 10, 1);
		this.spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 20, 10, 1));
		this.spinner.setEditable(true);
		this.lbl_dispersion = new JFxLabel("dispersion:");
		this.gridPane.add(this.lbl_dispersion, 0, 4);
		this.gridPane.add(this.spinner, 1, 4);
		
		/*------------------------------------------------------------------*/
		this.toggleShadow = new JFxToggleSwitch();
		this.lbl_shadow = new JFxLabel("shadow");
		this.gridPane.add(this.lbl_shadow, 0, 5);
		this.gridPane.add(this.toggleShadow, 1, 5);
		
		this.gridPane.setAlignment(Pos.CENTER);
		JFxVBox mainLayout = new JFxVBox(2);
		mainLayout.getChildren().add(this.gridPane);
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
	private Slider sld_speedScale;
	private Slider sld_inputSize;
	private Slider sld_inputOpacity;
	private JFxLabel lbl_speedScale;
	private JFxLabel lbl_size;
	private JFxLabel lbl_opacity;
	private JFxIconButton btn_resetZoom;
	private JFxSpinner spinner;
	private JFxLabel lbl_dispersion;
	private JFxToggleSwitch toggleShadow;
	private JFxLabel lbl_shadow;
	private JFxIconButton btn_resetParticles;

}
