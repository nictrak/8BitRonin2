package Logic;

import javafx.scene.image.Image;

public class AttackThread implements Runnable{
	
	private Hero hero;
	
	public AttackThread(Hero hero) {
		this.hero = hero;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(hero.isRight()) hero.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/attack right2.0.gif").toString()));
		else hero.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/attack left2.0.gif").toString()));
		hero.setAttack(true);
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(hero.isRight()) hero.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/stand right.png").toString()));
		else hero.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/stand left.png").toString()));
		hero.setAttack(false);
	}
	
}
