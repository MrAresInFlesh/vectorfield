package gui.components.tools.buttons;

import gui.icons.IconsStore;
import gui.components.tools.style.STG;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class JFxIconButton extends Button {

    /*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
    public JFxIconButton (IconsStore graphic) {
	    super();
	    this.btn_size = STG.BTN_SIZE20;
	    this.btn_label = new Label("");
	    this.btn_image = graphic;
	    geometry();
	    control();
	    appearance();
    }

    public JFxIconButton (IconsStore graphic, double size) {
        super();
        this.btn_size = size;
        this.btn_label = new Label("");
        this.btn_image = graphic;
        geometry();
        control();
        appearance();
    }
	
	
	public JFxIconButton (Label label, IconsStore graphic, double size) {
		super();
		this.btn_label = label;
		this.btn_image = graphic;
		this.btn_size = size;
		geometry();
		control();
		appearance();
	}
	
	/*------------------------------------------------------------------*|
	|*						  Public Methods		    				*|
	|*------------------------------------------------------------------*/
	
	public void setTransparent() {
		setStyle("-fx-background-color: transparent;");
	}
	
	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry() {
		btn_image.setSmooth(true);
		btn_image.setCache(true);
		setGraphic(btn_image);
		btn_image.setFitHeight(btn_size);
    	btn_image.setPreserveRatio(true);
		darker = new ColorAdjust();
		darker.setBrightness(-0.05);
    }

    private void control() {
		ColorAdjust hueShift = new ColorAdjust();
		hueShift.setHue(.1);
		setOnMouseEntered(event -> btn_image.setEffect(darker));
		setOnMouseExited(event -> btn_image.setEffect(null));
		setOnMousePressed(event -> btn_image.setEffect(hueShift));
		setOnMouseReleased(event -> btn_image.setEffect(darker));
    }

    private void appearance() {
        setTransparent();
	    setBackground(new Background(new BackgroundFill(Color.web("#3d3f47"), new CornerRadii(10), Insets.EMPTY)));
    }

	/*------------------------------------------------------------------*|
	|*							Attributes Private						*|
	|*------------------------------------------------------------------*/

    // tools
    private final double btn_size;
	private final Label btn_label;
	private final ImageView btn_image;
    private ColorAdjust darker;
	
}
