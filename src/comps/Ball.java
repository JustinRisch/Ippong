package comps;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;

import JBreak.JBreak;

import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class Ball extends JRadioButton implements Runnable {

	private int x=50, y=150, width = 23, height = 20;
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

		//Testing collision
		if (tempx<0 || tempx>427){
			right=!right; 
			if (right)
				tempx=x+(int)(xspeed*speed); //re-decide new direction.
			else 
				tempx=x-(int)(xspeed*speed);
		}
		if (tempy<0) {
			up=!up;
			if (up)
				tempy=y-(int)(yspeed*speed);
			else
				tempy=y+(int)(yspeed*speed);

		} else if (tempy>250){
			speed =0;
			JBreak.gameOver=true;
			JBreak.endScreen.setText("Game Over! Your score was "+JBreak.score+"!");
			JBreak.endScreen.revalidate();
			JBreak.endScreen.repaint();
			return;
		}
		
		//testing each brick at once to see if the ball collided with any of them. 
		JBreak.brick.parallelStream().filter(brick ->  	
		ballrect.getBounds().intersects(brick.getBounds()))						
		.forEach(e -> {						//for every ball that collided,
				e.setLocation(-50, -50); 	//move it off screen.
				right=!right; 				//flip the direction of the ball
				up=!up;						//on both axis'
				speed+=.5;					//increase the speed of the ball
				Paddle.speed+=.5;			//increase the speed of the paddle
				JBreak.score++;				//increment the score. 
			});

			if (JBreak.score>14) {
				JBreak.endScreen.setText("You beat the game?!?! Enjoy your prize.");
				JBreak.gameOver=true;
				return;
			}
		
		//JBreak.contentPane.getComponentAt(tempx, tempy).setLocation(-50, -50);

		x=tempx;
		y=tempy;
		this.setBounds(x, y, width, height);
		this.revalidate();
		this.repaint();

	}

	public Ball(Insets insets, Dimension size){
		this.setVisible(true);
		this.setEnabled(false);
		this.setSelected(true);
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
