package Logic;

public class LifeForm extends Entity{
	
	private int life;
	
	public LifeForm() {
		super();
		life = 1;
	}
	
	public LifeForm(Vector2D pos,Vector2D vel,Vector2D size,int l) {
		super(pos,vel,size);
		life = l;
	}
	
	public void takeDamage(int l) {
		life = life - 1;
	}
	
	

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	
}
