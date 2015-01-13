package JBreak;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comps.*;


@SuppressWarnings("serial")
public class JBreak extends JFrame {
	public static boolean gameOver=false;
	public static final JLabel endScreen = new JLabel();
	public static int score=0;
	private static JPanel contentPane = new JPanel();
	private static Insets insets = contentPane.getInsets();
	private static Dimension size = contentPane.getSize(); 
	private static Ball ball = new Ball(insets, size);;
	private static JBreak frame;
	public static Paddle paddle = new Paddle();
	public static Brick[][] brick = new Brick[5][3];
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JBreak();
					frame.setVisible(true); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


	}

	public JBreak() {
		this.setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setTitle("Play Ippong! Try to break all the blocks.");
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.add(ball);
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
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

			}
		});
		contentPane.add(paddle);
		for (int i = 0; i < brick.length; i++)
			for (int j = 0; j<brick[i].length; j++)
			{
				brick[i][j] = new Brick();
				brick[i][j].setBounds( i*90 + (45 * (j%2)), j*10, brick[i][j].size.width, brick[i][j].size.height);
				
				contentPane.add(brick[i][j]);
			}

		endScreen.setBounds(5,25, 450,50);
		endScreen.setText("Can you break all the bricks? Press space to see!");
		
		contentPane.add(endScreen);
	}
}
