package App;

import java.util.ArrayList;

import Logic.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestMain extends Application{

	private ArrayList<Platform> platforms;
	
	public TestMain() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void start(Stage playStage) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Platform> platforms = new ArrayList<Platform>();
		//make pane
		Pane root = new Pane();
		//make floor
		Floor floor = new Floor();
		//make hero by classic constructor
		Hero hero = new Hero();
		Platform p0 = new Platform(new Vector2D(500,500),true);
		platforms.add(p0);
		//set scene
		root.getChildren().add(p0.getGameImage());
		root.getChildren().add(hero.getGameImage());
		root.getChildren().add(floor.getGameImage());
        Scene scene = new Scene(root,1366,768);
        playStage.setTitle("TestScene");
        playStage.setScene(scene);
        //set key
        KeyHandle keyHandle = new KeyHandle();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
        	if(key.getCode() == KeyCode.D) {
        		keyHandle.setMoveRightPressed(true);
            }
            if(key.getCode() == KeyCode.A) {
            	keyHandle.setMoveLeftPressed(true);
            }
            if(key.getCode() == KeyCode.W) {
            	keyHandle.setJumpPressed(true);
            }
            if(key.getCode() == KeyCode.S) {
            	keyHandle.setMoveDownPressed(true);
            }
       
        });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
        	if(key.getCode()==KeyCode.D) {
        		keyHandle.setMoveRightPressed(false);
            }
        	if(key.getCode()==KeyCode.A) {
        		keyHandle.setMoveLeftPressed(false);
            }
        	if(key.getCode()==KeyCode.W) {
        		keyHandle.setJumpPressed(false);
        		keyHandle.setAlreadyJump(false);
            }
        	if(key.getCode()==KeyCode.W) {
        		keyHandle.setMoveDownPressed(false);
        		keyHandle.setAlreadyMoveDown(false);
            }
        	
       
        });
		//show
        playStage.show();
        new AnimationTimer() {
            @Override public void handle(long currentNanoTime) {
            	if(keyHandle.isMoveRightPressed() ) {
            		hero.getPosition().add(new Vector2D(10,0));
            	}
            	if(keyHandle.isMoveLeftPressed() ) {
            		hero.getPosition().add(new Vector2D(-10,0));
            	}
            	if(keyHandle.isJumpPressed() && !keyHandle.isAlreadyJump()) {
            		hero.jump();
            		keyHandle.setAlreadyJump(true);
            	}
            	if(keyHandle.isMoveDownPressed() && !keyHandle.isAlreadyMoveDown()) {
            		hero.down();
            		keyHandle.setAlreadyMoveDown(true);
            	}
            	hero.update();
            	hero.gravityUpdate(floor,platforms);
            	for(Platform platform : platforms) {
            		platform.update();
            	}
            }
        }.start();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
