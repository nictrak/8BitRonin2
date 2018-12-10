package Logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class HighScoreController {
	
	private int highScore;
	private static final String SAVEPATH = "Save/HighScore.txt";
	
	public HighScoreController() {
		InputStream reader = null;
		reader = this.getClass().getClassLoader().getResourceAsStream(SAVEPATH);
		Scanner s = new Scanner(reader).useDelimiter("\\A");
		int result = s.nextInt();
		this.highScore = result;
	}
	
	public int readScore() {
		InputStream reader = null;
		reader = this.getClass().getClassLoader().getResourceAsStream(SAVEPATH);
		Scanner s = new Scanner(reader).useDelimiter("\\A");
		int result = s.nextInt();
		this.highScore = result;
		return this.highScore;
	}
	public void writeScore(int score)  {
		if(score > this.highScore) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(new File(this.getClass().getResource("/"+SAVEPATH).getPath()));
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

	            // Always wrap FileWriter in BufferedWriter.
	        BufferedWriter bufferedWriter = new BufferedWriter(writer);
	        try {
				bufferedWriter.write(Integer.toString(score));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        this.highScore = score;
	        try {
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public static String getSavepath() {
		return SAVEPATH;
	}
	
}
