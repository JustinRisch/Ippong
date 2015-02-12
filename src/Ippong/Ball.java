package Ippong;

import java.awt.Component;

import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class Ball extends JRadioButton implements Runnable {

	private int x = 50, y = 150, width = 22, height = 20;
	private double speed = 1;
	private double up = 1, right = 1;

	// call this when you hit something.
	public void hitWall() {

		double width = this.getWidth()
				- (this.getBounds().intersection(Ippong.jta.getBounds()))
						.getWidth();
		double height = this.getHeight()
				- (this.getBounds().intersection(Ippong.jta.getBounds()))
						.getHeight();
		if (width > 0) {
			right *= -1;
		}
		if (height > 0) {
			y += height;
			up *= -1;
		}

	}

	public void collide(Component e) {
		// bounce the ball
		double interX = this.getBounds().getCenterX() - e.getBounds().getMinX();
		double paddleX = e.getBounds().getWidth() / 2;
		right = (interX / paddleX) - 1;
		up *= -1;
		if (e.getClass() == Brick.class) { // if it hit a brick...
			e.setLocation(-50, -50);
			speed += .25; // increase the speed of the ball
			Ippong.paddle.speed += .25; // increase the speed of the paddle
			Ippong.score++; // increment the score.

		}
	}

	public boolean isCollidingWith(Component e) {
		return this.getBounds().intersects(e.getBounds());
	}

	public void move() {
		if (Ippong.gameOver) {
			return;
		}
		if (this.isCollidingWith(Ippong.paddle)) {
			collide(Ippong.paddle);
		}
		// Seeing if it's leaving the playing field
		hitWall();// check to see if we hit the wall.
		x = x + (int) (speed * right);
		y = y + (int) (speed * up);
		// right*=-1; //flip direction

		if (y > 265) {
			Ippong.gameOver = true;
			Ippong.endScreen.setText("Game Over! Your score was "
					+ Ippong.score + "!");
			Ippong.endScreen.revalidate();
			Ippong.endScreen.repaint();
			return;
		}
		// COLLISION CODE HERE
		// testing each brick at once to see if the ball collided with any of
		// them.
		Ippong.bricks.parallelStream().filter(brick -> isCollidingWith(brick))
				.forEach(e -> collide(e)); /*
											 * for each that collided, call the
											 * corresponding code
											 */

		if (Ippong.score > 14) {
			Ippong.endScreen
					.setText("You beat the game?!?! Bet you can't do it again.");
			Ippong.gameOver = true;
			return;
		}
		if (Ippong.score >= 10)
			Ippong.bricks.parallelStream().filter(x -> x.getX() >= 0)
					.forEach(x -> x.move());
		;

		this.setBounds(x, y, this.getWidth(), this.getHeight());
		this.revalidate();
		this.repaint();

	}

	public Ball() {
		x = 50;
		y = 150;
		this.setVisible(true);
		this.setEnabled(false);
		this.setSelected(true);
		this.revalidate();
		this.repaint();
		this.setBounds(x, y, width, height);

	}

	@Override
	public void run() {
		while (true) {
			try {
				this.move();
				Thread.sleep(15);
			} catch (Exception e) {
			}
		}

	}

}
