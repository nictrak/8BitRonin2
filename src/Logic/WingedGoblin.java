package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class WingedGoblin extends Monster{
	
	public WingedGoblin(Hero h) {
		super(h);
		this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource("Images/melee wing.png").toString())));
		this.updateImage();
	}
	public WingedGoblin(Vector2D pos,Hero h) {
		super(pos,h);
		this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource("Images/melee wing.png").toString())));
		this.updateImage();
	}
	
	@Override
	public Vector2D direct() {
		double a = findHero();
		
		Vector2D j = new Vector2D(Math.cos(a)*5,Math.sin(a)*5);
		this.getPosition().add(j);
		return j;
	}


}
