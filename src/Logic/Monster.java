package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class Monster extends LifeForm{
	
	private Hero target;
	
	public Monster(Hero h) {
		super(new Vector2D(500,500),new Vector2D(0,0),new Vector2D(60,90),new GameImage(new Image(ClassLoader.getSystemResource("Images/Hero1.png").toString())),3);
		target = h;
		this.getGameImage().updatePosition(this.getPosition());
	}
	
	public Monster(Vector2D pos,Vector2D vel,Vector2D size,GameImage gameImage,int l,Hero h) {
		super(pos,vel,size,gameImage,l);
		target = h;
	}
	
	public double findHero() {
		double x = target.getPosition().getX() - this.getPosition().getX();
		double y = target.getPosition().getY() - this.getPosition().getY();
		
		double rad = Math.atan2(y, x);
		return rad;
		
	}
	

}
