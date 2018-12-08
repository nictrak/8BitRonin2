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
		while(this.getOwner().getLife() > 0) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(waitedFireBalls == null) waitedFireBalls = new  ArrayList<FireBall>();
			if(this.getOwner().getLife() > 0) waitedFireBalls.add(new FireBall(this.owner.getPosition().sum(new Vector2D(0,0)),this.owner.getCastDirection()));
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
