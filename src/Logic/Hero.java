package Logic;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import App.GameImage;
import javafx.scene.image.Image;

public class Hero extends LifeForm {
	
	//Attributes 
	private FeetStatus feetStatus;
	private boolean isRight;
	private boolean isMove;
	private boolean isAttack;
	private Sword sword;
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
		Thread attackThread = new Thread(new AttackThread(this,300));
		attackThread.start();
	}	
	//Method
	///move left
	public void setLeft() {
		this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/run left f2.gif").toString()));
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
	public void setRight() {
		this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/run right f2.gif").toString()));
	}
	public void setJump() {
		if(!isAttack) {
			if(isMove) {
				if(isRight) this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/run right 2.png").toString()));
				else this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/run left 2.png").toString()));
			}
		}
	}
	public void notMove() {
		if(!isAttack) {
			if(isRight) this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/stand right.png").toString()));
			else this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/stand left.png").toString()));
			isMove = false;
		}
	}
	///Attack
	public void setAttack() {
		if(!isAttack) {
			this.isAttack = true;
		}
	}
	///Down
	public void down() {
		if(feetStatus == FeetStatus.PLATFORM) {
			this.getPosition().add(new Vector2D(0,20));
			feetStatus = FeetStatus.AIR;
		}
	}
	///Jump
	public void jump() {
		feetStatus = FeetStatus.AIR;
		this.getVelocity().setY(0);
		Vector2D jumpForce = new Vector2D(0,-20);
		this.getPosition().add(jumpForce);
		this.getVelocity().add(jumpForce);
		this.setJump();
	}
	///gravity update
	public void gravityUpdate(Floor floor,ArrayList<Plat> platforms){
		if(isCollide(floor) && feetStatus == FeetStatus.AIR ) {
			this.getVelocity().setY(0);
			this.getPosition().setY(floor.getPosition().getY()-this.getSize().getY());
			this.updateImage();
			feetStatus = FeetStatus.FLOOR;
				if(isMove) {
					if(isRight) this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/run right f2.gif").toString()));
					else this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/run left f2.gif").toString()));
				}
				else {
					if(isRight) this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/stand right.png").toString()));
					else this.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/stand left.png").toString()));
				}
				this.updateImage();
		}
		for(int i = 0 ; i < platforms.size() ; i++) {
			Plat platform = platforms.get(i);
			if(!platform.equals(null)) {
				if(feetStatus == FeetStatus.AIR) {
					if(isCollide(platform) && this.getPosition().getY()+this.getSize().getY()-30<platform.getPosition().getY()) {
						this.getVelocity().setY(0);
						this.getPosition().setY(platform.getPosition().getY()-this.getSize().getY());
						feetStatus = FeetStatus.PLATFORM;
					}
				}
				if(feetStatus == FeetStatus.PLATFORM) {
					if(!isCollide(platform) ) {
						feetStatus = FeetStatus.AIR;
					}else if(this.getPosition().getY()+this.getSize().getY()-30<platform.getPosition().getY())this.getPosition().add(platform.getVelocity());
				}
			}
		}
		if(feetStatus == FeetStatus.AIR) {
			Vector2D gravityForce = new Vector2D(0,1.5);
			this.getVelocity().add(gravityForce);
		}
	}
	//getter and setter
	public FeetStatus getFeetStatus() {
		return feetStatus;
	}
	public void setFeetStatus(FeetStatus feetStatus) {
		this.feetStatus = feetStatus;
	}
	public boolean isRight() {
		return isRight;
	}
	public void setRight(boolean isRight) {
		this.isRight = isRight;
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
	
	
}	
