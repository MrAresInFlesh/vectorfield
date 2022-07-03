package gui.tests;

import gui.components.JFxVectorField;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import prog.tools.Particle;
import prog.tools.Pattern;

public class TestGui extends Application {
	
	final double WID = 1200, HEI = 600, SIZE_CELL = 100;
	double w, h;
	final int BALL_RADIUS = 1;
	
	@Override
    public void start(Stage primaryStage) throws Exception {
	
	    primaryStage.setTitle("P2 AirFlow");
		HBox hBox = new HBox();
		
		JFxVectorField vf = new JFxVectorField(WID, HEI, SIZE_CELL, BALL_RADIUS, Pattern.TypePattern.HORIZONTAL_CUT);
		hBox.getChildren().add(vf);
	    Scene scene = new Scene(hBox, WID, HEI);
	
	    w = WID / SIZE_CELL;
	    h = HEI / SIZE_CELL;
	
	    scene.setOnMouseMoved(vf.getVECTOR_FIELD()::setMouseMoved);
	    scene.setOnKeyReleased(vf.getVECTOR_FIELD()::setKeyReleased);
	
	    for(int i = 0; i < WID; i += 10) {
		    for(int j = 0; j < HEI; j += 10) {
			    new Particle(new Point2D(i, j), BALL_RADIUS, vf.getVECTOR_FIELD());
		    }
	    }
	
	    primaryStage.setScene(scene);
	    //primaryStage.setResizable(false);
	    primaryStage.show();
	
	    new AnimationTimer(){
		    private long previousTime = 0;
		    public void handle(long currentTime) {
			    if (previousTime == 0) {
				    previousTime = currentTime;
				    return;
			    }
			
			    double secondsElapsed = (currentTime - previousTime) / 1_000_000_000.0; // Convert nanoseconds to seconds
			
			    vf.getVECTOR_FIELD().update(secondsElapsed);
			    vf.getVECTOR_FIELD().draw();
			
			    previousTime = currentTime;
		    }
	    }.start();
	   
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
