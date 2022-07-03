package gui.components.tools;

import gui.components.tools.style.STG;
import gui.icons.IconsStore;
import gui.components.tools.boxes.JFxHBox;
import gui.components.tools.buttons.JFxIconButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class JFxPaneBar extends HBox {
	
    /*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
	public JFxPaneBar (Label paneTitle, double size) {
		super();
		this.lbl_paneTitle = paneTitle;
		this.size = size;
		hasButtons = false;
		geometry();
		control();
		appearance();
	}
	
	public JFxPaneBar (Label paneTitle, double size, boolean buttons) {
		super();
		this.lbl_paneTitle = paneTitle;
		this.size = size;
		hasButtons = buttons;
		geometry();
		control();
		appearance();
	}
	
	/*------------------------------------------------------------------*|
	|*						  Public Methods		    				*|
	|*------------------------------------------------------------------*/
	
	public void setHasButtons(boolean b) {
		hasButtons = b;
	}
	
	/*------------------------------*|
	|*				Get				*|
	|*------------------------------*/
	
	public boolean getHasButtons() {
		return hasButtons;
	}
	
	public JFxIconButton getBtn_minimize () {
		return btn_minimize;
	}
	
	public JFxIconButton getBtnInfos () {
		return btn_maximize;
	}
	
	public JFxIconButton getBtn_default () {
		return btn_default;
	}
	
	public double getxOffset () {
		return xOffset;
	}
	
	public double getyOffset () {
		return yOffset;
	}
	
	public void getOffsets (MouseEvent event) {
		xOffset = event.getX();
		yOffset = event.getY();
	}
	
	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry () {
		title();
		
		JFxHBox mainLayout;
		if (hasButtons) {
			buttons();
			mainLayout = new JFxHBox(0);
			mainLayout.getChildren().addAll(left_LabelBox, fp_bar);
		}
		else {
			mainLayout = new JFxHBox(0);
			mainLayout.getChildren().addAll(left_LabelBox);
		}
		getChildren().addAll(mainLayout);
	}
	
	private void control () {
		brightness = new ColorAdjust();
		brightness.setBrightness(+0.1);
		setOnMouseEntered(event -> this.setEffect(brightness));
		setOnMouseExited(event -> this.setEffect(null));
		setOnMousePressed(this::getOffsets);
	}
	
	private void appearance () {
		setBackground(new Background(new BackgroundFill(STG.DARK_BLUE_G, CornerRadii.EMPTY, Insets.EMPTY)));
		lbl_paneTitle.setFont(Font.font("Consolas", FontWeight.BOLD, 16));
		lbl_paneTitle.setTextFill(Color.WHITESMOKE);
	}
	
	private void title () {
		left_LabelBox = new JFxHBox(5);
		left_LabelBox.setAlignment(Pos.CENTER_LEFT);
		left_LabelBox.setMinSize(100, 20);
		left_LabelBox.getChildren().add(lbl_paneTitle);
	}
	
	private void buttons () {
		btn_default = new JFxIconButton(new IconsStore(IconsStore.default_url, size), size);
		btn_default.setTransparent();
		
		btn_minimize = new JFxIconButton(new IconsStore(IconsStore.minus_url, size), size);
		btn_minimize.setTransparent();
		
		btn_maximize = new JFxIconButton(new IconsStore(IconsStore.plus_url, size), size);
		btn_maximize.setTransparent();
		
		fp_bar = new FlowPane(btn_default, btn_minimize, btn_maximize);
		fp_bar.setAlignment(Pos.CENTER_RIGHT);
	}
	
	// tools
	private final Label lbl_paneTitle;
	private final double size;
	private JFxIconButton btn_minimize;
	private JFxIconButton btn_maximize;
	private JFxIconButton btn_default;
	private ColorAdjust brightness;
	private boolean hasButtons;
	
	// container
	private JFxHBox left_LabelBox;
	private FlowPane fp_bar;
	
	// window
	private double xOffset;
	private double yOffset;
	
}
