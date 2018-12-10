package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class WingedGoblin extends Monster{
	
	public WingedGoblin(Vector2D pos,Hero h) {
		super(pos,h);
		this.setLeftSide("Images/melee wing left.gif");
		this.setRightSide("Images/melee wing right.gif");
		this.setGameImage(new GameImage(new Image(ClassLoader.getSystemResource(this.getLeftSide()).toString())));
		this.updateImage();
		this.getHitBox().updatePosition(this.getPosition().sum(new Vector2D(50,0)));
		this.getHitBox().setFitWidth(60);
		this.getHitBox().setFitHeight(70);
		this.getHitBox().setVisible(false);
	}
	
	@Override
	public Vector2D direct() {
		double a = findHero();
		Vector2D j = new Vector2D(Math.cos(a)*5,Math.sin(a)*5);
		this.getPosition().add(j);
		if(j.getX() >= 0) this.isRight = true;
		else this.isRight = false;
		this.updateSide();
		this.getHitBox().updatePosition(this.getPosition().sum(new Vector2D(60,10)));
		return j;
	}


}
