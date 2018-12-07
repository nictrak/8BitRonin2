package Logic;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Platform;
public class WingedSpawner {
	
	private double spawnRate;
	private Thread spawnthread;
	private Hero target;
	private Vector2D spawnPos;
	private boolean isSpawn;

	
	public Vector2D getSpawnPos() {
		return spawnPos;
	}

	public void setSpawnPos(Vector2D spawnPos) {
		this.spawnPos = spawnPos;
	}

	private class SpawnThread implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				double randomPos = Math.random() * 667 + 1;
				int randomSide = (int)(Math.random() * 2 + 1);
				spawnPos = new Vector2D(0,randomPos);
				if(randomSide == 1) {
					spawnPos.setX(-10);
				}else {
					spawnPos.setX(1376);
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
	
	public WingedSpawner(double spawnRate,Pane pane,Hero target) {
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

	public boolean isSpawn() {
		return isSpawn;
	}

	public void setSpawn(boolean isSpawn) {
		this.isSpawn = isSpawn;
	}
	
}
