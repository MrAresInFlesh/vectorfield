package tests;

import javafx.geometry.Point2D;
import org.junit.Test;
import prog.tools.Vector;

public class TestVector {

    /*------------------------------------------------------------------*|
    |*							Methods Public							*|
    |-------------------------------------------------------------------*/

        @Test
        public void testVec() {
            double x = 10;
            double y = 20;
            Point2D point2D1 = new Point2D(x, y);
            Point2D point2D2 = new Point2D(x*2, y*2);
            Vector vector = new Vector(point2D1, point2D2);
            System.out.println(vector);
        }

        @Test
        public void testOp() {
            double x = 10;
            double y = 20;
            Point2D point2D1 = new Point2D(x, y);
            Point2D point2D2 = new Point2D(x*2, y*2);
            Vector vector1 = new Vector(point2D1, point2D2);
            Vector vector2 = new Vector(point2D1, point2D2);

            vector1.add(vector2);
            System.out.println("Add: vector1 + vector2 : " + vector1 + "    |   " + vector2);

            vector1.sub(vector2);
            System.out.println("Sub: vector1 - vector2 : " + vector1 + "    |   " + vector2);

            double factor = 2;
            vector1.mul(factor);
            System.out.println("Mul: vector1 * " + factor + ": " + vector1);

            double divider = 4;
            vector1.div(divider);
            System.out.println("Div: vector1 / " + divider + ": " + vector1);

            double dotProd = vector1.dotProduct(vector2);
            System.out.println("Dot prod.  vector1 and vector2 : " + dotProd);

            double magnitude = vector1.mag();
            System.out.println("Magnitude  vector1 : " + magnitude);

            double angle = vector1.angle(vector2);
            System.out.println("Angle between 2 vectors -->  vector1 : " + angle);
        }
}


