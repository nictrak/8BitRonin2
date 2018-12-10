package Logic;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ScoreBoard extends Label{
	
	private int score;
	private int highScore;
	
	public ScoreBoard(int highScore) {
		this.score = 0;
		this.highScore = highScore;
		this.setTextFill(Color.WHITE);
	}
	
	public void update() {
		this.setText("Score : " + Integer.toString(score) + "  HighScore : " + Integer.toString(highScore));
	}
	
	public void writeHighScore(int newScore) {
		if(newScore > this.highScore) {
			this.highScore = newScore;
		}
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

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	
	
}
