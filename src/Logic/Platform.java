package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class Platform extends Entity{
	public boolean isRight;
	
	public Platform(Vector2D position,boolean isRight) {
		super(position,new Vector2D(0,0),new Vector2D(80,20),new GameImage(new Image(ClassLoader.getSystemResource("Images/Platform.png").toString())));
		this.getGameImage().updatePosition(this.getPosition());
		this.isRight = isRight;
		if(isRight) this.setVelocity(new Vector2D(7,0));
		else this.setVelocity(new Vector2D(-7,0));
	}
	
	
}
