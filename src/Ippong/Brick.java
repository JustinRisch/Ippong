package Ippong;

import java.awt.Dimension;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Brick extends JLabel{
	protected int x=50, y=50;
	public Dimension size = new Dimension(28,15);
	public Brick(){
		this.setText("[==]");
	}
}
