package prog.tools;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class VectorField extends Pane {
	
	public VectorField(double width, double height, double sizeCell, Pattern.TypePattern pattern) {
        super();
        this.cellsPerRow = width / sizeCell;
        this.cellsPerCol = height / sizeCell;
        this.sizeCell = sizeCell;
        this.setPrefSize(width, height);

        this.cells = new ArrayList<>();
        this.particles = new ArrayList<>();

        new Pattern(pattern, this.cells, this.cellsPerRow, this.cellsPerCol, this.sizeCell);

        // Add neighbors to cells
        for(int i = 0; i < this.cellsPerRow; i++) {
            for (int j = 0; j < this.cellsPerCol; j++) {
                Cell c = this.cells.get(i).get(j);
                this.getChildren().addAll(c, c.getVector());
                for(int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        int indX = i + k, indY = j + l;
                        if (indX >= 0 && indX < this.cellsPerRow && indY >= 0 && indY < this.cellsPerCol) {
                            if(c != this.cells.get(indX).get(indY))
                                c.addNeighbor(this.cells.get(indX).get(indY));
                        }
                    }
                }
            }
        }
    }

    public void draw(){
        for(ArrayList<Cell> ac : this.cells){
            for(Cell c : ac){
                c.draw();
            }
        }

        for(Particle p : this.particles){
            p.draw();
        }
    }

    public void update(double deltaTime) {
        for (Particle p : new ArrayList<>(particles)) {
            Vector res = this.applyNeighbors(p.getCenterX(), p.getCenterY());
            p.update(deltaTime);
            p.applyForce(res.mul(SPEED_SCALE));
        }
    }

    public Vector applyNeighbors(double posX, double posY) {
        Vector sumV = new Vector(Point2D.ZERO, Point2D.ZERO);

        for(int i = -1; i <= 1; i += 2){
            for(int j = -1; j <= 1; j += 2) {

                int neighborX = (int) (posX + (i * this.sizeCell / 2));
                int neighborY = (int) (posY + (j * this.sizeCell / 2));

                int x = (int) (neighborX / this.sizeCell);
                int y = (int) (neighborY / this.sizeCell);

                if (x >= 0 && x < this.cellsPerRow &&
                        y >= 0 && y < this.cellsPerCol) {
                    Cell c = this.getAt(x, y);
                    Vector v = c.getVector();

                    double d = Tools.dist(posX, posY, v.start.getX(), v.start.getY());
                    if(d != 0) {
                        sumV = sumV.add(v.div(Math.sqrt(d)));
                    }
                }
            }
        }

        return sumV;
    }

    public void setShowCells(boolean b) {
        this.showCells = b;
        for (ArrayList<Cell> ac : this.cells) {
            for (Cell cell : ac) {
                cell.setVisible(b);
                cell.getVector().setVisible(b);
            }
        }
    }

    public void addParticle(Particle b){
        b.mouseTransparentProperty().setValue(true);
        this.particles.add(b);
        this.getChildren().add(b);
    }

    public Cell getFromPos(double x, double y){
        return this.getAt((int)(x / this.sizeCell), (int)(y / this.sizeCell));
    }

    public Cell getAt(int i, int j){

        return this.cells.get(i).get(j);
    }

    public void setMouseMoved(MouseEvent mouseEvent) {
        for(ArrayList<Cell> ac : cells) {
            for(Cell c : ac) {
                c.moved(mouseEvent);
            }
        }
    }

    public void setKeyReleased(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.CONTROL) {
            for(ArrayList<Cell> ac : cells) {
                for(Cell c : ac)
                    c.select(false);
            }
        }
    }
	
	public ArrayList<ArrayList<Cell>> getCells () {
		return this.cells;
	}
	
	public ArrayList<Particle> getBalls () {
		return this.particles;
	}
	
	public boolean isShowCells () {
		return this.showCells;
	}
	
	public void setCellsSize (double sizeCell) {
		this.sizeCell = sizeCell;
	}
	
	public double getSizeCell () {
		return this.sizeCell;
	}
	
	public void setSPEED_SCALE (double speed_scale) {
		this.SPEED_SCALE = speed_scale;
	}
	
	public double getSPEED_SCALE () {
		return this.SPEED_SCALE;
	}
	
	public void setBalls (ArrayList<Particle> particles) {
		this.particles = particles;
	}
	
	// tools
    protected ArrayList<ArrayList<Cell>> cells;
    protected ArrayList<Particle> particles;
    protected boolean showCells = true;
    private double sizeCell;
    private double SPEED_SCALE = 10;
	private double cellsPerRow;
	private double cellsPerCol;

}
