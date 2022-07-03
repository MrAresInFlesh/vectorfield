package prog.tools;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Tools {

    // Constrain a value between low and high
    public static double constrain(double n, double low, double high){
        return Math.max(Math.min(n, high), low);
    }

    public static Vector constrain(Vector v, double low, double high){
        Vector newVec = new Vector(v);
        newVec.vec = new Point2D(constrain(v.vec.getX(), low, high), constrain(v.vec.getY(), low, high));
        return newVec;
    }

    public static double dist(double x1, double y1, double x2, double y2){
        return Math.hypot(x2 - x1, y2 - y1);
    }

    public static double map(double n, double a, double b, double c, double d){
        return (n-a)/(b-a) * (d-c) + c;
    }

    public static final Color LAVENDER_BLUE = Color.web("#E3D7FF");
    public static final Color WHITE = Color.web("#F2F4F3");
    public static final Color LIGHT_BROWN = Color.web("#A9927D");
    public static final Color BLUE_PURPLE = Color.web("#AFA2FF");
    public static final Color TEA_GREEN = Color.web("#D5E2BC");
    public static final Color DARK_PINK = Color.web("#A6979C");
    public static final Color PINK = Color.web("#D3C0D2");
}
