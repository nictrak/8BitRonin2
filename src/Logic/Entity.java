package Logic;

import App.GameImage;

public class Entity {
	private Vector2D position;
	private Vector2D velocity;
	private Vector2D size;
	private Vector2D maxVelocity;
	private Vector2D gravity;
	private GameImage gameImage;
	
	//constructor ÃÑºpos vel sizeµÒÁÅÓ´Ñº
	public Entity(Vector2D pos,Vector2D vel,Vector2D maxVel,Vector2D size,Vector2D gravity,GameImage gameImage) {
		position = pos;
		velocity = vel;
		this.maxVelocity = maxVel;
		this.size = size;
		this.gravity = gravity;
		this.gameImage = gameImage;
	}
	
	public Entity() {
		position = new Vector2D(0,0);
		velocity = new Vector2D(0,0);
		size = new Vector2D(100,100);
	}
	
	public void updateGravity() {
		this.velocity.add(this.gravity);
	}
	public void updateImage() {
		this.gameImage.updatePosition(position);
	}
	public void update() {
		this.position.add(this.velocity);
		this.updateGravity();
		this.updateImage();
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
		if(Math.abs(velocity.getX()) > this.maxVelocity.getX()) velocity.setX(this.maxVelocity.getX());
		if(Math.abs(velocity.getY()) > this.maxVelocity.getY()) velocity.setY(this.maxVelocity.getY());
		this.velocity = velocity;
	}

	public Vector2D getSize() {
		return size;
	}

	public void setSize(Vector2D size) {
		this.size = size;
	}

	public Vector2D getMaxVelocity() {
		return maxVelocity;
	}

	public void setMaxVelocity(Vector2D maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	public Vector2D getGravity() {
		return gravity;
	}

	public void setGravity(Vector2D gravity) {
		this.gravity = gravity;
	}

	public GameImage getGameImage() {
		return gameImage;
	}

	public void setGameImage(GameImage gameImage) {
		this.gameImage = gameImage;
	}
	
	
	
	
}
