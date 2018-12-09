package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class FireBall extends Entity{
	
	private double angle;
	
	public FireBall(Vector2D pos,Vector2D vel,double angle) {
		super(pos,vel,new Vector2D(0,0),new GameImage(new Image(ClassLoader.getSystemResource("Images/fireball.gif").toString())));
		this.angle = angle;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	
}
