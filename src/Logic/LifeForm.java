package Logic;

import App.GameImage;

public class LifeForm extends Entity{
	
	private int life;
	private boolean isDeath;
	
	public LifeForm() {
		super();
		life = 1;
	}
	
	public LifeForm(Vector2D pos,Vector2D vel,Vector2D size,GameImage gameImage,int l) {
		super(pos,vel,size,gameImage);
		if(l < 0) l = 1 ; 
		isDeath = false;
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
		if(this.life == 0) {
			this.isDeath = true;
		}
	}

	public boolean isDeath() {
		return isDeath;
	}

	public void setDeath(boolean isDeath) {
		this.isDeath = isDeath;
	}
	
	
	
}
