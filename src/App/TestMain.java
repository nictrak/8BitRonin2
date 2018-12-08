package App;



import java.util.ArrayList;

import com.sun.glass.events.MouseEvent;

import Logic.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestMain extends Application{

	private boolean isEnd;
	
	
	public TestMain() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void start(Stage playStage) throws Exception {
		// TODO Auto-generated method stub
		int stageNumber = 1 ;
		//Menu Scene
		Pane menu = new Pane();
		GameImage goblinSouls = new GameImage(new Image(ClassLoader.getSystemResource("Images/goblin soul y.png").toString()));
		ImageView startButton = new GameImage(new Image(ClassLoader.getSystemResource("Images/start1.png").toString()));
		GameImage quitButton = new GameImage(new Image(ClassLoader.getSystemResource("Images/quit1.png").toString()));
		goblinSouls.updatePosition(new Vector2D(0,200));
		//startButton.updatePosition(new Vector2D(580,485));
		quitButton.updatePosition(new Vector2D(610,570));
		goblinSouls.setFitHeight(150);
		goblinSouls.setFitWidth(1350);
		startButton.setFitHeight(40);
		startButton.setFitWidth(200);
		quitButton.setFitHeight(40);
		quitButton.setFitWidth(140);
		menu.getChildren().add(goblinSouls);
		menu.getChildren().add(startButton);
		menu.getChildren().add(quitButton);
		Scene menuScene = new Scene(menu,1366,768);
        menuScene.setFill(Color.BLACK);
		//
		ArrayList<Plat> plats = new ArrayList<Plat>();
		ArrayList<FireBall> fireBalls = new ArrayList<FireBall>();
		new CastThread(null).setup();
		isEnd = false;
		//make pane
		Pane root = new Pane();
		//make floor
		Floor floor = new Floor();
		//make hero by classic constructor
		Hero hero = new Hero();
		//make health bar
		HealthBar healthBar = new HealthBar(hero);
		GameImage death = new GameImage(new Image(ClassLoader.getSystemResource("Images/you died 2.png").toString()));
		death.updatePosition(new Vector2D(0,200));
		MediaPlayer youDiePlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/you die.mp3").toString()));
		MediaPlayer killPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/goblin die.mp3").toString()));
		MediaPlayer swordPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/sword.mp3").toString()));
		MediaPlayer backSongs = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/soul of cinder.mp3").toString()));
		backSongs.setVolume(0.1);
		//set scene
		root.getChildren().add(floor.getGameImage());
		root.getChildren().add(hero.getGameImage());
		root.getChildren().add(hero.getSword().getGameImage());
		root.getChildren().add(hero.getHitBox());
		root.getChildren().add(healthBar.getHealth1());
		root.getChildren().add(healthBar.getHealth2());
		root.getChildren().add(healthBar.getHealth3());
		death.setVisible(false);
        Scene scene = new Scene(root,1366,768);
        scene.setFill(Color.AQUAMARINE);
        playStage.setTitle("TestScene");
        playStage.setScene(scene);
        //playStage.setScene(menuScene);
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
        WingedSpawner wingedSpawner= new WingedSpawner(4000,root,hero);
        WingedSpawner mageSpawner= new WingedSpawner(10000,root,hero);
        PlatformSpawner platSpawner = new PlatformSpawner(500,root,hero);
        WalkerSpawner walkerSpawner = new WalkerSpawner(4000,root,hero);
        //wing spawning
        wingedSpawner.start();
        mageSpawner.start();
        platSpawner.start();
        walkerSpawner.start();
        new AnimationTimer() {
            @Override public void handle(long currentNanoTime) {
            	if(stageNumber == 0) {
            	}if(stageNumber == 1) {
            		if(hero.getLife() > 0) {
            			backSongs.play();
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
                    	hero.getGameImage().setVisible(DamagedThread.isVisible());
                    	hero.update();
                    	hero.updateHitBox();
                    	hero.getSword().update();
                    	hero.gravityUpdate(floor,plats);
                    	healthBar.updateBar();
                    	//wing spawning
                    	if(wingedSpawner.isSpawn()) {
                    		WingedGoblin wg = new WingedGoblin(wingedSpawner.getSpawnPos(),hero);
                    		root.getChildren().add(wg.getGameImage());
                    		root.getChildren().add(wg.getHitBox());
                    		MonstersSet.getMonsters().add(wg);
                    		wingedSpawner.setSpawn(false);
                    	}
                    	//mage spawning
                    	if(mageSpawner.isSpawn()) {
                    		Mage wg = new Mage(mageSpawner.getSpawnPos(),hero);
                    		root.getChildren().add(wg.getGameImage());
                    		root.getChildren().add(wg.getHitBox());
                    		MonstersSet.getMonsters().add(wg);
                    		mageSpawner.setSpawn(false);
                    	}
                    	//walker spawning
                    	if(walkerSpawner.isSpawn()) {
                    		MeleeWalker wg = new MeleeWalker(walkerSpawner.getSpawnPos(),hero);
                    		root.getChildren().add(wg.getGameImage());
                    		root.getChildren().add(wg.getHitBox());
                    		MonstersSet.getMonsters().add(wg);
                    		walkerSpawner.setSpawn(false);
                    	}
                    	//plat spawning
                    	if(platSpawner.isSpawn()) {
                    		Plat wg = new Plat(platSpawner.getSpawnPos(),platSpawner.isRightSide());
                    		root.getChildren().add(wg.getGameImage());
                    		plats.add(wg);
                    		platSpawner.setSpawn(false);
                    	}
                    	//fireball spawning
                    	for(int i = 0 ; i < CastThread.getWaitedFireBalls().size(); i++) {
                    		FireBall fireBall = CastThread.getWaitedFireBalls().get(i);
                    		root.getChildren().add(fireBall.getGameImage());
                    		fireBalls.add(fireBall);
                    		CastThread.getWaitedFireBalls().remove(fireBall);
                    	}
                    	for(int i = 0 ; i < fireBalls.size();i++) {
                    		FireBall fireBall = fireBalls.get(i);
                    		if(fireBall != null) {
                    			fireBall.getPosition().add(fireBall.getVelocity());
                    			fireBall.updateImage();
                    			if(fireBall.isCollide(hero.getHitBox())) {
                    				hero.takeDamage();
                    				fireBalls.remove(fireBall);
                    				root.getChildren().remove(fireBall.getGameImage());
                    			}
                    		}
                    	}
                    	for(int i = 0 ; i < plats.size(); i++) {
                    		Plat platform = plats.get(i);
                    		if(platform != null) platform.update();
                    	}
                    	for(int i = 0 ; i < MonstersSet.getMonsters().size(); i++ ) {
                    		Monster monster = MonstersSet.getMonsters().get(i);
                    		if(monster != null) {
                    			monster.direct();
                        		monster.update();
                        		if(hero.getHitBox() != null) {
                        			if(monster.getHitBox() != null) {
                        				if(monster.isCollide(hero.getHitBox())) {
                                			hero.takeDamage();
                                		}
                        			}
                        		}
                        		if(hero.isAttack() && !AttackThread.isWaited()) {
                        			swordPlayer.play();
                        			swordPlayer.seek(Duration.ZERO);
                        			if(monster.isCollide(hero.getSword())) {
                        				//monster.getGameImage().setVisible(false);
                        				//monster.getHitBox().setVisible(false);
                        				killPlayer.play();
                        				killPlayer.seek(Duration.ZERO);
                        				monster.setLife(monster.getLife()-1);
                        			}
                        			if(monster.getLife() <= 0) {
                        				root.getChildren().remove(monster.getGameImage());
                        				root.getChildren().remove(monster.getHitBox());
                        				MonstersSet.getMonsters().remove(monster);
                        			}
                        		}
                    		}
                    	}
                    	}
                    	else{
                    		if(!isEnd) {
                    			backSongs.stop();
                    			root.getChildren().add(death);
                        		death.setVisible(true);
                        		youDiePlayer.play();
                        		isEnd = true;
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
