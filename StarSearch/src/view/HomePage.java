package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.User;

public class HomePage {
	private JFrame myFrame;
	private JPanel mySearchPanel;
	private JPanel myLogInPanel;
	private JLabel invalid;
	private JTextField mySearchBox;
	private JTextField myUserName;
	private JTextField myPassword;
	private String myUserString;
	private String myPassString;
	private ArrayList<User> myUsers = new ArrayList<User>();
	
	public HomePage() {
		myFrame = new JFrame("Star Search Database");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setMinimumSize(new Dimension(500, 500));
		myFrame.setResizable(false);
		User aUser = new User();
		aUser.setUser("bob1");
		aUser.setPassword("1234");
		myUsers.add(aUser);
		//setImages();
		logInPanel();
		searchPanel();
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
	}
	
	private void setImages() {
		URL icon = HomePage.class.getResource("/golden_star.png");
		ImageIcon frameIcon = new ImageIcon(icon);
		myFrame.setIconImage(frameIcon.getImage());	
		URL background = HomePage.class.getResource("/night_sky.jpg");
		ImageIcon backgroundIcon = new ImageIcon(background);
		JLabel backgroundImage = new JLabel(backgroundIcon);
		myFrame.add(backgroundImage); 
		myFrame.pack();
	}
	
	private void searchPanel() {
		mySearchPanel = new JPanel();
		mySearchPanel.setLayout(new GridBagLayout());
		mySearchBox = new JTextField(15);
		JButton search = createButton("Search");
		mySearchPanel.add(mySearchBox);
		mySearchPanel.add(search);
		JButton advancedSearch = new JButton("Advanced Search");
		mySearchPanel.add(advancedSearch);
		myFrame.add(mySearchPanel, BorderLayout.CENTER);
	}

	private JButton createButton(String string) {
		JButton button = new JButton(string);
		button.addActionListener(new HomeListener());
		return button;
	}
	
	private void logInPanel() {
		myLogInPanel = new JPanel();
		myLogInPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		myUserName = new JTextField(6);
		myUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent theEvent) {
				if (theEvent.getKeyCode() != KeyEvent.VK_ENTER) {
					myUserString = myUserName.getText() + theEvent.getKeyChar();
				}
			}
		});
		myUserName.addActionListener(new LogOnListener());
		myPassword = new JTextField(6);
		myPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent theEvent) {
				if (theEvent.getKeyCode() != KeyEvent.VK_ENTER) {
					myPassString = myPassword.getText() + theEvent.getKeyChar();
				}
			}
		});
		myPassword.addActionListener(new LogOnListener());
		JButton enter = new JButton("Enter");
		enter.addActionListener(new LogOnListener());
		myLogInPanel.add(myUserName);
		myLogInPanel.add(myPassword);
		myLogInPanel.add(enter);
		invalid = new JLabel("");
		invalid.setForeground(Color.RED);
		myLogInPanel.add(invalid);
		myFrame.add(myLogInPanel, BorderLayout.NORTH);
	}

	private class HomeListener implements ActionListener {
		public void actionPerformed(ActionEvent theEvent) {
			switch (theEvent.getActionCommand()) {
				case "Search":
					System.out.println("Search Pressed");
					break;
			}
		}
	}
	
	private class LogOnListener implements ActionListener {
		public void actionPerformed(ActionEvent theEvent) {
			if(verifyUser()) {
				logon();
			} else {
				invalid.setText("Invalid Login Information!");
			}
		}
	}
	
	private boolean verifyUser() {
		boolean result = false;//set this to false in order to actually verify.
		
		if (myUsers.get(0).isUser(myUserString, myPassString)) {
			result = true;
		}
		return result;
	}
	
	private void logon() {
		JOptionPane.showMessageDialog(myLogInPanel, "YOU SIGNED IN!");
	}

}
