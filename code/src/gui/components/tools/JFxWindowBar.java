package gui.components.tools;

import gui.components.tools.boxes.JFxHBox;
import gui.components.tools.buttons.JFxIconButton;
import gui.components.tools.style.STG;
import gui.icons.IconsStore;
import javafx.animation.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class JFxWindowBar extends HBox {

    /*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
	public JFxWindowBar (Label paneTitle, double size) {
		super();
		this.lbl_paneTitle = paneTitle;
		this.size = size;
		geometry();
		control();
		appearance();
	}
	
	/*------------------------------------------------------------------*|
	|*						  Public Methods		    				*|
	|*------------------------------------------------------------------*/
	
	/*------------------------------*|
	|*				Get				*|
	|*------------------------------*/
	
	public JFxIconButton getBtn_minimize () {
		return btn_minimize;
	}
	
	public JFxIconButton getBtn_close () {
		return btn_close;
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
		buttons();
		JFxHBox layout = new JFxHBox(5);
		layout.getChildren().addAll(btn_default, btn_minimize, btn_close);
		left_LabelBox.setAlignment(Pos.CENTER_LEFT);
		layout.setAlignment(Pos.CENTER_RIGHT);
		setHgrow(layout, Priority.ALWAYS);
		getChildren().addAll(left_LabelBox, layout);
	}
	
	private void control () {
		brightness = new ColorAdjust();
		brightness.setBrightness(+0.1);
		setOnMouseEntered(event -> this.setEffect(brightness));
		setOnMouseExited(event -> this.setEffect(null));
		setOnMousePressed(this::getOffsets);
	}
	
	private void appearance () {
		lbl_paneTitle.setFont(Font.font("Consolas", FontWeight.BOLD, 16));
		lbl_paneTitle.setTextFill(Color.WHITESMOKE);
		animateBackground();
	}
	
	public void animateBackground () {
		this.baseColor = new SimpleObjectProperty<>();
		
		KeyValue keyValue1 = new KeyValue(this.baseColor, STG.DARK_BLUE_G, Interpolator.EASE_OUT);
		KeyValue keyValue2 = new KeyValue(this.baseColor, STG.DARK_BLUE_G2, Interpolator.EASE_OUT);
		
		KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
		KeyFrame keyFrame2 = new KeyFrame(Duration.millis(5000), keyValue2);
		
		Timeline timeline = new Timeline(keyFrame1, keyFrame2);
		
		baseColor.addListener((observable, oldColor, newColor) -> {
			LinearGradient lg = new LinearGradient(0, 0, 10, 10, true, CycleMethod.NO_CYCLE, new Stop(2.0f, newColor));
			this.setBackground(new Background(new BackgroundFill(lg, null, null)));
		});
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
	}
	
	private void title () {
		left_LabelBox = new JFxHBox(5);
		left_LabelBox.setAlignment(Pos.CENTER_LEFT);
		left_LabelBox.setMinSize(100, 20);
		left_LabelBox.getChildren().add(lbl_paneTitle);
	}
	
	private void buttons () {
		btn_minimize = new JFxIconButton(new IconsStore(IconsStore.minimize_url, size), size);
		btn_minimize.setTransparent();
		
		btn_close = new JFxIconButton(new IconsStore(IconsStore.quit_url, size), size);
		btn_close.setTransparent();
		
		btn_default = new JFxIconButton(new IconsStore(IconsStore.defaultview_url, size), size);
		btn_default.setTransparent();
	}
	
	/*------------------------------------------------------------------*|
	|*							Private Attributes						*|
	|*------------------------------------------------------------------*/
	
	// tools
	private final Label lbl_paneTitle;
	private final double size;
	private JFxIconButton btn_minimize;
	private JFxIconButton btn_close;
	private JFxIconButton btn_default;
	private ColorAdjust brightness;
	ObjectProperty<Color> baseColor;
	
	// container
	private JFxHBox left_LabelBox;
	
	// window
	private double xOffset;
	private double yOffset;
	
}
