package Logic;

public class DamagedThread implements Runnable{
	
	private Hero hero;
	private double immuneRate;
	private double blinkRate;
	private static boolean isVisible;
	
	public DamagedThread(Hero hero,double immuneRate, double blinkRate) {
		this.hero = hero;
		this.immuneRate = immuneRate;
		this.blinkRate = blinkRate;
		isVisible = true;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.hero.setLife(this.hero.getLife() - 1);
		double count = this.immuneRate;
		while(count > 0 ) {
			try {
				isVisible = false;
				//this.hero.getGameImage().setVisible(false);
				Thread.sleep((long) (blinkRate/2));
				//this.hero.getGameImage().setVisible(true);
				isVisible = true;
				Thread.sleep((long) (blinkRate/2));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count = count - this.blinkRate;
		}
		this.hero.setImmune(false);
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public double getImmuneRate() {
		return immuneRate;
	}

	public void setImmuneRate(double immuneRate) {
		this.immuneRate = immuneRate;
	}

	public double getBlinkRate() {
		return blinkRate;
	}

	public void setBlinkRate(double blinkRate) {
		this.blinkRate = blinkRate;
	}

	public static boolean isVisible() {
		return isVisible;
	}

	public static void setVisible(boolean isVisible) {
		DamagedThread.isVisible = isVisible;
	}
	

	
	
}
