package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class Hero extends LifeForm {
	
	//Attributes 
	private FeetStatus feetStatus;
	//Constructor
	///basic hero
	public Hero() {
		super(new Vector2D(667,604),new Vector2D(0,0),new Vector2D(10,10),new Vector2D(32,64),new Vector2D(0,2),new GameImage(new Image(ClassLoader.getSystemResource("Hero1.png").toString())),3);
		this.feetStatus = FeetStatus.FLOOR;
	}	
	//Method
	///Attack
	///Jump
	
	//getter and setter
	public FeetStatus getFeetStatus() {
		return feetStatus;
	}
	public void setFeetStatus(FeetStatus feetStatus) {
		this.feetStatus = feetStatus;
	}
}	
