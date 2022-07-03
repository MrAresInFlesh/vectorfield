package gui;

import gui.components.JFxVectorField;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class ATField extends AnimationTimer {
	
	/*--------------------|*------------------------------*|---------------------*|
	|---------------------|*			  CORE            *|---------------------*|
	|---------------------|*------------------------------*|---------------------*/
	@Override
	public abstract void handle (long now);
	
	@Override
	public void start() {
		super.start();
		isActive = true;
		restartScheduled = true;
	}
	
	@Override
	public void stop() {
		super.stop();
		pauseStart = 0;
		isPaused = false;
		isActive = false;
		pauseScheduled = false;
		playScheduled = false;
		animationDuration.set(0);
	}
	
	/*------------------------------*|
	|*			  Abstract			*|
	|*------------------------------*/
	
	public abstract void tick(long relativeNow);

	/*------------------------------------------------------------------*|
	|*						  Protected Methods		    				*|
	|*------------------------------------------------------------------*/
	
	protected void updateFrameRate() {
		FRAME_RATE.set((int) Math.round(getFrameRateHertz()));
	}
	
	protected void updateFrameTime(long currentTimeNano) {
		deltaTimeNano = currentTimeNano - lastFrameTime;
		lastFrameTime = currentTimeNano;
	}
	
	protected void control (long now) {
		if (pauseScheduled) {
			pauseStart = now;
			isPaused = true;
			pauseScheduled = false;
		}
		
		if (playScheduled) {
			animationStart += (now - pauseStart);
			isPaused = false;
			playScheduled = false;
		}
		
		if (restartScheduled) { 
			isPaused = false;
			animationStart = now;
			restartScheduled = false;
		}
		
		if (!isPaused) {
			long animDuration = now - animationStart;
			animationDuration.set(animDuration / 1e9);
			tick(animDuration);
		}
		
		if (previousTime == 0) {
			previousTime = now;
		}
	}
	
	protected void updateField(long now, JFxVectorField vectorField) {
		if (this.previousTime == 0) {
			previousTime = now;
			return;
		}
		
		double secondsElapsed = (now - previousTime) / 1_000_000_000.0; // Convert nanoseconds to seconds
		
		if(vectorField.getVECTOR_FIELD() != null) {
			vectorField.getVECTOR_FIELD().update(secondsElapsed);
			vectorField.getVECTOR_FIELD().draw();
		}
		
		previousTime = now;
		
	}
	
	/*------------------------------------------------------------------*|
	|*							Public Methods		    				*|
	|*------------------------------------------------------------------*/
	
	/*------------------------------*|
	|*			  Getters			*|
	|*------------------------------*/
	
	public int getFrameRate() {
		return FRAME_RATE.get();
	}
	
	public long getDeltaTimeNano() {
		return deltaTimeNano;
	}
	
	public double getFrameRateHertz() {
		double FRAME_RATE = 1d / deltaTimeNano;
		return FRAME_RATE * 1e9;
	}
	
	/*------------------------------*|
	|*			 Properties			*|
	|*------------------------------*/
	
	public IntegerProperty frameRateProperty() {
		return FRAME_RATE;
	}
	
	public DoubleProperty animationDurationProperty() {
		return animationDuration;
	}
	
	public boolean isPaused() {
		return isPaused;
	}
	
	public boolean isActive() {
		return isActive;
	}

	/*------------------------------*|
	|*			 Control			*|
	|*------------------------------*/
	
	public void pause() {
		if (!isPaused) {
			pauseScheduled = true;
		}
	}
	
	public void play() {
		if (isPaused) {
			playScheduled = true;
		}
	}
	
	/*------------------------------------------------------------------*|
	|*							 Attributes     						*|
	|*------------------------------------------------------------------*/
	
	// timer
	long previousTime = 0;
	long pauseStart;
	long animationStart;
	DoubleProperty animationDuration = new SimpleDoubleProperty(0L);
	
	boolean isPaused;
	boolean isActive;
	boolean pauseScheduled;
	boolean playScheduled;
	boolean restartScheduled;
	

	// tools
	long lastFrameTime = -1L;
	long deltaTimeNano;
	IntegerProperty FRAME_RATE = new SimpleIntegerProperty(0);
	
}
