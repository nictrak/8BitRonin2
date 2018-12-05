package App;

import Logic.Vector2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameImage extends ImageView{
	
	public GameImage(Image image) {
		super(image);
	}
	
	public void updatePosition(Vector2D position) {
		this.setX(position.getX());
		this.setY(position.getY());
	}
}
