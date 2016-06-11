package cs2410.game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import cs2410.components.Scoreboard;
import cs2410.components.Color_Piece;
import cs2410.components.HighScoreBoard;
import cs2410.components.Menu;

	public class Simon {
		
		JFrame frame;
		Container pane;
		private Menu menu;
		HighScoreBoard hiScoreBoard;
		
		Color_Piece topLeftPanel;
		Color_Piece topRightPanel;
		Color_Piece bottomLeftPanel;
		Color_Piece bottomRightPanel;
		
		JButton startBtn;
		Scoreboard score;
		ColorChangeListener colorListener;
		
		boolean compTurn = true;
		
		private static Random random;
		private Vector<Integer> pattern = new Vector<Integer>();
		private Vector<Integer> userPattern = new Vector<Integer>();
		private Vector<Integer> highScores = new Vector<Integer>();
		private Vector<String> highScoreNames = new Vector<String>();
		
		private ImageIcon highScorePic;
		private ImageIcon historyPic;
		private ImageIcon fett;
		private ImageIcon solo;
		private ImageIcon maul;
		private ImageIcon chewy;
		private ImageIcon c3po;
		
		JLabel one;
		JLabel two;
		JLabel three;
		JLabel four;
		
		Scanner intReader, nameReader;
		Scanner historyReader;
		
		String PlayerAndScore = "";
		String rules = "";
		String about = "";
		
		int playerscore = 0;
		int gamesPlayed = 0;
		int totalScore = 0;	
		int averageScore = 0;
		
		String name;
		private int speed = 500;
		int size = 0;
		
		public int getSpeed(){
			return speed;
		}
		public void setSpeed(int newSpeed){
			speed = newSpeed;
		}
		
		public Simon() {
			
			try{
				nameReader = new Scanner(new FileReader("HighScoreNames.txt"));
				for(int i = 0; i < 10; i ++){
					highScoreNames.add(nameReader.nextLine());
				}
				nameReader.close();
			} catch (Exception e){
				e.printStackTrace();
			}
				try{
					intReader = new Scanner(new FileReader("HighScores.txt"));
					for(int i = 0; i < 10; i ++){
						highScores.add(intReader.nextInt());
					}
					intReader.close();
				} catch (Exception e){
					e.printStackTrace();
				}
				
				try {
					historyReader = new Scanner(new FileReader("History.txt"));
					gamesPlayed = historyReader.nextInt();
					totalScore = historyReader.nextInt();
					historyReader.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				averageScore = totalScore/gamesPlayed;
			
			score = new Scoreboard();
			frame = new JFrame("Simon");
			frame.setSize(600,650);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);	
			
			pane = frame.getContentPane();
			pane.setLayout(new BorderLayout());
			
			JPanel colorPane = new JPanel();
			colorPane.setLayout(new GridLayout(2,2));
			
			highScorePic = new ImageIcon("Stormtrooper1.png", null);
			historyPic = new ImageIcon("LongTimeAgo.png", null);
			fett = new ImageIcon("BobaFett.png", null);
			solo = new ImageIcon("HanSolo.png",null);
			maul = new ImageIcon("Maul.png", null);
			chewy= new ImageIcon("Chewbacca.png", null);
			c3po = new ImageIcon("C3PO.png",null);
			
			one = new JLabel(fett);
			two = new JLabel(solo);
			three = new JLabel(maul);
			four = new JLabel(chewy);
			
			colorListener = new ColorChangeListener();
			startBtn = score.getStartButton();
			startBtn.addActionListener(startListener);
			
			pane.add(score);
			
			topLeftPanel = new Color_Piece(java.awt.Color.BLUE, colorListener);
			topLeftPanel.add(one);
			colorPane.add(topLeftPanel);		
						
			topRightPanel = new Color_Piece(java.awt.Color.GREEN, colorListener);
			topRightPanel.add(two);
			colorPane.add(topRightPanel);		
				
			bottomLeftPanel = new Color_Piece(java.awt.Color.YELLOW, colorListener);
			bottomLeftPanel.add(three);
			colorPane.add(bottomLeftPanel);		
			
			bottomRightPanel = new Color_Piece(java.awt.Color.RED, colorListener);
			bottomRightPanel.add(four);
			colorPane.add(bottomRightPanel);	
			
			pane.add(score,  BorderLayout.NORTH);
			pane.add(colorPane, BorderLayout.CENTER);
			
			menu = new Menu(new menuListener());
			frame.setJMenuBar(menu);
			score.setHighScore(highScores.firstElement());

			frame.setVisible(true);	
			
	}
		public void addToPattern(){
			random = new Random();
			Integer rand = random.nextInt(4)+1;
			pattern.add(rand);
			}
		
		private void blinkAll()
		{
			for(int e = 0; e < pattern.size(); ++e){
				if(pattern.elementAt(e)== 1){
					topRightPanel.blink(this.speed);
				}
				else if (pattern.elementAt(e) == 2){
					topLeftPanel.blink(this.speed);
				}
				else if (pattern.elementAt(e) == 3){
					bottomLeftPanel.blink(this.speed);
				}
				else if (pattern.elementAt(e) == 4){
					bottomRightPanel.blink(this.speed);
				}
			}
		}
		
		public void startGame(){
			new Thread(new Runnable(){
				public void run(){
					compTurn = true;
					score.updateScore();
					score.update(score.getGraphics());
					addToPattern();
					blinkAll();
					compTurn = false;
					}
				}).start();
			}
		
		public void playSound(String wavfile) {
		    try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(wavfile).getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
		}
		

		public void outputTheFiles(){
			outerloop:
				for(int b = 0; b < highScores.size(); ++b){
					if(score.getScore() >= highScores.elementAt(b)){
						name = JOptionPane.showInputDialog(null, "You have been accepted into the Jedi Order!\nEnter your name: ");
						highScores.insertElementAt(score.getScore(), b);
						highScoreNames.insertElementAt(name,  b);
						if(highScores.size() > 10){
							highScores.removeElementAt(10);
							highScoreNames.removeElementAt(10);
							break outerloop;
						}
					}
				}
				
			BufferedWriter outNameFile;
			try {					
				outNameFile = new BufferedWriter(new FileWriter("HighScoreNames.txt"));
				for(int a = 0; a < 10; a++){
					outNameFile.write(highScoreNames.get(a) + "\n");			
					}
					outNameFile.close();
			} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			BufferedWriter outScoreFile;
			try {					
				outScoreFile = new BufferedWriter(new FileWriter("HighScores.txt"));
				for(int a = 0; a < 10; a++){
					outScoreFile.write(highScores.get(a) + "\n");			
					}
					outScoreFile.close();
			} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}		
				playerscore = score.getScore();
				gamesPlayed++;
				totalScore += playerscore;
				averageScore = totalScore/gamesPlayed;
				
			try{
				BufferedWriter historyWriter = new BufferedWriter(new FileWriter("History.txt"));
				historyWriter.write(gamesPlayed+" "+totalScore);
				historyWriter.close();
				
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
				return;
		}
		
		
		
		public void checkAnswer(){
			if(pattern.size() == 0){
				JOptionPane.showMessageDialog(null, "Click Start", "Error", JOptionPane.ERROR_MESSAGE);
				userPattern.clear();
				return;
			}			

			for(int i = 0; i < userPattern.size(); ++i){
				if(pattern.get(i) != userPattern.get(i)){
					score.setRanking();
					if(score.getRanking() == "Much to learn, you still have Padawan"){
	
					}
					if(score.getScore() < score.getHighScore()){
					JOptionPane.showMessageDialog(null,  "You Lose...\n" + "You scored: " 
						+ score.getScore() + "\n" + score.getRanking() + "\n\nWant to play again? Hit Start!",
						"Game Over!", JOptionPane.ERROR_MESSAGE);
					}
					if(score.getScore() == score.getHighScore()){
						JOptionPane.showMessageDialog(null, "You Lose...\n" + "You scored: " 
								+ score.getScore() + "\n" + "You tied the High Score!\n" + score.getRanking() 
								+ "\n\nWant to play again? Hit Start!", "Game Over",
								JOptionPane.INFORMATION_MESSAGE);
					}
					if(score.getScore() > score.getHighScore()){
						JOptionPane.showMessageDialog(null, "You Lose...\n" + "You scored: " 
								+ score.getScore() + "\n" + "New High Score!\n" + score.getRanking() 
								+ "\n\nWant to play again? Hit Start!", "Game Over",
								JOptionPane.INFORMATION_MESSAGE);
						score.updateHighScore();
					}
					outputTheFiles();
					playSound("throneroom.wav");
					pattern.clear();
					userPattern.clear();
					score.setScore(0);
					
					return;
				}
			}
			if(pattern.size() == userPattern.size()){
				frame.update(frame.getGraphics());
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e1) {						
					e1.printStackTrace();
				}
				
				userPattern.clear();
				startGame();
				}
		}		
		
		class ColorChangeListener implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(compTurn == true){
					return;
				}
				else{
				JPanel color = (JPanel) e.getSource();
				((JPanel)e.getSource()).setBackground(color.getBackground().brighter());
				if(color == topRightPanel){
					playSound("blaster1.wav");
				}
				if(color == topLeftPanel){
					playSound("blaster2.wav");
				}
				if(color == bottomLeftPanel){
					playSound("lightsaberSound.wav");
				}
				if(color == bottomRightPanel){
					playSound("chewy_roar.wav");
				}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(compTurn == true){
					return;
				}
				else{
				JPanel color = (JPanel) e.getSource();
				((JPanel)e.getSource()).setBackground(color.getBackground().darker());
				
				if(color == topRightPanel){
					userPattern.add(1);
				}
				if(color == topLeftPanel){
					userPattern.add(2);
				}
				if(color == bottomLeftPanel){
					userPattern.add(3);
				}
				if(color == bottomRightPanel){
					userPattern.add(4);
				}
				checkAnswer();				
				}	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}	
		}
		
		ActionListener startListener = new ActionListener()	{
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == score.getStartButton())
				{
					score.reset();
					pattern.clear();
					startGame();
				}
			}
		};
		
		public class menuListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == menu.getHighScore()){
					playSound("swtheme.wav");
					for(int i = 0; i < 10; i++){
						PlayerAndScore += highScoreNames.get(i) + " " + highScores.get(i) + "\n";
					}
					 UIManager UI=new UIManager();
					 UI.put("OptionPane.background", Color.BLACK);
					JOptionPane.showMessageDialog(null, "Leading members of the Jedi Council:  \n"
							 + PlayerAndScore, "High Scores", JOptionPane.INFORMATION_MESSAGE, highScorePic);
					PlayerAndScore = "";
				}
				
				if(e.getSource() == menu.getHistory()){
					//playSound("");
				String history = "Number of games played: "+ gamesPlayed+"\n"
						+ "Running average score: "+ averageScore +"\n";
				JOptionPane.showMessageDialog(null,history,"History",JOptionPane.INFORMATION_MESSAGE, historyPic);
				}
				
				if(e.getSource() == menu.getAbout()){
					playSound("technicl.wav");
					about = "This program was originally just a plain old Simon Game.\n"
							+ "Since then, and 30 hours later, this is the new and improved game!\n"
							+ "The game now has a menu bar that can display high scores and history,\n"
							+ "options to change the colors and speed of the game play.\n"
							+ "And just for your nerdy pleasure, it is Star Wars Themed!\n"
							+ "Hope you enjoy...\n";
					JOptionPane.showMessageDialog(null,about,"About",JOptionPane.INFORMATION_MESSAGE, c3po);
				}
				if(e.getSource() == menu.getTopleft()){
					Color topLeft = JColorChooser.showDialog(null,  "Top Left Color",  Color.yellow );
					topLeftPanel.color = topLeft;
					topLeftPanel.setBackground(topLeft);
					topLeftPanel.update(topLeftPanel.getGraphics());
				}
				if(e.getSource() == menu.getTopright()){
					Color topRight = JColorChooser.showDialog(null,  "Top Right Color",  Color.red );
					topRightPanel.color = topRight;
					topRightPanel.setBackground(topRight);
					topRightPanel.update(topRightPanel.getGraphics());
				}
				if(e.getSource() == menu.getBottomleft()){
					Color bottomleft = JColorChooser.showDialog(null,  "Bottom Left Color",  Color.blue );
					bottomLeftPanel.color = bottomleft;
					bottomLeftPanel.setBackground(bottomleft);
					bottomLeftPanel.update(bottomLeftPanel.getGraphics());
				}
				if(e.getSource() == menu.getBottomright()){
					Color bottomRight = JColorChooser.showDialog(null,  "Bottom Right Color",  Color.green );
					bottomRightPanel.color = bottomRight;
					bottomRightPanel.setBackground(bottomRight);
					bottomRightPanel.update(bottomRightPanel.getGraphics());
				}
				if(e.getSource() == menu.getRules()){
					playSound("newstrat.wav");
					rules = "Here are the rules:\n"
							+"1. Press Start and wait for a button to flash.\n"
							+"2. Click the same buttons that flashed, in the same order\n"
							+"3. Continue to memorize the flashing sequence and mimic it.\n"
							+"4. You get one point for each successful round you complete!\n"
							+"5. If you make onto the High Score Board,\n"
							+"   enter your name and become the stuff of legends.\n";
					JOptionPane.showMessageDialog(null,rules,"Rules",JOptionPane.INFORMATION_MESSAGE, c3po);
				
				}
				if(e.getSource() == menu.getBeginner()){
					playSound("Muchfear.wav");
					setSpeed(1000);
				}
				else if(e.getSource() == menu.getNormal()){
					setSpeed(500);
				}
				else if(e.getSource() == menu.getHard()){
					playSound("taught_u_well.wav");
					setSpeed(250);
				}
				else if(e.getSource() == menu.getPro()){
					playSound("swsidious01.wav");
				}
					setSpeed(100);
				}
			
		};
		
	

		public static void main(String[] args) {
			new Simon();
		}
	}
