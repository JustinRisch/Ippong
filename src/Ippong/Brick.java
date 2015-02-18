package Ippong;

import java.awt.Dimension;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Brick extends JLabel {
	protected int x = 50, y = 50, right = 1;
	public Dimension size = new Dimension(28, 15);

	public Brick(int i) {
		this.setText("[==]");
		right = i;
	}

	public void isHit() {
		Ippong.jta.remove(this);
	}

	public Brick(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.setBounds(x, y, width, height);
		this.setText("[==]");
	}

	public void move() {
		x += right;
		if (x > Ippong.contentPane.getWidth() - this.getWidth())
			x = 0;
		else if (x < 0)
			x = Ippong.contentPane.getWidth() - this.getWidth();
		this.setLocation(x, y);
	}
}
