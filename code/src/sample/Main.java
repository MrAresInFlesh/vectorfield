package sample;

import gui.JFxGui;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import prog.tools.Particle;
import prog.tools.Pattern;
import prog.tools.VectorField;

    /*------------------------------------------------------------------*|
    |*							    PROGRAM    							*|
    |*------------------------------------------------------------------*/

public class Main extends Application {
    final double WID = 1200, HEI = 600, SIZE_CELL = 50;
    double w, h;
    final int BALL_RADIUS = 2;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("P2 AirFlow");
        JFxGui gui = new JFxGui();
        HBox hbox = new HBox();
        //hbox.getVector().add(gui);

        VectorField vf = new VectorField(WID, HEI, SIZE_CELL, Pattern.TypePattern.HORIZONTAL_CUT);
        hbox.getChildren().add(vf);

        Scene scene = new Scene(hbox, WID, HEI);

        w = WID / SIZE_CELL;
        h = HEI / SIZE_CELL;
        
        vf.setShowCells(true);
        
        scene.setOnMouseMoved(vf::setMouseMoved);
        scene.setOnKeyReleased(vf::setKeyReleased);

        //new Particle(new Point2D(625, 200), 20, 1,vf);
        for(int i = 0; i < WID; i += 40){
            for(int j = 0; j < HEI; j += 40) {
                new Particle(new Point2D(i, j), BALL_RADIUS, vf);
            }
        }

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        new AnimationTimer(){
            private long previousTime = 0;
            public void handle(long currentTime){
                if (previousTime == 0) {
                    previousTime = currentTime;
                    return;
                }

                double secondsElapsed = (currentTime - previousTime) / 1_000_000_000.0; // Convert nanoseconds to seconds

                vf.draw();
                vf.update(secondsElapsed);

                previousTime = currentTime;
            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
