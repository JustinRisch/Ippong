package Ippong;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Paddle extends JLabel {
	public double speed = 20;
	private int x, y;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Paddle() {
		x = 100;
		y = 265;
		this.setText("(Ippon)");
		this.setVisible(true);
		this.setBounds(x, y, 44, 20);

	}

	public double hitWall() {
		return this.getWidth()
				- (this.getBounds().intersection(Ippong.jta.getBounds()))
						.getWidth();
	}

	public void move(double i) {
		double temp = x + (speed * i);
		x = (int) temp;

		if (x > Ippong.jta.getWidth() - this.getWidth())
			x = 0;
		else if (x < 0) {
			x = Ippong.jta.getWidth() - this.getWidth();
		}
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		this.revalidate();
		this.repaint();
	}

}
