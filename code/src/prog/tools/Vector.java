package prog.tools;

import javafx.geometry.Point2D;

public class Vector extends Arrow {

    public Vector(Point2D pos, Point2D vec) {
        super(pos, 10, 5);
        this.vec = new Point2D(vec.getX(), vec.getY());
        this.end = Point2D.ZERO;
    }

    public Vector(Vector otherVec){
        this(new Point2D(otherVec.start.getX(), otherVec.start.getY()),
             new Point2D(otherVec.vec.getX(), otherVec.vec.getY()));
    }

    public Vector add(Vector otherVec){
        Point2D p = new Point2D(this.vec.getX() + otherVec.vec.getX(), this.vec.getY() + otherVec.vec.getY());
        return new Vector(this.start, p);
    }

    public Vector sub(Vector otherVec){
        Point2D p = new Point2D(this.vec.getX() - otherVec.vec.getX(), this.vec.getY() - otherVec.vec.getY());
        return new Vector(this.start, p);
    }

    public Vector mul(double scale){
        Point2D p = new Point2D(this.vec.getX() * scale, this.vec.getY() * scale);
        return new Vector(this.start, p);
    }

    public Vector div(double scale){
        Point2D p = new Point2D(this.vec.getX() / scale, this.vec.getY() / scale);
        return new Vector(this.start, p);
    }

    public Vector normalise(){
        double len = this.mag();
        if(len != 0) {
            return this.mul(1 / this.mag());
        }
        return this;
    }

    public void setEnd(double widthScale, double heightScale){
        this.end = new Point2D(this.vec.getX() * widthScale, this.vec.getY() * heightScale);
    }

    public void setOffset(double newEndX, double newEndY, double widthScale, double heightScale){
        this.end = new Point2D(newEndX, newEndY);
        this.vec = new Point2D(this.end.getX() / widthScale, this.end.getY()/ heightScale);
    }

    public double dotProduct(Vector otherVec){
        return this.vec.getX() * otherVec.vec.getX() + this.vec.getY() * otherVec.vec.getY();
    }

    public double mag() {
        return Math.hypot(this.vec.getX(), this.vec.getY());
    }

    public double angle(Vector otherVec){
        return Math.acos((this.dotProduct(otherVec) / (this.mag() * otherVec.mag())));
    }


    public void update(double newX, double newY){
        this.vec = new Point2D(newX, newY);
    }

    @Override
    public String toString(){
        return "Vector " + this.vec + " at position " + this.start;
    }

    protected Point2D vec;
}
