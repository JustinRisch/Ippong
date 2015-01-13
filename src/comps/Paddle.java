package comps;

import java.awt.Dimension;


@SuppressWarnings("serial")
public class Paddle extends Brick {
	public static double speed = 12; 
	Dimension size = new Dimension(38,20);
	public Paddle(){
		x=100;
		y=250;
		this.setText("(===)");
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
