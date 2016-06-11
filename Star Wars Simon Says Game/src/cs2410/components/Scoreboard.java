package cs2410.components;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Scoreboard extends JPanel{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel scoreLabel;
	JLabel highScoreLabel;
	static JButton startBtn;
	private int score;
	private int highScore;
	private String ranking = "";

	public Scoreboard() {
		
		score = 0;
		highScore = 0;
		scoreLabel = (new JLabel("Score: " + Integer.toString(score), SwingConstants.CENTER));
		scoreLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		highScoreLabel = (new JLabel("High Score: " + highScore, SwingConstants.CENTER));
		startBtn = new JButton("START");
				
		setScoreLabel(new JLabel("Score: " + Integer.toString(score), SwingConstants.CENTER));
		highScoreLabel = new JLabel("High Score: " + highScore, SwingConstants.CENTER);
		startBtn = new JButton("START");
	
		setBounds(0,0,300,50);
		this.setLayout(new GridLayout(1,3));
		
		add(getScoreLabel());
		add(startBtn);
		add(highScoreLabel);
		setVisible(true);
		
	}
	public void updateScore() {
		scoreLabel.setText("Score: " + score);
		score++;
	}
	
	public void reset(){
		score = 0;
		scoreLabel.setText("Score: " + score);
	}
	
	public void updateHighScore(){
		if(score > highScore){
			highScore = score;
			highScoreLabel.setText("High Score: " + highScore);
		}
	}
	
	public String setRanking(){
		if(score <= 4){
			ranking = "Much to learn, you still have Padawan";
		}
		else if (score > 4 && score < 8){
			ranking = "You are learning well my young Apprentice";
		}
		else if (score >= 8 && score < 14){
			ranking = "I underestimated your power, Jedi Knight";
		}
		else if (score >= 14){
			ranking = "Powerful you have become in the ways of the force, Jedi Master!";
		}
		return ranking;
	}

	public JButton getStartButton(){
		return startBtn;
	}	
	public JLabel getRoundLabel(){
		return getScoreLabel();
	}
	public String getRanking(){
		return ranking;
	}	
	public int getScore(){
		return score;
	}	
	public int getHighScore(){
		return highScore;
	}
	public JLabel getHighScoreLabel() {
		return highScoreLabel;
	}
	public void setHighScoreLabel(JLabel highScoreLabel) {
		this.highScoreLabel = highScoreLabel;
	}
	public void setScore(int score){
		this.score = score;
	}
	public void setHighScore(int highscore){
		this.highScore = highscore;
		highScoreLabel.setText("High Score: " + Integer.toString(highScore));
	}
	public JLabel getScoreLabel() {
		return scoreLabel;
	}
	public void setScoreLabel(JLabel scoreLabel) {
		this.scoreLabel = scoreLabel;
	}
	
}