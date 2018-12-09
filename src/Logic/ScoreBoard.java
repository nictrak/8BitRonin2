package Logic;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ScoreBoard extends Label{
	
	private int score;
	
	public ScoreBoard() {
		this.score = 0;
	}
	
	public void update() {
		this.setText("Score : " + Integer.toString(score));
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
	
}
