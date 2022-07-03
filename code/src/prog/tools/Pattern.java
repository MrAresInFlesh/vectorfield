package prog.tools;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public class Pattern {

    public enum TypePattern {
        ROTATION,
        HORIZONTAL_CUT,
        VERTICAL_CUT,
        ALTERNATE,
        ALL_ZEROS
    }

    public Pattern(TypePattern tp, ArrayList<ArrayList<Cell>> cells, double cellsPerRow, double cellsPerCol, double sizeCell) {
        switch(tp){
            case ROTATION:
                rotation(cells, cellsPerRow, cellsPerCol, sizeCell);
                break;
            case HORIZONTAL_CUT:
                horizontal_cut(cells, cellsPerRow, cellsPerCol, sizeCell);
                break;
            case VERTICAL_CUT:
                vertical_cut(cells, cellsPerRow, cellsPerCol, sizeCell);
                break;
            case ALTERNATE:
                alternate(cells, cellsPerRow, cellsPerCol, sizeCell);
                break;
            case ALL_ZEROS:
                all_zeros(cells, cellsPerRow, cellsPerCol, sizeCell);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tp);
        }
    }

    private void all_zeros(ArrayList<ArrayList<Cell>> cells, double cellsPerRow, double cellsPerCol, double sizeCell){
        for(int i = 0; i < cellsPerRow; i++) {
            cells.add(new ArrayList<>());
            for(int j = 0; j < cellsPerCol; j++) {
                Point2D p = new Point2D(sizeCell * i, sizeCell * j);
                Point2D v = new Point2D(0, 0);
                Cell c = new Cell(p, sizeCell, sizeCell, v);

                c.setOnMouseClicked(c::clicked);
                c.getVector().setOnMouseClicked(c::clicked);

                cells.get(i).add(c);
            }
        }
    }

    private void rotation(ArrayList<ArrayList<Cell>> cells, double cellsPerRow, double cellsPerCol, double sizeCell){
        for(int i = 0; i < cellsPerRow; i++) {
            cells.add(new ArrayList<>());
            for(int j = 0; j < cellsPerCol; j++) {
                Point2D p = new Point2D(sizeCell * i, sizeCell * j);
                Point2D v = new Point2D(-j + cellsPerCol/2, i - cellsPerRow/2);
                Cell c = new Cell(p, sizeCell, sizeCell, v);
                c.setVector(c.getVector().normalise());

                c.setOnMouseClicked(c::clicked);
                c.getVector().setOnMouseClicked(c::clicked);

                cells.get(i).add(c);
            }
        }
    }

    private void horizontal_cut(ArrayList<ArrayList<Cell>> cells, double cellsPerRow, double cellsPerCol, double sizeCell){
        for(int i = 0; i < cellsPerRow; i++) {
            cells.add(new ArrayList<>());
            for(int j = 0; j < cellsPerCol; j++) {
                Point2D p = new Point2D(sizeCell * i, sizeCell * j);
                Point2D v = new Point2D((j >= cellsPerCol/2 ? 1 : -1), 0);
                Cell c = new Cell(p, sizeCell, sizeCell, v);

                c.setOnMouseClicked(c::clicked);
                c.getVector().setOnMouseClicked(c::clicked);

                cells.get(i).add(c);
            }
        }
    }

    private void vertical_cut(ArrayList<ArrayList<Cell>> cells, double cellsPerRow, double cellsPerCol, double sizeCell){
        for(int i = 0; i < cellsPerRow; i++) {
            cells.add(new ArrayList<>());
            for(int j = 0; j < cellsPerCol; j++) {
                Point2D p = new Point2D(sizeCell * i, sizeCell * j);
                Point2D v = new Point2D(0, (i >= cellsPerRow/2 ? 1 : -1));
                Cell c = new Cell(p, sizeCell, sizeCell, v);

                c.setOnMouseClicked(c::clicked);
                c.getVector().setOnMouseClicked(c::clicked);

                cells.get(i).add(c);
            }
        }
    }

    private void alternate(ArrayList<ArrayList<Cell>> cells, double cellsPerRow, double cellsPerCol, double sizeCell){
        for(int i = 0; i < cellsPerRow; i++) {
            cells.add(new ArrayList<>());
            for(int j = 0; j < cellsPerCol; j++) {
                Point2D p = new Point2D(sizeCell * i, sizeCell * j);
                Point2D v = new Point2D(-2 * (j % 2) + 1, 0);
                Cell c = new Cell(p, sizeCell, sizeCell, v);

                c.setOnMouseClicked(c::clicked);
                c.getVector().setOnMouseClicked(c::clicked);

                cells.get(i).add(c);
            }
        }
    }
}
