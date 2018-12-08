package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class MeleeWalker extends Monster{

	public MeleeWalker(Hero h) {
		super(h);
		this.setLeftSide("Images/walker left resize.gif");
		this.setRightSide("Images/walker right resize.gif");
		this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource(this.getLeftSide()).toString())));
		this.updateImage();
		this.setHitBox(new GameImage(new Image(ClassLoader.getSystemResource("Images/HitBox.png").toString())));
		this.getHitBox().updatePosition(this.getPosition());
		this.getHitBox().setFitWidth(70);
		this.getHitBox().setFitHeight(70);
		this.getHitBox().setVisible(false);
		this.isRight = false;
	}
	public MeleeWalker(Vector2D pos,Hero h) {
		super(pos,h);
		this.setLeftSide("Images/walker left resize.gif");
		this.setRightSide("Images/walker right resize.gif");
		this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource(this.getLeftSide()).toString())));
		//this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource("Images/walker left resize.gif").toString())));
		this.updateImage();
		this.setHitBox(new GameImage(new Image(ClassLoader.getSystemResource("Images/HitBox.png").toString())));
		this.getHitBox().updatePosition(this.getPosition());
		this.getHitBox().setFitWidth(70);
		this.getHitBox().setFitHeight(70);
		this.getHitBox().setVisible(false);
		this.isRight = false;
	}
	
	@Override
	public Vector2D direct() {
		double a = findHero();
		Vector2D j = new Vector2D(Math.cos(a)*3,0);
		if(j.getX() >= 0) this.isRight = true;
		else this.isRight = false;
		this.updateSide();
		this.getPosition().add(j);
		this.getHitBox().updatePosition(this.getPosition().sum(new Vector2D(20,10)));
		return j;
	}
}
