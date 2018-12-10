package App;



import java.util.ArrayList;

import Logic.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application{

	private static boolean isEnd;
	private int stageNumber ;
	private Scene scene;
	private Scene menuScene;
	private Floor floor;
	private Hero hero;
	private HealthBar healthBar;
	private GameImage death;
	private ScoreBoard scoreBoard;
	private MediaPlayer youDiePlayer ;
	private MediaPlayer killPlayer;
	private MediaPlayer swordPlayer ;
	private MediaPlayer backSongs;
	private MediaPlayer menuSongs;
	private KeyHandle keyHandle;
	private SpawnerThread wingedSpawner;
    private SpawnerThread mageSpawner;
    private SpawnerThread walkerSpawner;
    private SpawnerThread platSpawner ;
    private Label hScore;
	
	public Main() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void start(Stage playStage) throws Exception {
		// TODO Auto-generated method stub
		stageNumber = 0 ;
		//Menu Scene
		Pane menu = new Pane();
		GameImage goblinSouls = new GameImage(new Image(ClassLoader.getSystemResource("Images/goblin soul y.png").toString()));
		GameImage startButton = new GameImage(new Image(ClassLoader.getSystemResource("Images/start1.png").toString()));
		GameImage quitButton = new GameImage(new Image(ClassLoader.getSystemResource("Images/quit1.png").toString()));
		GameImage backGround = new GameImage(new Image(ClassLoader.getSystemResource("Backgrounds/bg.png").toString()));
		GameImage tutorial = new GameImage(new Image(ClassLoader.getSystemResource("Images/wadj.png").toString()));
		backGround.updatePosition(new Vector2D(0,0));
		goblinSouls.updatePosition(new Vector2D(0,200));
		quitButton.updatePosition(new Vector2D(610,570));
		goblinSouls.setFitHeight(150);
		goblinSouls.setFitWidth(1350);
		startButton.setFitHeight(40);
		startButton.setFitWidth(200);
		quitButton.setFitHeight(40);
		quitButton.setFitWidth(140);
		startButton.setLayoutX(580);
		startButton.setLayoutY(485);
		tutorial.setLayoutX(950);
		tutorial.setLayoutY(380);
		tutorial.setScaleX(0.25);
		tutorial.setScaleY(0.25);
		menu.getChildren().add(backGround);
		menu.getChildren().add(goblinSouls);
		menu.getChildren().add(startButton);
		menu.getChildren().add(quitButton);
		menu.getChildren().add(tutorial);
		quitButton.setOnMouseEntered(e -> quitButton.setImage(new Image(ClassLoader.getSystemResource("Images/quit2.png").toString())));
		quitButton.setOnMouseExited(e -> quitButton.setImage(new Image(ClassLoader.getSystemResource("Images/quit1.png").toString())));
		startButton.setOnMouseEntered(e -> startButton.setImage(new Image(ClassLoader.getSystemResource("Images/start2.png").toString())));
		startButton.setOnMouseExited(e -> startButton.setImage(new Image(ClassLoader.getSystemResource("Images/start1.png").toString())));
		//
		ArrayList<Plat> plats = new ArrayList<Plat>();
		ArrayList<FireBall> fireBalls = new ArrayList<FireBall>();
		new CastThread(null).setup();
		isEnd = false;
		//media setup
		youDiePlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/you die.mp3").toString()));
		killPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/goblin die.mp3").toString()));
		swordPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/sword.mp3").toString()));
		backSongs = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/soul of cinder.mp3").toString()));
		menuSongs = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/menu.mp3").toString()));
		backSongs.setVolume(0.1);
		//make pane
		Pane root = new Pane();
		floor = new Floor();
		hero = new Hero();
		healthBar = new HealthBar(hero);
		death = new GameImage(new Image(ClassLoader.getSystemResource("Images/you died 2.png").toString()));
		death.updatePosition(new Vector2D(0,200));
		scoreBoard = new ScoreBoard(0);
		scoreBoard.setLayoutX(450);
		scoreBoard.setLayoutY(20);
		scoreBoard.setScaleX(3);
		scoreBoard.setScaleY(3);
		hScore = new Label();
		hScore.setText("HighScore : " + Integer.toString(scoreBoard.getHighScore()));
		hScore.setTextFill(Color.WHITE);
		hScore.setLayoutX(1160);
		hScore.setLayoutY(20);
		hScore.setScaleX(3);
		hScore.setScaleY(3);
		menu.getChildren().add(hScore);
		//set scene
		root.getChildren().add(floor.getGameImage());
		root.getChildren().add(hero.getGameImage());
		root.getChildren().add(hero.getSword().getGameImage());
		root.getChildren().add(hero.getHitBox());
		root.getChildren().add(healthBar.getHealth1());
		root.getChildren().add(healthBar.getHealth2());
		root.getChildren().add(healthBar.getHealth3());
		root.getChildren().add(scoreBoard);
		death.setVisible(false);
		floor.getGameImage().setVisible(false);
		menuScene = new Scene(menu,1366,768);
        menuScene.setFill(Color.BLACK);
        root.setBackground(new Background(new  BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader().getResource("Backgrounds/space.jpg").toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(root,1366,768);
        //scene.setFill(Color.AQUAMARINE);
        playStage.setTitle("TestScene");
        //playStage.setScene(scene);
        playStage.setScene(menuScene);
        //set key
        keyHandle = new KeyHandle();
        //make monsters set
        new MonstersSet();
        //attack thread
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
        	if(key.getCode() == KeyCode.D) {
        		hero.setRight(true);
        		keyHandle.setMoveRightPressed(true);
        		keyHandle.setAlreadyNotMove(false);
            }
            if(key.getCode() == KeyCode.A) {
            	hero.setRight(false);
            	keyHandle.setMoveLeftPressed(true);
            	keyHandle.setAlreadyNotMove(false);
            }
            if(key.getCode() == KeyCode.W) {
            	keyHandle.setJumpPressed(true);
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
        	if(key.getCode() == KeyCode.J) {
            	keyHandle.setAttackPressed(false);
            	keyHandle.setAlreadyAttack(false);
            }
       
        });
		//show
        playStage.show();
        startButton.setOnMouseClicked(e -> {
			this.stageNumber = 1;
			playStage.setScene(scene);
			wingedSpawner = new SpawnerThread(3000,hero,100,600);
	        mageSpawner = new SpawnerThread(6000,hero,100,600);
	        walkerSpawner = new SpawnerThread(2000,hero,588,588);
	        platSpawner = new SpawnerThread(1000,hero,100,600);
	        Thread wingThread = new Thread(wingedSpawner);
	        Thread mageThread = new Thread(mageSpawner);
	        Thread walkerThread = new Thread(walkerSpawner);
	        Thread platThread = new Thread(platSpawner);
			wingThread.start();
			mageThread.start();
			walkerThread.start();
			platThread.start();
			swordPlayer.play();
			swordPlayer.seek(Duration.ZERO);
		});
        quitButton.setOnMouseClicked(e-> Platform.exit());
        new AnimationTimer() {
            @Override public void handle(long currentNanoTime) {
            	if(stageNumber == 0) {
            		menuSongs.play();
            	}if(stageNumber == 1) {
            		if(hero.getLife() > 0) {
            			menuSongs.stop();
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
                    		keyHandle.setAlreadyMoveLeft(true);
                    	}
                    	if(keyHandle.isMoveRightPressed() && !keyHandle.isAlreadyMoveRight()) {
                    		keyHandle.setAlreadyMoveRight(true);
                    	}
                    	if(keyHandle.isJumpPressed() && !keyHandle.isAlreadyJump()) {
                    		hero.jump();
                    		keyHandle.setAlreadyJump(true);
                    	}
                    	if(keyHandle.isAttackPressed() && !keyHandle.isAlreadyAttack()) {
                    		hero.setAttack();
                    		keyHandle.setAlreadyAttack(true);
                    	}
                    	hero.updateImageStatus();
                    	hero.getGameImage().setVisible(DamagedThread.isVisible());
                    	hero.moveLimit();
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
                    		Plat wg = new Plat(platSpawner.getSpawnPos(),platSpawner.isRight());
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
                    			if(fireBall.isCollide(hero.getSword()) && hero.isAttack() && !AttackThread.isWaited()) {
                    				root.getChildren().remove(fireBall.getGameImage());
                    				fireBalls.remove(fireBall);
                    			}
                    		}
                    	}
                    	if(hero.isAttack() && !AttackThread.isWaited()) {
                    		swordPlayer.play();
                			swordPlayer.seek(Duration.ZERO);
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
                                			hero.setImmune(true);
                                		}
                        			}
                        		}
                        		if(hero.isAttack() && !AttackThread.isWaited()) {
                        			if(monster.isCollide(hero.getSword())) {
                        				//monster.getGameImage().setVisible(false);
                        				//monster.getHitBox().setVisible(false);
                        				killPlayer.play();
                                		killPlayer.seek(Duration.ZERO);
                        				monster.setLife(monster.getLife()-1);
                        				hero.minijump();
                        			}
                        			if(monster.getLife() <= 0) {
                        				scoreBoard.addScore(monster.getScore());
                        				root.getChildren().remove(monster.getGameImage());
                        				root.getChildren().remove(monster.getHitBox());
                        				MonstersSet.getMonsters().remove(monster);
                        			}
                        		}
                    		}
                    	}
                    	scoreBoard.update();
                    	}
                    	else{
                    		if(!isEnd) {
                    			scoreBoard.writeHighScore(scoreBoard.getScore());
                    			backSongs.stop();
                        		death.setVisible(true);
                        		youDiePlayer.play();
                        		isEnd = true;
                        		root.getChildren().add(death);
                    		}else {
                    			try {
									Thread.sleep(10000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                    			//root.getChildren().remove(0, root.getChildren().size());
                    			MonstersSet.getMonsters().clear();
                    			plats.clear();
                    			fireBalls.clear();
                    			CastThread.getWaitedFireBalls().clear();
                        		stageNumber = 0;
                    			playStage.setScene(menuScene);
                    			isEnd = false;
                    			//setup
                    			root.getChildren().clear();
                    			floor = new Floor();
                    			hero = new Hero();
                    			healthBar = new HealthBar(hero);
                    			death = new GameImage(new Image(ClassLoader.getSystemResource("Images/you died 2.png").toString()));
                    			death.updatePosition(new Vector2D(0,200));
                    			int high = scoreBoard.getHighScore();
                    			scoreBoard = new ScoreBoard(high);
                    			scoreBoard.setLayoutX(450);
                    			scoreBoard.setLayoutY(20);
                    			scoreBoard.setScaleX(3);
                    			scoreBoard.setScaleY(3);
                    			//set scene
                    			root.getChildren().add(floor.getGameImage());
                    			root.getChildren().add(hero.getGameImage());
                    			root.getChildren().add(hero.getSword().getGameImage());
                    			root.getChildren().add(hero.getHitBox());
                    			root.getChildren().add(healthBar.getHealth1());
                    			root.getChildren().add(healthBar.getHealth2());
                    			root.getChildren().add(healthBar.getHealth3());
                    			root.getChildren().add(scoreBoard);
                    			death.setVisible(false);
                    			//set media
                    			youDiePlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/you die.mp3").toString()));
                    			killPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/goblin die.mp3").toString()));
                    			swordPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/sword.mp3").toString()));
                    			backSongs = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/soul of cinder.mp3").toString()));
                    			menuSongs = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sounds/menu.mp3").toString()));
                    			backSongs.setVolume(0.1);
                    			keyHandle = new KeyHandle();
                    			menu.getChildren().remove(hScore);
                    			hScore.setText("HighScore : " + Integer.toString(scoreBoard.getHighScore()));
                    			hScore.setTextFill(Color.WHITE);
                    			hScore.setLayoutX(1160);
                    			hScore.setLayoutY(20);
                    			hScore.setScaleX(3);
                    			hScore.setScaleY(3);
                    			menu.getChildren().add(hScore);
                    			floor.getGameImage().setVisible(false);
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
	public static boolean isEnd() {
		return isEnd;
	}
	public static void setEnd(boolean isEnd) {
		Main.isEnd = isEnd;
	}
	public int getStageNumber() {
		return stageNumber;
	}
	public void setStageNumber(int stageNumber) {
		this.stageNumber = stageNumber;
	}
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public Scene getMenuScene() {
		return menuScene;
	}
	public void setMenuScene(Scene menuScene) {
		this.menuScene = menuScene;
	}
	
}
