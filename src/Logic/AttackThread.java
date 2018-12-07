package Logic;

import javafx.scene.image.Image;

public class AttackThread implements Runnable{
	
	private Hero hero;
	private double attackRate;
	private static boolean waited;
	
	public AttackThread(Hero hero,double attackRate) {
		this.hero = hero;
		this.attackRate = attackRate;
		this.waited = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			this.waited = false;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(hero.isAttack()) {
				if(hero.isRight()) hero.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/attack right2.0.gif").toString()));
				else hero.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/attack left2.0.gif").toString()));
				
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(hero.isMove()) {
					if(hero.isRight()) hero.setRight();
					else hero.setLeft();
				}else {
					if(hero.isRight()) hero.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/stand right.png").toString()));
					else hero.getGameImage().setImage(new Image(ClassLoader.getSystemResource("Images/stand left.png").toString()));
				}
				hero.setAttack(false);
				this.waited = true;
				try {
					Thread.sleep((long) attackRate);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public double getAttackRate() {
		return attackRate;
	}

	public void setAttackRate(double attackRate) {
		this.attackRate = attackRate;
	}

	public static boolean isWaited() {
		return waited;
	}

	public static void setWaited(boolean waited) {
		AttackThread.waited = waited;
	}
	
	
}
