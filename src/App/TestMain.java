package App;

import Logic.Floor;
import Logic.Hero;
import Logic.Vector2D;
import Logic.Monster;
import Logic.WingedGoblin;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestMain extends Application{

	public TestMain() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void start(Stage playStage) throws Exception {
		// TODO Auto-generated method stub
		//make pane
		Pane root = new Pane();
		//make floor
		Floor floor = new Floor();
		//make hero by classic constructor
		Hero hero = new Hero();
		//make test monster
		WingedGoblin monster = new WingedGoblin(hero);
		//set scene
		root.getChildren().add(floor.getGameImage());
		root.getChildren().add(hero.getGameImage());
		root.getChildren().add(monster.getGameImage());
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
            	hero.update();
            	monster.direct();
            	monster.update();
            	hero.gravityUpdate(floor);
            	
            }
        }.start();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		launch(args);
	}
}
