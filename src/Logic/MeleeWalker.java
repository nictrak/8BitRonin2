package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class MeleeWalker extends Monster{

	public MeleeWalker(Hero h) {
		super(h);
		this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource("Images/walker left resize.gif").toString())));
		this.updateImage();
		this.setHitBox(new GameImage(new Image(ClassLoader.getSystemResource("Images/HitBox.png").toString())));
		this.getHitBox().updatePosition(this.getPosition());
		this.getHitBox().setFitWidth(70);
		this.getHitBox().setFitHeight(70);
		this.getHitBox().setVisible(false);
	}
	public MeleeWalker(Vector2D pos,Hero h) {
		super(pos,h);
		this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource("Images/walker left resize.gif").toString())));
		this.updateImage();
		this.setHitBox(new GameImage(new Image(ClassLoader.getSystemResource("Images/HitBox.png").toString())));
		this.getHitBox().updatePosition(this.getPosition());
		this.getHitBox().setFitWidth(70);
		this.getHitBox().setFitHeight(70);
		this.getHitBox().setVisible(false);
	}
	
	@Override
	public Vector2D direct() {
		double a = findHero();
		
		Vector2D j = new Vector2D(Math.cos(a)*5,0);
		this.getPosition().add(j);
		this.getHitBox().updatePosition(this.getPosition().sum(new Vector2D(20,10)));
		return j;
	}
}
