package Logic;

public class WingedGoblin extends Monster{
	
	public WingedGoblin(Hero h) {
		super(h);
	}
	
	public Vector2D direct() {
		double a = findHero();
		
		Vector2D j = new Vector2D(Math.cos(a)*5,Math.sin(a)*5);
		this.getPosition().add(j);
		return j;
	}


}
