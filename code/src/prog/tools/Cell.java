package prog.tools;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class Cell extends Rectangle {

    public Cell(Point2D pos, double width, double height, Point2D vec) {
        super(pos.getX(), pos.getY(), width, height);

        this.neighborsCell = new ArrayList<>();

        this.pos = new Point2D(pos.getX(), pos.getY());
        Point2D vecPos = new Point2D(pos.getX() + this.getWidth()/2, pos.getY() + this.getHeight()/2);

        this.vector = new Vector(vecPos, vec);

        double offsetX = Tools.map(vec.getX(), -1, 1, -this.getWidth()/2, this.getWidth()/2);
        double offsetY = Tools.map(vec.getY(), -1, 1, -this.getHeight()/2, this.getHeight()/2);
        this.vector.setOffset(offsetX, offsetY, this.getWidth()/2, this.getHeight()/2);
    }

    public Cell(Cell c){
        this(new Point2D(c.pos.getX(), c.pos.getY()), c.getWidth(), c.getHeight(), new Point2D(c.vector.vec.getX(), c.vector.vec.getY()));
    }

    public Cell copy(){
        return new Cell(this);
    }

    public void draw(){
        this.setFill(this.fillColor);
        this.setStroke(this.strokeColor);
        this.vector.draw();
    }

    // Select the current cell
    public void select(boolean s) {
        if (s) {
            this.fillColor = Tools.DARK_PINK;
            this.strokeColor = Tools.DARK_PINK;
        } else {
            this.fillColor = Tools.LAVENDER_BLUE;
            this.strokeColor = Tools.BLUE_PURPLE;
        }

        this.selected = s;
    }

    public void moved(MouseEvent mouseEvent) {
        if(this.selected){
	        double sceneYOffset = 43;
	        double _x = mouseEvent.getX() - this.pos.getX() - this.getWidth()/2,
                   _y = (mouseEvent.getY()- sceneYOffset) - this.pos.getY() - this.getHeight()/2;
            this.vector.setOffset(Tools.constrain(_x, -this.getWidth()/2, this.getWidth()/2),
                    Tools.constrain(_y, -this.getHeight()/2, this.getHeight()/2),
                    this.getWidth()/2, this.getHeight()/2);
            updateNeighbors();
        }
    }

    private ArrayList<Cell> copyNeighbors(){
        ArrayList<Cell> copyN = new ArrayList<>();
        for (Cell cell : this.neighborsCell) {
            copyN.add(cell.copy());
        }
        return copyN;
    }

    public void updateNeighbors(){
        for (Cell cell : this.neighborsCell) {
            Vector updatedVec = cell.updateVector(cell.copyNeighbors());
            cell.vector.update(updatedVec.vec.getX(), updatedVec.vec.getY());
            cell.vector.setEnd(this.getWidth()/2, this.getHeight()/2);
        }
    }

    public Vector updateVector(ArrayList<Cell> copyN){
        Vector sumV = new Vector(this.vector.start, Point2D.ZERO);
        int cpt = 0;
        for(Cell c : copyN){
            cpt++;
            sumV = sumV.add(c.vector);
        }
        return sumV.div(cpt);
    }

    public void addNeighbor(Cell c){
        this.neighborsCell.add(c);
    }

    public void clicked(MouseEvent mouseEvent){
        if(!mouseEvent.isControlDown())
            return;

        select(!this.selected);
    }

    // Return the child (vector) of the cell
    public Vector getVector(){
        return vector;
    }

    public void setVector(Vector v) {
        this.vector = new Vector(v);
        this.vector.setEnd(this.getWidth()/2, this.getHeight()/2);
    }

    // inputs :
    private Vector vector;
    private final Point2D pos;
    private boolean selected = false;
    private Color fillColor = Tools.LAVENDER_BLUE;
    private Color strokeColor = Tools.BLUE_PURPLE;
    private final ArrayList<Cell> neighborsCell;

}
