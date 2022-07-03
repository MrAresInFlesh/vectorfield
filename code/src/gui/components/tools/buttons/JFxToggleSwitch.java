package gui.components.tools.buttons;

import gui.components.tools.style.STG;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class JFxToggleSwitch extends Parent {

    /*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	
	public JFxToggleSwitch() {
        this.swtOn = new SimpleBooleanProperty(false);
        this.translateTransition = new TranslateTransition(Duration.seconds(0.25));
        this.fillTransition = new FillTransition(Duration.seconds(0.25));
        this.animation = new ParallelTransition(translateTransition, fillTransition);
        
        geometry();
        control();
        appearance();
    }

	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	private void geometry() {
        Rectangle bg = new Rectangle(length, height);
        bg.setArcWidth(height);
        bg.setArcHeight(height);
        bg.setFill(Color.WHITE);
        bg.setStroke(Color.TRANSPARENT);

        double radius = height / 2;
        Circle trigger = new Circle(radius);
        trigger.setCenterX(radius);
        trigger.setCenterY(radius);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(Color.LIGHTBLUE);

        translateTransition.setNode(trigger);
        fillTransition.setShape(bg);
        getChildren().addAll(bg, trigger);
    }

    private void control() {
        swtOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState;
            translateTransition.setToX(isOn ? length - height : 0);
            fillTransition.setFromValue(isOn ? Color.WHITE : Color.LIGHTBLUE);
            fillTransition.setToValue(isOn ? Color.LIGHTBLUE : Color.WHITE);
            animation.play();
        });
        setOnMouseClicked(event -> swtOn.set(!swtOn.get()));
    }

    private void appearance() {

    }

    public BooleanProperty swtOnProperty() {
        return swtOn;
    }

    private void setStyle() {

    }

	/*------------------------------------------------------------------*|
	|*							Attributes Private						*|
	|*------------------------------------------------------------------*/

    // tools
    private final double length = STG.LENGTH;
    private final double height = length / 2;
    private final SimpleBooleanProperty swtOn;
    private final TranslateTransition translateTransition;
    private final FillTransition fillTransition;
    private final ParallelTransition animation;

}
