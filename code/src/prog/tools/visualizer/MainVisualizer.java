package prog.tools.visualizer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import prog.tools.Pattern;
import prog.tools.VectorField;

    /*------------------------------------------------------------------*|
    |*							    PROGRAM    							*|
    |*------------------------------------------------------------------*/

public class MainVisualizer extends Application {
    final double WID = 1200, HEI = 600, SIZE_CELL = 50;
    double w, h;
    final int BALL_RADIUS = 2;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("P2 AirFlow");

        VectorField vf = new VectorField(WID/2, HEI, SIZE_CELL, Pattern.TypePattern.HORIZONTAL_CUT);
        Canvas canvas = new Canvas(WID/2, HEI);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        HBox root = new HBox(canvas, vf);

        VF_Visualizer vfv = new VF_Visualizer(WID/2, HEI, vf, gc);
        Scene scene = new Scene(root, WID, HEI);

        scene.setOnMouseMoved(vf::setMouseMoved);
        scene.setOnKeyReleased(vf::setKeyReleased);

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
                vfv.update();

                previousTime = currentTime;
            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
