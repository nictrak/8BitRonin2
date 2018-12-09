package Logic;

public class Vector2D {
	private double x;
	private double y;
	
	public Vector2D(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Vector2D primary) {
		this.x = primary.x;
		this.y = primary.y;
	}
	
	public void add(Vector2D other) {
		this.x = x + other.getX();
		this.y = y + other.getY();
	}
	public Vector2D sum(Vector2D other) {
		return new Vector2D(this.x + other.x,this.y + other.y);
	}
	//getter and setter
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
}
