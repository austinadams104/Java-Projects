package cs2410.components;

import java.util.Vector;

import javax.swing.JOptionPane;

public class HighScoreBoard {
String winner;
int highscore;
Scoreboard scoreboard;


	public HighScoreBoard(String name, int highscore){
		super();
		winner = name;
		this.highscore = highscore;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}






}
