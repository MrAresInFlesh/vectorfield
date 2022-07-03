package tests;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import prog.tools.Arrow;
import prog.tools.Vector;

public class TestArrow extends Application {

    @Test
    public void testArrow() {
        double x = 20;
        double y = 20;
        double u = 20;
        double v = 20;
        Point2D p1 = new Point2D(x, y);

        Arrow arrow = new Arrow(p1,  10, 5);
        System.out.println(arrow);
    }

    @Override
    public void start(Stage primaryStage) {

        testArrow();

        double x = 10, y = 10, u = 0, v = 0;

        Point2D point2D1 = new Point2D(x, y);
        Point2D point2D2 = new Point2D(u, v);

        Vector vector = new Vector(point2D1, point2D2);

        Group root = new Group();
        //root.getVector().add(arrow);
        root.getChildren().add(vector);

        Scene scene = new Scene(root, 450, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
