package prog.tools.visualizer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MatrixPixel {

    public MatrixPixel(GraphicsContext gc, int sizeRect){
        this.gc = gc;
        this.size_rect = sizeRect;
    }

    public void setPixel(int x, int y, int value){
        this.gc.setFill(Color.hsb(value, 1, 1));
        this.gc.fillRect(x, y, size_rect, size_rect);
    }


    private GraphicsContext gc;
    private int size_rect;
}
