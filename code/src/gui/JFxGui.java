package gui;

import gui.components.JFxSettings;
import gui.components.JFxVectorField;
import gui.components.tools.JFxCredits;
import gui.components.tools.JFxMainWindow;
import gui.components.tools.style.STG;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import prog.tools.Particle;
import prog.tools.Pattern;
import prog.tools.VectorField;

public class JFxGui extends Application {

	/*------------------------------------------------------------------*|
	|*							  VARIABLES    							*|
	|*------------------------------------------------------------------*/
	
	private double xOffset;
	private double yOffset;
	
	// field
	public static double FIELD_WIDTH = 1600;
	public static double FIELD_HEIGHT = 800;
	public static double FIELD_CELL_SIZE = 80;
	int BALL_RADIUS = 2;
	double DISPERSION = 10;
	Pattern.TypePattern PATTERN = Pattern.TypePattern.ALTERNATE;
	
	private static final double ZOOM_FACTOR = 0.01;
	
	JFxVectorField vectorField;
	JFxSettings settings;
	JFxMainWindow window;
	Scene scene;
	ATField AT_field;
	Stage stage;
	JFxCredits credits;
	boolean isReduce = true;

/*-------------------------------|*------------------------------*|--------------------------------*|
|--------------------------------|*			    MAIN             *|--------------------------------*|
|--------------------------------|*------------------------------*|--------------------------------*/

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start (Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		initializing(FIELD_WIDTH, FIELD_HEIGHT,  FIELD_CELL_SIZE, BALL_RADIUS, PATTERN);
		events(stage);

	/*------------------------------------------------------------------*|
	|*							    TIMER   		    				*|
	|*------------------------------------------------------------------*/
		
		AT_field = new ATField() {
			@Override
			public void handle (long now) {
				updateFrameTime(now);
				updateFrameRate();
				if (isActive()) {
					updateField(now, vectorField);
				}
				control(now);
			}
			
			@Override
			public void tick (long relativeNow) {
			
			}
		};
		AT_field.start();
		
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setResizable(true);
		primaryStage.show();
		
	}

	/*------------------------------------------------------------------*|
	|*							  COMPUTATION		    				*|
	|*------------------------------------------------------------------*/
	
	private void particles () {
		BALL_RADIUS = vectorField.getPARTICLE_RADIUS();
		JFxVectorField.addParticles(FIELD_WIDTH, FIELD_HEIGHT, BALL_RADIUS, vectorField.getVECTOR_FIELD(), DISPERSION);
	}
	private void initializing (double width, double height, double cells_size, int radius, Pattern.TypePattern pattern) {
		vectorField = new JFxVectorField(width, height, cells_size, radius, pattern);
		settings = new JFxSettings();
		settings.setPrefWidth(STG.SETTINGS_PANE_WIDTH);
		window = new JFxMainWindow(settings, vectorField);
		credits = new JFxCredits();
		window.getChildren().add(credits);
		
		scene = new Scene(window, width*1.15, height*1.25);
		scene.getRoot().setEffect(new DropShadow(10, Color.rgb(100, 100, 100)));
		scene.setFill(Color.TRANSPARENT);
	}
	
	/*-------------------------------|*------------------------------*|--------------------------------*|
	|--------------------------------|*			    CONTROL          *|--------------------------------*|
	|--------------------------------|*------------------------------*|--------------------------------*/
	
	private void events (Stage primaryStage) {
		// field control
		this.scene.setOnMouseMoved(this.vectorField.getVECTOR_FIELD()::setMouseMoved);
		this.scene.setOnKeyReleased(this.vectorField.getVECTOR_FIELD()::setKeyReleased);
		this.settings.getFieldController().getPanel().getSpinner().setOnMousePressed(this::setCellsSize);
		this.settings.getFieldController().getPanel().getToggleSwitch().setOnMousePressed(this::showCells);
		this.settings.getFieldController().getPanel().getStdButtons().getBtn_start().setOnMousePressed(this::createField);
		this.settings.getFieldController().getPanel().getStdButtons().getBtn_pause().setOnMousePressed(this::moreParticles);
		this.settings.getFieldController().getPanel().getStdButtons().getBtn_reset().setOnMousePressed(this::resetField);
		this.vectorField.setOnScroll(this::zoom);
		this.vectorField.setOnMouseDragged(event -> {
			if (vectorField.isPressed() && event.isShiftDown()) {
				vectorField.setTranslateX(event.getScreenX() - xOffset);
				vectorField.setTranslateY(event.getScreenY() - yOffset);
				event.consume();
			}
		});

		// particle control
		this.settings.getParticleController().getPanel().getSld_inputSize().valueProperty().addListener((observable, oldValue, newValue) -> {
			settings.getParticleController().getPanel().getLbl_size().getLbl_text().setText("size:" + String.format("%d", newValue.intValue()));
			BALL_RADIUS = newValue.intValue();
			vectorField.setPARTICLE_RADIUS(newValue.intValue());
		});
		this.settings.getParticleController().getPanel().getSld_inputOpacity().valueProperty().addListener((observable, oldValue, newValue) -> {
			for (Particle particle : vectorField.getVECTOR_FIELD().getBalls()) {
				particle.setOpacity(newValue.doubleValue());
			}
			this.settings.getParticleController().getPanel().getLbl_opacity().getLbl_text().setText("opacity: " + String.format("%.1f", newValue.doubleValue()));
		});
		this.settings.getParticleController().getPanel().getSld_speedScale().valueProperty().addListener((observable, oldValue, newValue) -> {
			settings.getParticleController().getPanel().getLbl_speedScale().getLbl_text().setText("speed:" + String.format("%.1f", newValue.doubleValue()));
			vectorField.getVECTOR_FIELD().setSPEED_SCALE(newValue.doubleValue());
		});
		this.vectorField.getVECTOR_FIELD().setOnMouseDragged(this::addParticles);
		this.settings.getParticleController().getPanel().getSpinner().valueProperty().addListener((observable, oldValue, newValue) -> {
			DISPERSION = Double.parseDouble(newValue.toString());
		});
		this.settings.getParticleController().getPanel().getToggleShadow().setOnMousePressed(this::setParticleShadow);
		this.settings.getParticleController().getPanel().getBtn_resetParticles().setOnMousePressed(this::resetParticles);
		
		// vector control
		
		// window control
		this.window.setOnMousePressed(event -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
			event.consume();
		});
		this.window.setOnMouseDragged(event -> {
			if (window.getWindowBar().isPressed())
			{
				primaryStage.setX(event.getScreenX() - xOffset);
				primaryStage.setY(event.getScreenY() - yOffset);
				event.consume();
			}
		});
		this.settings.getPaneBar().getBtnInfos().setOnMousePressed(this::credits);
		
	}
	
	private void resetParticles (MouseEvent event) {
		for (Particle particle : vectorField.getVECTOR_FIELD().getBalls()) {
			
			vectorField.getVECTOR_FIELD().getChildren().remove(particle);
			
		}
		vectorField.getVECTOR_FIELD().getBalls().clear();
		
	}
	
	private void setParticleShadow (MouseEvent event) {
		for (Particle particle : vectorField.getVECTOR_FIELD().getBalls()) {
			if (!particle.hasShadow()) {
				particle.setHasShadow(true);
			}
			else if (particle.hasShadow()) {
				particle.setHasShadow(false);
				particle.setEffect(null);
			}
		}
	}
	
	/*---------------------|*------------------------------*|---------------------*/
	
		private void zoom (ScrollEvent scrollEvent) {
			if (scrollEvent.getDeltaY() > 0) {
				zoomIn(scrollEvent, vectorField.getVECTOR_FIELD());
			}
			else if (scrollEvent.getDeltaY() < 0) {
				zoomOut(scrollEvent, vectorField.getVECTOR_FIELD());
			}
		}
		private void zoomIn (ScrollEvent scrollEvent, VectorField vector_field) {
			Scale newScale = new Scale();
			newScale.setX(vector_field.getScaleX() + ZOOM_FACTOR);
			newScale.setY(vector_field.getScaleY() + ZOOM_FACTOR);
			newScale.setPivotX(vector_field.getScaleX() + scrollEvent.getX()/2);
			newScale.setPivotY(vector_field.getScaleY() + scrollEvent.getY()/2);
			vector_field.getTransforms().add(newScale);
		}
		private void zoomOut (ScrollEvent scrollEvent, VectorField vector_field) {
			Scale newScale = new Scale();
			newScale.setX(vector_field.getScaleX() - ZOOM_FACTOR);
			newScale.setY(vector_field.getScaleY() - ZOOM_FACTOR);
			newScale.setPivotX(vector_field.getScaleX() + scrollEvent.getX()/2);
			newScale.setPivotY(vector_field.getScaleY() + scrollEvent.getY()/2);
			vector_field.getTransforms().add(newScale);
		}

		/*------------------------------*|
		|*			   FIELD			*/
	
		/*------------BUTTONS----------*/
		private void createField (MouseEvent event) {
			if(vectorField.getVECTOR_FIELD() == null) {
				vectorField.setVECTOR_FIELD(new VectorField(FIELD_WIDTH, FIELD_HEIGHT, FIELD_CELL_SIZE, PATTERN));
				vectorField.getChildren().add(vectorField.getVECTOR_FIELD());
			}
			AT_field.start();
			events(stage);
		}
		private void moreParticles (MouseEvent event) {
		if (vectorField.getVECTOR_FIELD() != null) {
			particles();
		}
	}
		private void resetField (MouseEvent event) {
			vectorField.getChildren().remove(vectorField.getVECTOR_FIELD());
			AT_field.isActive = false;
			vectorField.setVECTOR_FIELD(null);
		}

		private void showCells (MouseEvent event) {
		if (vectorField.getVECTOR_FIELD() != null) {
			vectorField.getVECTOR_FIELD().setShowCells(!vectorField.getVECTOR_FIELD().isShowCells());
		}
	}
	
		/*------------SPINNERS----------*/
		private void setCellsSize (MouseEvent event) {
			var value = (Integer) settings.getFieldController().getPanel().getSpinner().getValue();
				if (vectorField.getVECTOR_FIELD() != null) {
					vectorField.getVECTOR_FIELD().setCellsSize(value);
				}
			FIELD_CELL_SIZE = value;
		}
	
	/*---------------------|*------------------------------*|---------------------*/
		/*------------------------------*|
		|*			  PARTICLE			*/
		private void addParticles (MouseEvent event) {
			if (vectorField.isPressed()) {
				new Particle(new Point2D(event.getX(), event.getY()), BALL_RADIUS, vectorField.getVECTOR_FIELD());
			}
		}
	
	/*---------------------|*------------------------------*|---------------------*/
		/*------------------------------*|
		|*			  VECTOR			*/
		private void setDirection (MouseEvent event) {
		
		}
		private void setMagnitude (MouseEvent event) {
		
		}
		private void invertVector (MouseEvent event) {
		
		}
	
	/*---------------------|*------------------------------*|---------------------*/
		private void credits (MouseEvent event) {
		if (isReduce) {
			isReduce = false;
			JFxCredits.show(credits);
		}
		else {
			isReduce = true;
			JFxCredits.reduce(credits);
		}
	}
	
}
