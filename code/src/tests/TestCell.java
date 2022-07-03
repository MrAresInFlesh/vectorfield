package tests;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import prog.tools.Cell;

public class TestCell extends Application {
    final double w = 1200, h = 600;
    @Test
    public void testCell(){
        double x = w / 2,
               y = h / 2;

        Point2D point2D1 = new Point2D(x - 100, y - 100);
        Cell c = new Cell(point2D1, 200, 200, point2D1);

        System.out.println(c);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("P2 AirFlow");

        testCell();

        double x = w / 2,
               y = h / 2;

        Point2D point2D1 = new Point2D(x - 100, y - 100);

        Group root = new Group();
        Scene scene = new Scene(root, w, h);

        Cell c = new Cell(point2D1, 200, 200, point2D1);

        root.getChildren().addAll(c, c.getVector());

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
