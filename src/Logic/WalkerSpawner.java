package Logic;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Platform;
public class WalkerSpawner {
	
	private double spawnRate;
	private Thread spawnthread;
	private Hero target;
	private Vector2D spawnPos;
	private boolean isSpawn;

	
	private class SpawnThread implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				try {
					Thread.sleep((long) spawnRate);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int randomSide = (int)(Math.random() * 2 + 1);
				spawnPos = new Vector2D(0,588);
				if(randomSide == 1) {
					spawnPos.setX(-20);
				}else {
					spawnPos.setX(1376);
				}
				isSpawn = true;
			}
		}
	}
	
	public WalkerSpawner(double spawnRate,Pane pane,Hero target) {
		this.spawnRate = spawnRate;
		this.target = target;
		this.spawnthread = new Thread(new SpawnThread());
		this.spawnPos = new Vector2D(0,0);
		this.isSpawn = false;
	}
	
	public void start() {
		this.spawnthread.start();
	}
	//getter setter

	public double getSpawnRate() {
		return spawnRate;
	}

	public void setSpawnRate(double spawnRate) {
		this.spawnRate = spawnRate;
	}

	public Thread getSpawnthread() {
		return spawnthread;
	}

	public void setSpawnthread(Thread spawnthread) {
		this.spawnthread = spawnthread;
	}


	public Hero getTarget() {
		return target;
	}

	public void setTarget(Hero target) {
		this.target = target;
	}

	public Vector2D getSpawnPos() {
		return spawnPos;
	}

	public void setSpawnPos(Vector2D spawnPos) {
		this.spawnPos = spawnPos;
	}

	public boolean isSpawn() {
		return isSpawn;
	}

	public void setSpawn(boolean isSpawn) {
		this.isSpawn = isSpawn;
	}
	
}
