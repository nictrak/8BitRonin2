package Logic;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Platform;

public class PlatformSpawner {
	
	private double spawnRate;
	private Thread spawnthread;
	private Vector2D spawnPos;
	private boolean isSpawn;
	private boolean isRightSide;

	
	private class SpawnThread implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				double randomPos = Math.random() * 507 + 100;
				int randomSide = (int)(Math.random() * 2 + 1);
				spawnPos = new Vector2D(0,randomPos);
				if(randomSide == 1) {
					spawnPos.setX(-10);
					isRightSide = true;
				}else {
					spawnPos.setX(1376);
					isRightSide = false;
				}
				isSpawn = true;
				try {
					Thread.sleep((long) spawnRate);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public PlatformSpawner(double spawnRate,Pane pane,Hero target) {
		this.spawnRate = spawnRate;
		this.spawnthread = new Thread(new SpawnThread());
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

	public boolean isRightSide() {
		return isRightSide;
	}

	public void setRightSide(boolean isRightSide) {
		this.isRightSide = isRightSide;
	}

	
	
	
}
