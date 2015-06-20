package eventsAndGUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//Welcome window class 
public class WelcomeWindow extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JButton enterButton = new JButton("Enter");
	private JButton exitButton = new JButton("Exit");
	private Font customFont1 = new Font("Courier", Font.BOLD, 55);
	private Font customFont2 = new Font("Sans Serif", Font.BOLD, 75);
	private JLabel header1 = new JLabel("Auto Company");
	private JLabel header2 = new JLabel("Management System");
	
	public WelcomeWindow()
	{
		setTitle("Auto Trader Welcome Window");
		setSize(1365, 810);
		setResizable(false);
	    setContentPane(new JLabel(new ImageIcon("images/AutoBackground.jpg")));
	    setLayout(null);
	   
	    header1.setSize(600, 250);
	    header1.setLocation(400, 50);
	    header1.setForeground(Color.white);
	    header1.setFont(customFont2);
	    
	    header2.setSize(800, 250);
	    header2.setLocation(280, 150);
	    header2.setForeground(Color.white);
	    header2.setFont(customFont2);
	    
	    enterButton.setSize(230, 75);
	    enterButton.setLocation(210, 550);
	    enterButton.setFont(customFont1);
	    enterButton.setForeground(Color.blue);
	    enterButton.addActionListener(this);
	    
	    exitButton.setSize(230, 75);
	    exitButton.setLocation(890, 550);
	    exitButton.setFont(customFont1);
	    exitButton.setForeground(Color.blue);
	    exitButton.addActionListener(this);
	    
	    add(header1);
	    add(header2);
	    add(enterButton);
	    add(exitButton);
	    
	    setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Enter"))
		{
			MainMenuWindow mainMenu = new MainMenuWindow();
			this.setVisible(false);
			mainMenu.setVisible(true);
		}
		else if(e.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
	}
}
