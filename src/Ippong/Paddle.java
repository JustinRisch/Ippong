package Ippong;

import java.awt.Dimension;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Paddle extends JLabel{
	public static double speed = 12; 
	private int x, y;
	Dimension size = new Dimension(44,20);
	public Paddle(){
		x=100;
		y=250; 
		this.setText("(Ippon)");
		this.setVisible(true);
		this.setBounds(x, y, size.width, size.height);

	}
	public void move(double i){
		double temp = x+(speed*i);
		if (temp>-5 && temp < 450-size.width) {
			x=(int)temp;
			this.setBounds(x, y, size.width, size.height);
			this.revalidate();
			this.repaint();
		}
	}
}
