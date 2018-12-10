package Logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class HighScoreController {
	
	private int highScore;
	
	public HighScoreController(int highScore) {
		this.highScore = highScore;
	}
	
	public void writeScore(int score)  {
		if(score > this.highScore) {
			this.highScore = score;
		}
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

}
