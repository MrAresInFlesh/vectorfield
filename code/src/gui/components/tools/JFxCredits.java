package gui.components.tools;

import gui.icons.IconsStore;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.util.Duration;

public class JFxCredits extends Pane {
	
	public JFxCredits () {
		super();
		IconsStore logo = new IconsStore(IconsStore.he_arc_logo, true);
		HBox box_logo = new HBox();
		box_logo.getChildren().add(logo);
		VBox mainLayout = new VBox();
		
		mainLayout.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), Insets.EMPTY)));
		
		WebView view = new WebView();
		if (IconsStore.credits_url.toExternalForm() != null) {
			String url = IconsStore.credits_url.toExternalForm();
			view.getEngine().load(url);
		}
		
		mainLayout.getChildren().addAll(view);
		
		setTranslateY(38);
		getChildren().add(mainLayout);
		this.setScaleX(0);
		this.setScaleY(0);
		this.setOpacity(0);
	}
	
	public static void reduce (Node node) {
		Duration duration = new Duration(500);
		ScaleTransition scaleTransition = new ScaleTransition(duration, node);
		scaleTransition.setToX(0.0);
		scaleTransition.setToY(0.0);
		scaleTransition.play();
		
		FadeTransition fadeTransition = new FadeTransition(duration, node);
		fadeTransition.setToValue(0);
		fadeTransition.play();
	}
	
	public static void show (Node node) {
		Duration duration = new Duration(500);
		ScaleTransition scaleTransition = new ScaleTransition(duration, node);
		scaleTransition.setToX(1);
		scaleTransition.setToY(1);
		scaleTransition.play();
		
		FadeTransition fadeTransition = new FadeTransition(duration, node);
		fadeTransition.setToValue(1);
		fadeTransition.play();
	}
	
}
