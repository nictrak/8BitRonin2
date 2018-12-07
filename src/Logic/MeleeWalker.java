package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class MeleeWalker extends Monster{

	public MeleeWalker(Hero h) {
		super(h);
		this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource("Images/walker left resize.gif").toString())));
		this.updateImage();
	}
	public MeleeWalker(Vector2D pos,Hero h) {
		super(pos,h);
		this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource("Images/walker left resize.gif").toString())));
		this.updateImage();
	}
	
	@Override
	public Vector2D direct() {
		double a = findHero();
		
		Vector2D j = new Vector2D(Math.cos(a)*10,0);
		this.getPosition().add(j);
		return j;
	}
}
