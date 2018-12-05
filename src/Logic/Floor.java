package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class Floor extends Entity {
	public Floor() {
		super(new Vector2D(0,668),new Vector2D(0,0),new Vector2D(32,64),new GameImage(new Image(ClassLoader.getSystemResource("Images/Floor.png").toString())));
		this.getGameImage().updatePosition(this.getPosition());
	}	
}
