package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class WingedGoblin extends Monster{
	
	public WingedGoblin(Hero h) {
		super(h);
		this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource("Images/melee wing.png").toString())));
		this.updateImage();
		this.getHitBox().updatePosition(this.getPosition());
		this.getHitBox().setFitWidth(70);
		this.getHitBox().setFitHeight(70);
		this.getHitBox().setVisible(false);
	}
	public WingedGoblin(Vector2D pos,Hero h) {
		super(pos,h);
		this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource("Images/melee wing.png").toString())));
		this.updateImage();
		this.getHitBox().updatePosition(this.getPosition());
		this.getHitBox().setFitWidth(70);
		this.getHitBox().setFitHeight(70);
		this.getHitBox().setVisible(false);
	}
	
	@Override
	public Vector2D direct() {
		double a = findHero();
		
		Vector2D j = new Vector2D(Math.cos(a)*2.5,Math.sin(a)*2.5);
		this.getPosition().add(j);
		this.getHitBox().updatePosition(this.getPosition().sum(new Vector2D(50,10)));
		return j;
	}


}
