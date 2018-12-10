package Logic;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ScoreBoard extends Label{
	
	private int score;
	private HighScoreController highScoreController;
	
	public ScoreBoard() {
		this.score = 0;
		this.highScoreController = new HighScoreController();
		this.setTextFill(Color.WHITE);
	}
	
	public void update() {
		this.setText("Score : " + Integer.toString(score) + "  HighScore : " + Integer.toString(highScoreController.getHighScore()));
	}
	
	public void addScore(int adder) {
		this.score = this.score + adder;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public HighScoreController getHighScoreController() {
		return highScoreController;
	}

	public void setHighScoreController(HighScoreController highScoreController) {
		this.highScoreController = highScoreController;
	}
	
}
