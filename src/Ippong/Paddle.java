package Ippong;

import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Paddle extends JLabel{
	public static double speed = 15; 
	private int x, y;
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public Paddle(){
		x=100;
		y=265; 
		this.setText("(Ippon)");
		this.setVisible(true);
		this.setBounds(x, y, 44, 20);

	}
	public void move(double i){
		double temp = x+(speed*i);
		if (temp>-5 && temp < 450-this.getWidth()) {
			x=(int)temp;
			this.setBounds(x, y, this.getWidth(),this.getHeight());
			this.revalidate();
			this.repaint();
		}
	}
}
