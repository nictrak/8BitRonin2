package Logic;

import java.util.ArrayList;

import App.GameImage;
import javafx.scene.image.Image;

public class Hero extends LifeForm {
	
	//Attributes 
	private FeetStatus feetStatus;
	//Constructor
	///basic hero
	public Hero() {
		super(new Vector2D(653,578),new Vector2D(0,0),new Vector2D(60,90),new GameImage(new Image(ClassLoader.getSystemResource("Images/HeroTest.png").toString())),3);
		this.getGameImage().updatePosition(this.getPosition());
		this.feetStatus = FeetStatus.FLOOR;
	}	
	//Method
	///Attack
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
	}
	///gravity update
	public void gravityUpdate(Floor floor,ArrayList<Platform> platforms){
		if(isCollide(floor)) {
			this.getVelocity().setY(0);
			this.getPosition().setY(floor.getPosition().getY()-this.getSize().getY());
			feetStatus = FeetStatus.FLOOR;
		}
		for(Platform platform : platforms) {
			if(feetStatus == FeetStatus.AIR) {
				if(isCollide(platform) && this.getPosition().getY()+this.getSize().getY()-30<platform.getPosition().getY()) {
					this.getVelocity().setY(0);
					this.getPosition().setY(platform.getPosition().getY()-this.getSize().getY());
					feetStatus = FeetStatus.PLATFORM;
				}
			}
			if(feetStatus == FeetStatus.PLATFORM) {
				if(!isCollide(platform)) {
					feetStatus = FeetStatus.AIR;
				}else this.getPosition().add(platform.getVelocity());
			}
		}
		if(feetStatus == FeetStatus.AIR) {
			Vector2D gravityForce = new Vector2D(0,1);
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
}	
