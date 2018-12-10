package Logic;

import java.util.ArrayList;

import App.GameImage;
import javafx.scene.image.Image;

public class Hero extends LifeForm {
	
	//Attributes 
	private FeetStatus feetStatus;
	private boolean isMove;
	private boolean isAttack;
	private Sword sword;
	private boolean isImmune;
	private GameImage hitBox;
	private int Score;
	private ImageStatus imageStatus;
	private ImageStatus nowStatus;
	private int jumpLimit;
	private int jumpCount;
	//Constructor
	///basic hero
	public Hero() {
		super(new Vector2D(653,510),new Vector2D(0,0),new Vector2D(120,154),new GameImage(new Image(ClassLoader.getSystemResource("Images/stand right.png").toString())),3);
		this.getGameImage().updatePosition(this.getPosition());
		this.feetStatus = FeetStatus.FLOOR;
		this.isRight = true;
		this.isMove = false;
		this.isAttack = false;
		this.sword = new Sword(this);
		Thread attackThread = new Thread(new AttackThread(this,200));
		attackThread.start();
		this.hitBox = new GameImage(new Image(ClassLoader.getSystemResource("Images/HitBox.png").toString()));
		this.hitBox.updatePosition(this.getPosition());
		this.hitBox.setFitWidth(90);
		this.hitBox.setFitHeight(150);
		this.hitBox.setVisible(false);
		new Thread(new DamagedThread(this,2000,500));
		imageStatus = ImageStatus.STANDRIGHT;
		nowStatus = ImageStatus.STANDRIGHT;
		this.jumpCount = 0;
		this.jumpLimit = 2;
	}	
	//Method
	///update image status
	public void updateImageStatus() {
		if(isAttack) {
			if(isRight) this.imageStatus = ImageStatus.ATTACKRIGHT;
			else this.imageStatus = ImageStatus.ATTACKLEFT;
		}else if(isMove) {
			if(feetStatus == FeetStatus.AIR) {
				if(isRight) this.imageStatus = ImageStatus.AIRRIGHT;
				else this.imageStatus = ImageStatus.AIRLEFT;
			}else {
				if(isRight) this.imageStatus = ImageStatus.MOVERIGHT;
				else this.imageStatus = ImageStatus.MOVELEFT;
			}
		}else {
			if(isRight) this.imageStatus = ImageStatus.STANDRIGHT;
			else this.imageStatus = ImageStatus.STANDLEFT;
		}
		if(!imageStatus.equals(nowStatus)) {
			if(imageStatus == ImageStatus.STANDRIGHT) {
				this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/stand right.png").toString()));
				this.nowStatus = ImageStatus.STANDRIGHT;
			}
			if(imageStatus == ImageStatus.STANDLEFT) {
				this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/stand left.png").toString()));
				this.nowStatus = ImageStatus.STANDLEFT;
			}
			if(imageStatus == ImageStatus.MOVELEFT) {
				this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/run left f2.gif").toString()));
				this.nowStatus = ImageStatus.MOVELEFT;
			}
			if(imageStatus == ImageStatus.MOVERIGHT) {
				this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/run right f2.gif").toString()));
				this.nowStatus = ImageStatus.MOVERIGHT;
			}
			if(imageStatus == ImageStatus.ATTACKLEFT) {
				this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/attack left2.0.gif").toString()));
				this.nowStatus = ImageStatus.ATTACKLEFT;
			}
			if(imageStatus == ImageStatus.ATTACKRIGHT) {
				this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/attack right2.0.gif").toString()));
				this.nowStatus = ImageStatus.ATTACKRIGHT;
			}
			if(imageStatus == ImageStatus.AIRLEFT) {
				this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/run left 2.png").toString()));
				this.nowStatus = ImageStatus.AIRLEFT;
			}
			if(imageStatus == ImageStatus.AIRRIGHT) {
				this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/run right 2.png").toString()));
				this.nowStatus = ImageStatus.AIRRIGHT;
			}
		}
	}
	public void moveLeft() {
		this.getPosition().add(new Vector2D(-10,0));
		this.isRight = false;
		this.isMove = true;
	}
	public void moveRight() {
		this.getPosition().add(new Vector2D(10,0));
		this.isRight = true;
		this.isMove = true;
	}
	public void notMove() {
		isMove = false;
	}
	public void setAttack() {
		if(!isAttack) {
			this.isAttack = true;
		}
	}
	///Jump
	public void jump() {
		if(this.jumpCount <= this.jumpLimit) {
			feetStatus = FeetStatus.AIR;
			this.getVelocity().setY(0);
			Vector2D jumpForce = new Vector2D(0,-20);
			this.getPosition().add(jumpForce);
			this.getVelocity().add(jumpForce);
			this.jumpCount = this.jumpCount + 1;
		}
	}
	///mini jump
	public void minijump() {
		if(this.feetStatus == FeetStatus.AIR) {
			feetStatus = FeetStatus.AIR;
			this.getVelocity().setY(0);
			Vector2D jumpForce = new Vector2D(0,-10);
			this.getPosition().add(jumpForce);
			this.getVelocity().add(jumpForce);
		}
	}
	///gravity update
	public void gravityUpdate(Floor floor,ArrayList<Plat> platforms){
		boolean isOnPlat = false;
		for(int i = 0 ; i < platforms.size() ; i++) {
			Plat platform = platforms.get(i);
			if(!platform.equals(null)) {
					if(isCollide(platform)) {
						if(this.getPosition().getY()+this.getSize().getY()-30<platform.getPosition().getY()) {
							this.getVelocity().setY(0);
							this.getPosition().setY(platform.getPosition().getY() - this.getSize().getY() + 7);
							feetStatus = FeetStatus.PLATFORM;
							this.jumpCount = 0;
							isOnPlat = true;
						}
					}
				if(feetStatus == FeetStatus.PLATFORM) {
					if(isCollide(platform) && this.getPosition().getY()+this.getSize().getY()-30<platform.getPosition().getY()) {
						this.getPosition().add(platform.getVelocity());
					}
				}
			}
		}
		if(!isOnPlat && !isCollide(floor)) {
			feetStatus = FeetStatus.AIR;
		}
		if(isCollide(floor)) {
			this.getVelocity().setY(0);
			this.getPosition().setY(floor.getPosition().getY() - this.getSize().getY() + 7);
			this.updateImage();
			feetStatus = FeetStatus.FLOOR;
			this.updateImage();
			this.jumpCount = 0;
		}
		if(feetStatus == FeetStatus.AIR) {
			Vector2D gravityForce = new Vector2D(0,1.5);
			this.getVelocity().add(gravityForce);
		}
	}
	///take damage
	public void takeDamage() {
		if(!this.isImmune()) {
			Thread damagedThread = new Thread(new DamagedThread(this,2000,500));
			damagedThread.start(); 
		}
	}
	///update hitbox position
	public void updateHitBox() {
		if(this.hitBox != null) {
			if(isRight) this.hitBox.updatePosition(this.getPosition());
			else this.hitBox.updatePosition(this.getPosition().sum(new Vector2D(60,0)));
		}
	}
	//move limit
	public void moveLimit() {
		if(this.getPosition().getX() < 0) this.getPosition().setX(0);
		if(this.getPosition().getX() > 1366 - this.getSize().getX()) this.getPosition().setX(1366 - this.getSize().getX());
	}
	//getter and setter
	public FeetStatus getFeetStatus() {
		return feetStatus;
	}
	public void setFeetStatus(FeetStatus feetStatus) {
		this.feetStatus = feetStatus;
	}
	public boolean isMove() {
		return isMove;
	}
	public void setMove(boolean isMove) {
		this.isMove = isMove;
	}
	public boolean isAttack() {
		return isAttack;
	}
	public void setAttack(boolean isAttack) {
		this.isAttack = isAttack;
	}
	public Sword getSword() {
		return sword;
	}
	public void setSword(Sword sword) {
		this.sword = sword;
	}
	public boolean isImmune() {
		return isImmune;
	}
	public void setImmune(boolean isImmune) {
		this.isImmune = isImmune;
	}
	public GameImage getHitBox() {
		return hitBox;
	}
	public void setHitBox(GameImage hitBox) {
		this.hitBox = hitBox;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public ImageStatus getImageStatus() {
		return imageStatus;
	}
	public void setImageStatus(ImageStatus imageStatus) {
		this.imageStatus = imageStatus;
	}
	public ImageStatus getNowStatus() {
		return nowStatus;
	}
	public void setNowStatus(ImageStatus nowStatus) {
		this.nowStatus = nowStatus;
	}
	
}	
