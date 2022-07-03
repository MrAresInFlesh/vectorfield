package prog.tools;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Arrow extends Path {

    /*------------------------------------------------------------------*|
    |*							Constructors             				*|
    |*------------------------------------------------------------------*/

    public Arrow(Point2D p1, double len, double arrowHeadSize) {
        this.start = new Point2D(p1.getX(), p1.getY());
        this.len = len;
        this.arrowHeadSize = arrowHeadSize;
        strokeProperty().bind(fillProperty());
    }

    public void draw(){

        setFill(this.color);
        setStrokeWidth(1);
        
        // arrowhead
        // tools :
        double angle = Math.atan2((this.end.getY()), (this.end.getX())) - Math.PI * 0.5;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        double x1, y1;
        double x2, y2;
        // point 1
        x1 = (-1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + this.start.getX() + this.end.getX();
        y1 = (-1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + this.start.getY() + this.end.getY();

        // point2
        x2 = (1.0  / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + this.start.getX() + this.end.getX();
        y2 = (1.0  / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + this.start.getY() + this.end.getY();

        getElements().clear();

        getElements().add(new MoveTo(this.start.getX(), this.start.getY()));
        getElements().add(new LineTo(this.start.getX() + this.end.getX(), this.start.getY() + this.end.getY()));

        getElements().add(new LineTo(x1, y1));
        getElements().add(new LineTo(x2, y2));
        getElements().add(new LineTo(this.start.getX() + this.end.getX(), this.start.getY() + this.end.getY()));
        
    }

    /*------------------------------------------------------------------*|
    |*							Attributes Private      				*|
    |*------------------------------------------------------------------*/

    // inputs :
    protected Point2D start, end;
    protected double len;
    private final double arrowHeadSize;
    private final Color color = Tools.BLUE_PURPLE;

}
