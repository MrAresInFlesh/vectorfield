package prog.tools;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Trail {

    public Trail(VectorField vf){
        this.vf = vf;
        this.trail = new HashMap<>();
    }

    public void add(double x, double y, double radius, Color color) {
        Circle c = new Circle(x, y, radius);
        c.setFill(color);
        this.trail.put(c, 1.0);
        this.vf.getChildren().add(c);
    }

    public void draw() {
        for(Iterator<Map.Entry<Circle, Double>> iterator = this.trail.entrySet().iterator(); iterator.hasNext();){
            Map.Entry<Circle, Double> entry = iterator.next();
            double life = entry.getValue();
            Circle c = entry.getKey();
            if(life <= 0){
                this.vf.getChildren().remove(c);
                iterator.remove();
            } else {
                c.setOpacity(life);
                entry.setValue(life - DECAY);
            }
        }
    }

    public double getDECAY(){
        return this.DECAY;
    }

    public void setDECAY(double val){
        this.DECAY = val;
    }

    private final VectorField vf;
    private final HashMap<Circle, Double> trail;
    private double DECAY = 0.02;
}
