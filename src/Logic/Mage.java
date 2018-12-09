package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class Mage extends Monster{
	
	public Mage(Hero h) {
		super(h);
		this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource("Images/wing mage cast.png").toString())));
		this.updateImage();
		this.getHitBox().updatePosition(this.getPosition());
		this.getHitBox().setFitWidth(70);
		this.getHitBox().setFitHeight(70);
		this.getHitBox().setVisible(false);
		Thread castThread = new Thread(new CastThread(this));
		castThread.start();
	}
	public Mage(Vector2D pos,Hero h) {
		super(pos,h);
		this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource("Images/wing mage cast.png").toString())));
		this.updateImage();
		this.getHitBox().updatePosition(this.getPosition());
		this.getHitBox().setFitWidth(70);
		this.getHitBox().setFitHeight(70);
		this.getHitBox().setVisible(false);
		Thread castThread = new Thread(new CastThread(this));
		castThread.start();
	}
	
	@Override
	public Vector2D direct() {
		this.getHitBox().updatePosition(this.getPosition().sum(new Vector2D(50,10)));
		return null;
	}
	
	public Vector2D getCastDirection() {
		double angle = findHero();
		Vector2D direction = new Vector2D(Math.cos(angle)*2.5,Math.sin(angle)*2.5);
		return direction;
	}


}
