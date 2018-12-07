package Logic;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Platform;

public class PlatformSpawner {
	
	private double spawnRate;
	private Thread spawnthread;
	private Pane pane;
	private Hero target;
	private ArrayList<Plat> plats;

	
	private class SpawnThread implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				double randomPos = Math.random() * 507 + 100;
				int randomSide = (int)(Math.random() * 2 + 1);
				Vector2D spawnPos = new Vector2D(0,randomPos);
				boolean isRightSide;
				if(randomSide == 1) {
					spawnPos.setX(-10);
					isRightSide = true;
				}else {
					spawnPos.setX(1376);
					isRightSide = false;
				}
				Plat plat = new Plat(spawnPos,isRightSide);
				Updater u = new Updater(plat,pane);
				Platform.runLater(u);
				plats.add(plat);
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
		this.pane = pane;
		this.target = target;
		this.plats = new ArrayList<Plat>();
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

	public ArrayList<Plat> getPlats() {
		return plats;
	}

	public void setPlats(ArrayList<Plat> plats) {
		this.plats = plats;
	}
	
	
}
