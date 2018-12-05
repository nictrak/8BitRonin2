package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class Hero extends LifeForm {
	
	//Attributes 
	private FeetStatus feetStatus;
	//Constructor
	///basic hero
	public Hero() {
		super(new Vector2D(653,578),new Vector2D(0,0),new Vector2D(60,90),new GameImage(new Image(ClassLoader.getSystemResource("Images/run right 1.png").toString())),3);
		this.getGameImage().updatePosition(this.getPosition());
		this.feetStatus = FeetStatus.FLOOR;
	}	
	//Method
	///Attack
	///Jump
	public void jump() {
		feetStatus = FeetStatus.AIR;
		this.getVelocity().setY(0);
		Vector2D jumpForce = new Vector2D(0,-20);
		this.getPosition().add(jumpForce);
		this.getVelocity().add(jumpForce);
	}
	///gravity update
	public void gravityUpdate(Floor floor){
		if(isCollide(floor)) {
			this.getVelocity().setY(0);
			feetStatus = FeetStatus.FLOOR;
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
