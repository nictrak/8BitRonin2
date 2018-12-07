package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class Sword extends Entity {
	
	private Hero owner;
	
	public Sword(Hero owner) {
		super(new Vector2D(0,0),new Vector2D(0,0),new Vector2D(0,0),new GameImage(new Image(ClassLoader.getSystemResource("Images/SwordHitBox.png").toString())));
		this.owner = owner;
		this.getGameImage().setVisible(false);
	}
	@Override
	public void update() {
		if(owner.isRight()) {
			this.setPosition(owner.getPosition().sum(new Vector2D(90,0)));
		}else {
			this.setPosition(owner.getPosition().sum(new Vector2D(-10,0)));
		}
		this.updateImage();
	}
}
