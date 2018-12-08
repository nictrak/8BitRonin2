package Logic;

import App.GameImage;
import javafx.scene.image.Image;

public class HealthBar {
	private GameImage health1;
	private GameImage health2;
	private GameImage health3;
	private Hero hero;
	
	public HealthBar(Hero hero) {
		this.health1 = new GameImage(new Image(ClassLoader.getSystemResource("Images/heart2.gif").toString()));
		this.health1.updatePosition(new Vector2D(0,0));
		this.health2 = new GameImage(new Image(ClassLoader.getSystemResource("Images/heart2.gif").toString()));
		this.health2.updatePosition(new Vector2D(80,0));
		this.health3 = new GameImage(new Image(ClassLoader.getSystemResource("Images/heart2.gif").toString()));
		this.health3.updatePosition(new Vector2D(160,0));
		this.hero = hero;
	}
	
	public void updateBar() {
		if(hero.getLife() == 2) {
			health3.setVisible(false);
		}else if(hero.getLife() == 1) {
			health2.setVisible(false);
		}else if(hero.getLife() == 0) {
			health1.setVisible(false);
		}
	}

	public GameImage getHealth1() {
		return health1;
	}

	public void setHealth1(GameImage health1) {
		this.health1 = health1;
	}

	public GameImage getHealth2() {
		return health2;
	}

	public void setHealth2(GameImage health2) {
		this.health2 = health2;
	}

	public GameImage getHealth3() {
		return health3;
	}

	public void setHealth3(GameImage health3) {
		this.health3 = health3;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	
	
}
