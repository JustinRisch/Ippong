package comps;

import java.awt.Desktop;
import java.awt.Dimension;

import java.awt.Insets;
import java.awt.Rectangle;
import java.net.URI;

import JBreak.JBreak;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Ball extends JLabel implements Runnable {

	// making a singleton to avoid waste! :D 
	private int x=50, y=150, width = 50, height = 60;
	private double speed = 4;
	private double xspeed=1, yspeed=1; 
	private boolean up=false, right=true; 

	public void move(Rectangle r){
		if (JBreak.gameOver)
			return;
		Rectangle ballrect = this.getBounds();
	
		if (ballrect.intersects(r))
		{  
			up = !up; 
			Rectangle temp = ballrect.intersection(r);
			Double centerBall= temp.getCenterX(), centerPaddle = r.getCenterX(); 
			if (centerBall > (centerPaddle))
				right=true;
			else 
				right=false;
		}
		int tempx, tempy;
		if (right)
			tempx=x+(int)(xspeed*speed);
		else 
			tempx=x-(int)(xspeed*speed);

		if (up)
			tempy=y-(int)(yspeed*speed);
		else
			tempy=y+(int)(yspeed*speed);


		for (Brick[] b : JBreak.brick)
			for (Brick brick : b)
			{
				if (this.getBounds().intersects(brick.getBounds()))
				{
					right=!right; 
					up=!up;
					y+=10;
					x+=5;
					speed+=.5;
					Paddle.speed+=.5;
					//brick.setVisible(false);
					brick.setLocation(-50, -50);
					JBreak.score++;
					if (JBreak.score>14) {
						JBreak.endScreen.setText("You beat the game?!?! Enjoy your prize.");
						JBreak.gameOver=true;
						boolean start = Desktop.isDesktopSupported(); 
						if (start) {
							Desktop desk = Desktop.getDesktop();
							try { 
							URI uri = new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
							Thread.sleep(3000);
							desk.browse(uri);
							}catch(Exception e){}
						}
						return;
					}
				}
			}
		if (tempx<0 || tempx>427){
			right=!right;
			return;
		}
		if (tempy<0) {
			up=!up;
			return;
		} else if (tempy>250){
			speed =0;
			JBreak.gameOver=true;
			JBreak.endScreen.setText("Game Over! Your score was "+JBreak.score+"!");
			JBreak.endScreen.revalidate();
			JBreak.endScreen.repaint();
			return;
		}
		x=tempx;
		y=tempy;
		this.setBounds(x, y, width, height);
		this.revalidate();
		this.repaint();

	}

	public Ball(Insets insets, Dimension size){
		this.setVisible(true);
		this.setEnabled(false);
		ImageIcon icon =new ImageIcon("images/ball.gif");
		this.setIcon(icon);
		//this.setSelected(true);
		this.revalidate();
		this.repaint();
		this.setBounds(x+insets.left, y+insets.top, width, height);

	}
	
	@Override
	public void run() {
		while (true){
			try{
				this.move(JBreak.paddle.getBounds());
				Thread.sleep(60);
			} catch(Exception e){}
		}

	}

}
