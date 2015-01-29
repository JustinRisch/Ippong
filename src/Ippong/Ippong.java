package Ippong;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class Ippong extends JFrame {
	public static boolean gameOver=false;
	public static final JLabel endScreen = new JLabel();
	public static int score=0;
	private static Insets insets;
	private static Dimension size;
	public static Ippong frame;
	public static Paddle paddle;
	public static ArrayList<Brick> bricks;
	public static JTextArea jta;
	public static JMenuBar menubar; 
	public JPanel contentPane;

	public JPanel getContentPane(){
		return contentPane;
	}
	public static void main(String[] args) {

		EventQueue.invokeLater(()-> {
			try {
				frame = new Ippong();
				frame.setVisible(true);
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
		contentPane=new JPanel();

		insets= contentPane.getInsets();
		size = contentPane.getSize();
		paddle = new Paddle();
		bricks = new ArrayList<Brick>();
		jta = new JTextArea();
		jta.setBounds(0, 15, 450, 315);
		jta.setVisible(false);

		Ball ball = new Ball(insets, size);
		this.setResizable(false);
		contentPane.setBorder(new EmptyBorder(15, 5, 5, 5));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(90, 90, 450, 315);

		menubar = new JMenuBar();
		JMenu menu = new JMenu();
		menu.setText("Paddles");
		JMenuItem item = new JMenuItem("King of Java");
		item.addActionListener((e)->{
			paddle.setText("");
			ImageIcon icon = new ImageIcon("images/kingofjava.png");
			icon.getIconWidth();
			paddle.setBounds(paddle.getX(), paddle.getY(), icon.getIconWidth(), paddle.getHeight());
			paddle.setIcon(icon);
			paddle.revalidate();
			paddle.repaint();
		});
		menu.add(item);
		item=new JMenuItem("(Ippon)");
		item.addActionListener((e)->{
			contentPane.remove(paddle);
			paddle=new Paddle();
			contentPane.add(paddle);
			contentPane.revalidate();
			contentPane.repaint();
		});
		menu.add(item);
		menubar.add(menu);
		menubar.setBounds(0, 0, 450, 15);
		this.setTitle("Play Ippong! Try to break all the blocks.");
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.add(jta);
		contentPane.add(ball);
		contentPane.add(menubar);
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
			.setBounds( i*90 + (45 * (j%2)), j*10+15, bricks.get(x).size.width,bricks.get(x).size.height);
			contentPane.add(bricks.get(x));
			i++;
			if (i==5) { 
				i%=5;
				j++;
			}
		}

		endScreen.setBounds(5,40, 450,50);
		endScreen.setText("Can you break all the bricks? Press space to see!");
		contentPane.add(endScreen);
	}
}
