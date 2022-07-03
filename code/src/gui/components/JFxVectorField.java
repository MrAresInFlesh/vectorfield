package gui.components;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import prog.tools.Particle;
import prog.tools.Pattern;
import prog.tools.VectorField;

public class JFxVectorField extends Pane {

	/*------------------------------------------------------------------*|
	|*							Constructors							*|
	|*------------------------------------------------------------------*/
	public JFxVectorField (double width, double height, double sizeCell, int particleRadius, Pattern.TypePattern pattern) {
		super();
		this.WIDTH = width;
		this.HEIGHT = height;
		this.SIZE_CELL = sizeCell;
		this.PARTICLE_RADIUS = particleRadius;
		this.VECTOR_FIELD = new VectorField(width, height, sizeCell, pattern);
		geometry();
		control();
		appearance();
	}
	
	/*------------------------------------------------------------------*|
	|*							Public Methods		    				*|
	|*------------------------------------------------------------------*/
	
	/*------------------------------*|
	|*			  Setters			*|
	|*------------------------------*/
	public void setVECTOR_FIELD (VectorField vector_field) {
		this.VECTOR_FIELD = vector_field;
	}
	public void setPARTICLE_RADIUS (int PARTICLE_RADIUS) {
		this.PARTICLE_RADIUS = PARTICLE_RADIUS;
	}

	/*------------------------------*|
	|*			  Getters			*|
	|*------------------------------*/
	public VectorField getVECTOR_FIELD () {
		return this.VECTOR_FIELD;
	}
	public double getWIDTH () {
		return this.WIDTH;
	}
	public double getHEIGHT () {
		return this.HEIGHT;
	}
	public double getSIZE_CELL () {
		return this.SIZE_CELL;
	}
	public int getPARTICLE_RADIUS () {
		return this.PARTICLE_RADIUS;
	}
	
	/*------------------------------*|
	|*			  Static 			*|
	|*------------------------------*/
	public static void addParticles (double width, double height, int radius, VectorField vectorField, double dispersion) {
		for(int i = 0; i < width; i += dispersion) {
			for(int j = 0; j < height; j += dispersion) {
				new Particle(new Point2D(i, j), radius, vectorField);
			}
		}
	}
	
	/*------------------------------------------------------------------*|
	|*							Private Methods		    				*|
	|*------------------------------------------------------------------*/
	
	/*------------------------------*|
	|*			  Structure			*|
	|*------------------------------*/
	private void geometry () {
		getChildren().addAll(this.VECTOR_FIELD);
	}
	private void control () {
		setOnMouseMoved(this.VECTOR_FIELD::setMouseMoved);
		setOnKeyReleased(this.VECTOR_FIELD::setKeyReleased);
	}
	private void appearance () {

	}
	
	/*------------------------------------------------------------------*|
	|*							Private Attributes						*|
	|*------------------------------------------------------------------*/
	
	// field
	private VectorField VECTOR_FIELD;
	private double WIDTH;
	private double HEIGHT;
	private double SIZE_CELL;
	private int PARTICLE_RADIUS;

}


