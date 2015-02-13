package Ippong;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Ippong extends JFrame {
	public static boolean gameOver = true;
	public static final JLabel endScreen = new JLabel();
	public static int score = 0;
	public static Ippong frame;
	public static Paddle paddle;
	public static ArrayList<Brick> bricks;
	public static JTextArea jta;
	public static Menu menubar;
	public static JPanel contentPane;

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			try {
				frame = new Ippong();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	interface ControlListener extends KeyListener {
		// defaulting out the other methods so you can use lambdas
		default void keyTyped(KeyEvent e) {
		}

		default void keyReleased(KeyEvent e) {
		}

	}

	public Ippong() {
		contentPane = new JPanel();
		paddle = new Paddle();
		bricks = new ArrayList<Brick>();
		jta = new JTextArea();
		jta.setBounds(0, 15, 450, 315);
		jta.setVisible(false);

		Ball ball = new Ball();
		this.setResizable(false);
		contentPane.setBorder(new EmptyBorder(15, 5, 5, 5));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(90, 90, 450, 315);

		menubar = new Menu(ball, bricks, paddle, contentPane);

		this.setTitle("Play Ippong! Try to break all the blocks.");
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.add(jta);
		contentPane.add(ball);
		contentPane.add(menubar);
		ControlListener kl = (e) -> { // WOO LAMBDAS!
			switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				if (!gameOver)
					paddle.move(1);
				break;
			case KeyEvent.VK_LEFT:
				if (!gameOver)
					paddle.move(-1);
				break;
			case KeyEvent.VK_SPACE:
				if (gameOver) {
					endScreen.setText("");
					try {
						Thread.sleep(50);
					} catch (Exception err) {
					}
					Thread t = new Thread(ball);
					t.start();
					gameOver = false;
				}
			}
		};

		this.addKeyListener(kl);
		contentPane.add(paddle);
		int j = 0;
		int i = 0;
		for (int x = 0; x < 15; x++) {
			bricks.add((new Brick((i * 90) + (45 * (j % 2)), j * 30 + 25, 28,
					18)));
			contentPane.add(bricks.get(x));
			i++;
			if (i == 5) {
				i %= 5;
				j++;
			}
		}

		endScreen.setBounds(5, 50, 450, 50);
		endScreen.setText("Can you break all the bricks? Press space to see!");
		contentPane.add(endScreen);
	}
}
