package prog.tools.visualizer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import prog.tools.Tools;
import prog.tools.Vector;
import prog.tools.VectorField;

public class VF_Visualizer extends Canvas {
	
	public VF_Visualizer (double width, double height, VectorField vf, GraphicsContext gc) {
		super(width, height);
		this.vectorField = vf;
		this.matrixPixel = new MatrixPixel(gc, RESOLUTION);
		for (int i = 0; i < width; i += RESOLUTION) {
			for (int j = 0; j < height; j += RESOLUTION) {
				this.matrixPixel.setPixel(i, j, 0);
			}
		}
	}
	
	public void update() {
		for (int i = 0; i < this.getWidth(); i += RESOLUTION) {
			for (int j = 0; j < this.getHeight(); j += RESOLUTION) {
				Vector v = vectorField.applyNeighbors(i, j);

				double maxMag = Math.sqrt(2);
				int value = (int)Tools.map(v.mag(), 0, maxMag, 240, 0);
				this.matrixPixel.setPixel(i, j, value);
			}
		}
	}
	
	// tools
	private VectorField vectorField;
	private MatrixPixel matrixPixel;
	private final int RESOLUTION = 5;

}
