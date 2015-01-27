package Ippong;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class Ippong extends JFrame {
	public static boolean gameOver=false;
	public static final JLabel endScreen = new JLabel();
	public static int score=0;
	public static JPanel contentPane;
	private static Insets insets;
	private static Dimension size;
	private static Ball ball;
	private static Ippong frame;
	public static Paddle paddle;
	public static ArrayList<Brick> bricks;

	public static void main(String[] args) {
		EventQueue.invokeLater(()-> {
			try {
				contentPane=new JPanel();
				insets= contentPane.getInsets();
				size = contentPane.getSize();
				paddle = new Paddle();
				bricks = new ArrayList<Brick>();
				ball = new Ball(insets, size);
				frame = new Ippong();
				frame.setVisible(true); 
				ControlListener c2 = (e)->{
					switch (e.getKeyCode()){
					case KeyEvent.VK_ENTER:
						if (!gameOver)
							break;
						gameOver=false;
						frame.setVisible(false);
						frame.removeAll();
						frame=null;
						System.gc();
						Ippong.main("".split(""));
						break;
					}};
				frame.addKeyListener(c2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});


	}
	interface ControlListener extends KeyListener{
		//defaulting out the other methods so you can use lambdas 
		default void keyTyped(KeyEvent e){}
		default void keyReleased(KeyEvent e){}

	}
	public Ippong() {
		this.setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setTitle("Play Ippong! Try to break all the blocks.");
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.add(ball);

		ControlListener kl = (e)->{  // WOO LAMBDAS!
			if (!gameOver)
				switch (e.getKeyCode()){
				case KeyEvent.VK_RIGHT:
					paddle.move(1);
					break;
				case KeyEvent.VK_LEFT:
					paddle.move(-1);
					break;
				case KeyEvent.VK_SPACE:
					endScreen.setText("");
					try{
						Thread.sleep(50);
					} catch(Exception err){}
					Thread t = new Thread(ball);
					t.start();
				}
		};

		this.addKeyListener(kl);
		contentPane.add(paddle);
		int j=0; int i=0;
		for (int x=0; x< 15; x++) 
		{
			bricks.add((new Brick()));
			bricks.get(x)
			.setBounds( i*90 + (45 * (j%2)), j*10, bricks.get(x).size.width,bricks.get(x).size.height);
			contentPane.add(bricks.get(x));
			i++;
			if (i==5) { 
				i%=5;
				j++;
			}
		}

		endScreen.setBounds(5,25, 450,50);
		endScreen.setText("Can you break all the bricks? Press space to see!");
		contentPane.add(endScreen);
	}
}
