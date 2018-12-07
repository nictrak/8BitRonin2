package Logic;

import java.util.ArrayList;

public class MonstersSet {
	private static ArrayList<Monster> monsters;

	public MonstersSet() {
		monsters = new ArrayList<Monster>();
	}
	
	public static ArrayList<Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(ArrayList<Monster> monsters) {
		MonstersSet.monsters = monsters;
	}
}
