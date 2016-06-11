package cs2410.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import cs2410.game.Simon;

public class Menu extends JMenuBar implements ActionListener, ItemListener{
	JMenu stats, settings, help, mode, color;
	private JMenuItem topleft;
	private JMenuItem topright;
	private JMenuItem bottomleft;
	private JMenuItem bottomright;
	private JMenuItem highScore;
	private JMenuItem history;
	private JMenuItem about;
	private JMenuItem rules;
	private JMenuItem beginner;
	private JMenuItem normal;
	private JMenuItem hard;
	private JMenuItem pro;

	Simon simon;

	public Menu(ActionListener menuListener){
		super();
		
		settings = new JMenu("Settings");
		stats = new JMenu("Stats");
		help = new JMenu("Help");
		mode = new JMenu("Mode");
		color = new JMenu("Color");
		
		setTopleft(new JMenuItem("Change Top Left"));
		setTopright(new JMenuItem("Change Top Right"));
		setBottomleft(new JMenuItem("Change Bottom Left"));
		setBottomright(new JMenuItem("Change Bottom Right"));
		highScore = new JMenuItem("High Score");
		history = new JMenuItem("History");
		about = new JMenuItem("About");
		rules = new JMenuItem("Rules");
		beginner = new JMenuItem("Beginner");
		normal =new JMenuItem("Normal");
		hard = new JMenuItem("Hard");
		pro = new JMenuItem("Pro");
		
		add(settings);
		add(stats);
		add(help);
		
		settings.add(color);
		settings.addSeparator();
		settings.add(mode);
		
		stats.add(highScore);
		stats.addSeparator();
		stats.add(history);
			
		color.add(topleft);
		color.addSeparator();
		color.add(topright);
		color.addSeparator();
		color.add(bottomleft);
		color.addSeparator();
		color.add(bottomright);
		
		mode.add(beginner);
		mode.addSeparator();
		mode.add(normal);
		mode.addSeparator();
		mode.add(hard);
		mode.addSeparator();
		mode.add(pro);
		
		help.add(about);
		help.addSeparator();
		help.add(rules);
		
		about.addActionListener(menuListener);
		rules.addActionListener(menuListener);
		
		highScore.addActionListener(menuListener);
		history.addActionListener(menuListener);
		
		beginner.addActionListener(menuListener);
		normal.addActionListener(menuListener);
		hard.addActionListener(menuListener);
		pro.addActionListener(menuListener);
		
		topleft.addActionListener(menuListener);
		topright.addActionListener(menuListener);
		bottomleft.addActionListener(menuListener);
		bottomright.addActionListener(menuListener);
		
	}
	
	
	public JMenuItem getHighScore() {
		return highScore;
	}
	public JMenuItem getHistory() {
		return history;
	}
	public JMenuItem getAbout() {
		return about;
	}
	public void setAbout(JMenuItem about) {
		this.about = about;
	}
	public JMenu getColor() {
		return color;
	}
	public void setColor(JMenu color) {
		this.color = color;
	}
	public JMenuItem getRules() {
		return rules;
	}
	public void setRules(JMenuItem rules) {
		this.rules = rules;
	}
	public JMenuItem getBeginner() {
		return beginner;
	}
	public void setBeginner(JMenuItem beginner) {
		this.beginner = beginner;
	}
	public JMenuItem getNormal() {
		return normal;
	}
	public void setNormal(JMenuItem normal) {
		this.normal = normal;
	}
	public JMenuItem getHard() {
		return hard;
	}
	public void setHard(JMenuItem hard) {
		this.hard = hard;
	}
	public JMenuItem getPro() {
		return pro;
	}
	public void setPro(JMenuItem pro) {
		this.pro = pro;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public JMenuItem getTopleft() {
		return topleft;
	}


	public void setTopleft(JMenuItem topleft) {
		this.topleft = topleft;
	}


	public JMenuItem getTopright() {
		return topright;
	}


	public void setTopright(JMenuItem topright) {
		this.topright = topright;
	}


	public JMenuItem getBottomleft() {
		return bottomleft;
	}


	public void setBottomleft(JMenuItem bottomleft) {
		this.bottomleft = bottomleft;
	}


	public JMenuItem getBottomright() {
		return bottomright;
	}


	public void setBottomright(JMenuItem bottomright) {
		this.bottomright = bottomright;
	}
}