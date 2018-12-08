package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class FireBall extends Entity{
	public FireBall(Vector2D pos,Vector2D vel) {
		super(pos,vel,new Vector2D(0,0),new GameImage(new Image(ClassLoader.getSystemResource("Images/fireball.gif").toString())));
	}
}
