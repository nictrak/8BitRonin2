package Logic;

import java.util.ArrayList;


public class CastThread implements Runnable {

	public static ArrayList<FireBall> waitedFireBalls;
	public Mage owner;
	
	public CastThread(Mage owner) {
		this.owner = owner;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(this.getOwner().getLife() > 0 && this.getOwner().getTarget().getLife() > 0) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(waitedFireBalls == null) waitedFireBalls = new  ArrayList<FireBall>();
			if(this.getOwner().getLife() > 0) {
				FireBall fireBall = new FireBall(new Vector2D(this.owner.getPosition()),this.owner.getCastDirection(),this.owner.findHero());
				waitedFireBalls.add(fireBall);
			}
		}
	}
	
	public void setup() {
		waitedFireBalls = new  ArrayList<FireBall>();
	}
	
	public static ArrayList<FireBall> getWaitedFireBalls() {
		return waitedFireBalls;
	}

	public static void setWaitedFireBalls(ArrayList<FireBall> waitedFireBalls) {
		CastThread.waitedFireBalls = waitedFireBalls;
	}

	public Mage getOwner() {
		return owner;
	}

	public void setOwner(Mage owner) {
		this.owner = owner;
	}
	
	
}
