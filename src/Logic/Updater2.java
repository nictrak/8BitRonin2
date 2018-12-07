package Logic;

import App.GameImage;
import javafx.scene.layout.Pane;

public class Updater2 implements Runnable{
	private GameImage gameImage;
	private Pane pane;
	
	public Updater2(GameImage gameImage,Pane pane) {
		this.gameImage = gameImage;
		this.pane = pane;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		pane.getChildren().add(gameImage);
	}
}
