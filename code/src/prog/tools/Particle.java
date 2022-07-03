package prog.tools;

import javafx.geometry.Point2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Particle extends Circle {

    public Particle(Point2D pos, double radius, VectorField vf) {
        super();
        this.position = new Vector(Point2D.ZERO, pos);
        this.velocity = new Vector(Point2D.ZERO, Point2D.ZERO);
        this.trail = new Trail(vf);
        this.hasShadow = false;

        this.setCenterX(this.position.vec.getX());
        this.setCenterY(this.position.vec.getY());
        this.setRadius(radius);

        this.setFill(Tools.WHITE);
        this.setStroke(Color.TRANSPARENT);

        this.vf = vf;
        this.vf.addParticle(this);

    }

    public void draw() {
        this.setCenterX(this.position.vec.getX());
        this.setCenterY(this.position.vec.getY());

        double maxMag = Math.hypot(this.MAX_SPEED, this.MAX_SPEED);
        Color color = Color.hsb(Tools.map(this.velocity.mag(), 0, maxMag, 240, 0), 1, 1);
        this.setFill(color);

        this.trail.draw();
    }

    public void applyForce(Vector v){
        this.velocity = new Vector(this.velocity.start, v.vec);
        this.velocity = Tools.constrain(this.velocity, -MAX_SPEED, MAX_SPEED);
    }
	
	public void setHasShadow (boolean hasShadow) {
		this.hasShadow = hasShadow;
	}
	
	public boolean hasShadow () {
		return hasShadow;
	}

    public void update(double deltaTime){
        this.trail.add(this.position.vec.getX(), this.position.vec.getY(), this.getRadius(), (Color) this.getFill());

        this.position = this.position.add(this.velocity.mul(deltaTime));

        if(this.position.vec.getX() < this.getRadius()) {
            this.position.vec = new Point2D(vf.getWidth() - this.getRadius(), this.position.vec.getY());
        } else if(this.position.vec.getX() >= vf.getWidth() - this.getRadius()) {
            this.position.vec = new Point2D(this.getRadius(), this.position.vec.getY());
        }

        if(this.position.vec.getY() < this.getRadius()) {
            this.position.vec = new Point2D(this.position.vec.getX(), vf.getHeight() - this.getRadius());
        } else if(this.position.vec.getY() > vf.getHeight() - this.getRadius()) {
            this.position.vec = new Point2D(this.position.vec.getX(), this.getRadius());
        }
	
	    if (hasShadow) {
	        DropShadow dropShadow = new DropShadow(0.5, 1.0, 1.0, Color.GRAY);
	        this.setEffect(dropShadow);
		}
    }

    private Vector position;
    private Vector velocity;
    private final VectorField vf;
    private final Trail trail;
    private final double MAX_SPEED = 30;
    private boolean hasShadow;

}
