package Logic;

public class Entity {
	private Vector2D position;
	private Vector2D velocity;
	private Vector2D size;
	
	//constructor √—∫pos vel sizeµ“¡≈”¥—∫
	public Entity(Vector2D pos,Vector2D vel,Vector2D size) {
		position = pos;
		velocity = vel;
		this.size = size;
	}
	
	public Entity() {
		position = new Vector2D(0,0);
		velocity = new Vector2D(0,0);
		size = new Vector2D(100,100);
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
	
	
	
}
