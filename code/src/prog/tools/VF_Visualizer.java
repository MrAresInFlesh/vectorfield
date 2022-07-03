package prog.tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;

public class VF_Visualizer extends Canvas {
	
	public VF_Visualizer (double width, double height, double sizeCell) {
		super(width, height);
		this.resolution = 1000;
		this.width = width;
		this.height = height;
		this.sizeCell = sizeCell;
		this.graphicsContext = this.getGraphicsContext2D();
		
		this.quadCurve = new QuadCurve(0, 0, this.width/2, this.height/2, this.width, this.height);
		this.pixelWriter = this.getGraphicsContext2D().getPixelWriter();
		for (int i = 0; i < resolution; i += sizeCell) {
			for (int j = 0; j < resolution; j += sizeCell) {
				pixelWriter.setColor(i, j, Color.RED);
			}
		}
		this.setOpacity(0.3);
	}
	
	public void update (double secondsElapsed, double dispersion) {
		this.draw(secondsElapsed, dispersion);
	}
	
	public void draw(double resolution, double dispersion) {
		for (double i = 0; i < resolution; i+= dispersion) {
			for (double j = 0; j < resolution; j+= dispersion) {
				graphicsContext.beginPath();
				graphicsContext.moveTo(resolution, dispersion);
				graphicsContext.quadraticCurveTo(this.width/2, this.width/2, width, this.height);
				graphicsContext.setStroke(Color.BLACK);
				graphicsContext.fill();
			}
		}
	}
	
	// tools
	private int resolution;
	private PixelWriter pixelWriter;
	QuadCurve quadCurve;
	private final double width;
	private final double height;
	private final double sizeCell;
	private final GraphicsContext graphicsContext;
	
}
