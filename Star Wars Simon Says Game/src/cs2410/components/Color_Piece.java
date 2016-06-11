package cs2410.components;


import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Color_Piece extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public java.awt.Color color;
	public Color_Piece(java.awt.Color color, MouseListener colorListener){
		this.color = color;
		setBackground(color.darker());
		addMouseListener(colorListener);
	}
	public void blink(int speed) {
		this.setBackground(this.getBackground().brighter());
		this.update(this.getGraphics());
		try {
			Thread.sleep(speed);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		this.setBackground(this.getBackground().darker());
		this.update(this.getGraphics());
		try {
			Thread.sleep(speed);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}