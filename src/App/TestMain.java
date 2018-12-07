package App;



import Logic.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
		//set scene
		root.getChildren().add(floor.getGameImage());
		root.getChildren().add(hero.getGameImage());
		root.getChildren().add(hero.getSword().getGameImage());
        Scene scene = new Scene(root,1366,768);
        scene.setFill(Color.AQUAMARINE);
        playStage.setTitle("TestScene");
        playStage.setScene(scene);
        //set key
        KeyHandle keyHandle = new KeyHandle();
        //make monsters set
        new MonstersSet();
        //attack thread
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
        	if(key.getCode() == KeyCode.D) {
        		keyHandle.setMoveRightPressed(true);
        		keyHandle.setAlreadyNotMove(false);
            }
            if(key.getCode() == KeyCode.A) {
            	keyHandle.setMoveLeftPressed(true);
            	keyHandle.setAlreadyNotMove(false);
            }
            if(key.getCode() == KeyCode.W) {
            	keyHandle.setJumpPressed(true);
            	keyHandle.setAlreadyNotMove(false);
            }
            if(key.getCode() == KeyCode.S) {
            	keyHandle.setMoveDownPressed(true);
            	keyHandle.setAlreadyNotMove(false);
            }
            if(key.getCode() == KeyCode.J) {
            	keyHandle.setAttackPressed(true);
            	keyHandle.setAlreadyNotMove(false);
            }
        });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
        	if(key.getCode()==KeyCode.D) {
        		keyHandle.setMoveRightPressed(false);
        		keyHandle.setAlreadyMoveRight(false);
            }
        	if(key.getCode()==KeyCode.A) {
        		keyHandle.setMoveLeftPressed(false);
        		keyHandle.setAlreadyMoveLeft(false);
            }
        	if(key.getCode()==KeyCode.W) {
        		keyHandle.setJumpPressed(false);
        		keyHandle.setAlreadyJump(false);
            }
        	if(key.getCode()==KeyCode.W) {
        		keyHandle.setMoveDownPressed(false);
        		keyHandle.setAlreadyMoveDown(false);
            }
        	if(key.getCode() == KeyCode.J) {
            	keyHandle.setAttackPressed(false);
            	keyHandle.setAlreadyAttack(false);
            }
       
        });
		//show
        playStage.show();
        WingedSpawner wingedSpawner= new WingedSpawner(1000,root,hero);
        PlatformSpawner platSpawner = new PlatformSpawner(1000,root,hero);
        WalkerSpawner walkerSpawner = new WalkerSpawner(1000,root,hero);
        wingedSpawner.start();
        platSpawner.start();
        walkerSpawner.start();
        new AnimationTimer() {
            @Override public void handle(long currentNanoTime) {
            	if(keyHandle.isMoveRightPressed() ) {
            		hero.moveRight();
            	}
            	if(keyHandle.isMoveLeftPressed() ) {
            		hero.moveLeft();
            	}
            	if(!keyHandle.isMoveLeftPressed() && !keyHandle.isMoveRightPressed() && !keyHandle.isAlreadyNotMove()) {
            		hero.notMove();
            		keyHandle.setAlreadyNotMove(true);
            	}
            	if(keyHandle.isMoveLeftPressed() && !keyHandle.isAlreadyMoveLeft()) {
            		hero.setLeft();
            		keyHandle.setAlreadyMoveLeft(true);
            	}
            	if(keyHandle.isMoveRightPressed() && !keyHandle.isAlreadyMoveRight()) {
            		hero.setRight();
            		keyHandle.setAlreadyMoveRight(true);
            	}
            	if(keyHandle.isJumpPressed() && !keyHandle.isAlreadyJump()) {
            		hero.jump();
            		keyHandle.setAlreadyJump(true);
            	}
            	if(keyHandle.isMoveDownPressed() && !keyHandle.isAlreadyMoveDown()) {
            		hero.down();
            		keyHandle.setAlreadyMoveDown(true);
            	}
            	if(keyHandle.isAttackPressed() && !keyHandle.isAlreadyAttack()) {
            		hero.setAttack();
            		keyHandle.setAlreadyAttack(true);
            	}
            	hero.update();
            	hero.getSword().update();
            	hero.gravityUpdate(floor,platSpawner.getPlats());
            	for(int i = 0 ; i < platSpawner.getPlats().size(); i++) {
            		Plat platform = platSpawner.getPlats().get(i);
            		if(!platform.equals(null)) platform.update();
            	}
            	for(int i = 0 ; i < MonstersSet.getMonsters().size(); i++ ) {
            		Monster monster = MonstersSet.getMonsters().get(i);
            		if(monster != null) {
            			monster.direct();
                		monster.update();
                		if(hero.isAttack() && !AttackThread.isWaited()) {
                			if(monster.isCollide(hero.getSword())) {
                				MonstersSet.getMonsters().remove(monster);
                				root.getChildren().remove(monster.getGameImage());
                			}
                		}
            		}
            	}
            }
        }.start();
        
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
