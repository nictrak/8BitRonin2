package Logic;

import javafx.scene.layout.Pane;

public class Updater implements Runnable{
	private Entity entity;
	private Pane pane;
	
	public Updater(Entity entity,Pane pane) {
		this.entity = entity;
		this.pane = pane;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		pane.getChildren().add(entity.getGameImage());
	}
}
