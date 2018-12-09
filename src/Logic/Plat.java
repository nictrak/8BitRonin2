package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class Plat extends Entity{
	public boolean isRight;
	
	public Plat(Vector2D position,boolean isRight) {
		super(position,new Vector2D(0,0),new Vector2D(80,20),new GameImage(new Image(ClassLoader.getSystemResource("Images/platform.gif").toString())));
		this.getGameImage().updatePosition(this.getPosition());
		this.isRight = isRight;
		if(isRight) this.setVelocity(new Vector2D(5,0));
		else this.setVelocity(new Vector2D(-5,0));
	}
	
	
}
