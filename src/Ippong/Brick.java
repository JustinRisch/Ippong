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
	public Brick(int x, int y, int width, int height){
		this.x=x; 
		this.y=y;
		this.setBounds(x, y, width, height);
		this.setText("[==]");
	}

	public void move() {
		this.setLocation((this.getX()+2)%Ippong.contentPane.getWidth(), y);
		
	}
}
