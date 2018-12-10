package Logic;

import App.GameImage;
import javafx.scene.image.ImageView;

public class Entity {
	private Vector2D position;
	private Vector2D velocity;
	private Vector2D size;
	private GameImage gameImage;
	
	//constructor √—∫pos vel sizeµ“¡≈”¥—∫
	public Entity(Vector2D pos,Vector2D vel,Vector2D size,GameImage gameImage) {
		position = pos;
		velocity = vel;
		this.size = size;
		this.gameImage = gameImage;
	}

	public void updateImage() {
		this.gameImage.updatePosition(position);
	}
	public void update() {
		this.position.add(this.velocity);
		this.updateImage();
	}
	
	public boolean isCollide(Entity other) {
		return this.gameImage.getBoundsInParent().intersects(other.gameImage.getBoundsInParent());
	}
	public boolean isCollide(ImageView other) {
		return this.gameImage.getBoundsInParent().intersects(other.getBoundsInParent());
	}
	
	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public Vector2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	public Vector2D getSize() {
		return size;
	}

	public void setSize(Vector2D size) {
		this.size = size;
	}

	public GameImage getGameImage() {
		return gameImage;
	}

	public void setGameImage(GameImage gameImage) {
		this.gameImage = gameImage;
	}
	
	
	
	
}
