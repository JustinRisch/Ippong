package Ippong;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {

	public Menu(Paddle paddle, JPanel contentPane){
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
			paddle.setIcon(null);
			paddle.setText("(Ippon)");
			paddle.setBounds(paddle.getX(), paddle.getY(), 44, 20);
			contentPane.revalidate();
			contentPane.repaint();
			
		});
		menu.add(item);
		this.add(menu);
		this.setBounds(0, 0, 450, 15);
	}
}
