package Logic;

public class Vector2D {
	private double x;
	private double y;
	
	public Vector2D(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(Vector2D other) {
		this.x = x + other.getX();
		this.y = y + other.getY();
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
