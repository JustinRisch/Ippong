package Ippong;

import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Paddle extends JLabel{
	public double speed = 20; 
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
	public double hitWall(){
		return this.getWidth()-(this.getBounds().intersection(Ippong.jta.getBounds())).getWidth();
	}
	public void move(double i){
		double temp = x+(speed*i);
		x=(int)temp;
		this.setBounds(x, y, this.getWidth(),this.getHeight());
		double length;
		if ((length = hitWall())>0)
			x=x+(int)(length*-i);
		this.revalidate();
		this.repaint();
	}
}
