package Logic;


public class AttackThread implements Runnable{
	
	private Hero hero;
	private double attackRate;
	private static boolean waited;
	
	public AttackThread(Hero hero,double attackRate) {
		this.hero = hero;
		this.attackRate = attackRate;
		waited = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(hero.getLife() > 0) {
			waited = false;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(hero.isAttack()) {
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hero.setAttack(false);
				waited = true;
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
