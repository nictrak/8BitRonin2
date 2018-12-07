package Logic;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Platform;
public class WingedSpawner {
	
	private double spawnRate;
	private Thread spawnthread;
	private Pane pane;
	private Hero target;

	
	private class SpawnThread implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				double randomPos = Math.random() * 667 + 1;
				int randomSide = (int)(Math.random() * 2 + 1);
				Vector2D spawnPos = new Vector2D(0,randomPos);
				if(randomSide == 1) {
					spawnPos.setX(-10);
				}else {
					spawnPos.setX(1376);
				}
				WingedGoblin wg = new  WingedGoblin(spawnPos,target);
				Updater u = new Updater(wg,pane);
				Platform.runLater(u);
				MonstersSet.getMonsters().add(wg);
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
		this.pane = pane;
		this.target = target;
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

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}

	public Hero getTarget() {
		return target;
	}

	public void setTarget(Hero target) {
		this.target = target;
	}
	
}