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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.DBAccess;
import model.User;

public class HomePage {
	private JFrame myFrame;
	private JButton myHome;
	private JPanel mySearchPanel;
	private JPanel myLogInPanel;
	private ResultsPanel myCenter;
	private JPanel myTop;
	private JLabel invalid;
	private JTextField mySearchBox;
	private String mySearchedStar;
	private JTextField myUserName;
	private JTextField myPassword;
	private String myUserString;
	private String myPassString;
	private User[] myUsers;
	private User myUser;
	
	public HomePage() {
		myFrame = new JFrame("Star Search Database");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setMinimumSize(new Dimension(500, 500));
		myFrame.setResizable(false);
		//setImages();
		setUpHome();
		logInPanel();
		searchPanel();
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
	}
	
	private void setUpHome() {
		myHome = createButton("Return to Star Search Home");
		myHome.setEnabled(false);
		JPanel homePanel = new JPanel();
		homePanel.add(myHome);
		myFrame.add(homePanel, BorderLayout.SOUTH);
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
		mySearchBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent theEvent) {
				if (theEvent.getKeyCode() != KeyEvent.VK_ENTER) {
					mySearchedStar = mySearchBox.getText();
				}
			}
		});
		JButton search = createButton("Search");
		//////////mySearchBox.addActionListener(new HomeListener());
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
			public void keyReleased(final KeyEvent theEvent) {
				if (theEvent.getKeyCode() != KeyEvent.VK_ENTER) {
					myUserString = myUserName.getText();
				}
			}
		});
		myUserName.addActionListener(new LogOnListener());
		myPassword = new JTextField(6);
		myPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent theEvent) {
				if (theEvent.getKeyCode() != KeyEvent.VK_ENTER) {
					myPassString = myPassword.getText();
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
					createResultsView();
					break;
				case "Return to Star Search Home": 
					createBackToHome();
			}
		}
	}
	
	private void createResultsView() {
		if (!myHome.isEnabled()) {
			myHome.setEnabled(true);
			myFrame.getContentPane().remove(mySearchPanel);
			myFrame.getContentPane().remove(myLogInPanel);
			myCenter = null;
			myCenter = new ResultsPanel(mySearchedStar);
			myFrame.getContentPane().add(myCenter, BorderLayout.CENTER);
			myFrame.getContentPane().add(mySearchPanel, BorderLayout.NORTH);
			myFrame.revalidate();
		} else {
			myFrame.getContentPane().remove(myCenter);
			myCenter = null;
			myCenter = new ResultsPanel(mySearchedStar);
			myFrame.getContentPane().add(myCenter, BorderLayout.CENTER);
			myFrame.revalidate();
		}

	}
	
	private void createBackToHome() {
		myHome.setEnabled(false);
		myFrame.getContentPane().remove(myCenter);
		myFrame.getContentPane().remove(mySearchPanel);
		myCenter = null;
		mySearchPanel = null;
		logInPanel();
		searchPanel();
		myFrame.revalidate();
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
		myUsers = DBAccess.getUsers();
		for (int i = 0; i < myUsers.length && result == false; i++) {
			if (myUsers[i].isUser(myUserString, myPassString)) {
				result = true;
				myUser = myUsers[0];
			} 
		} 
		return result;
	}
	
	private void logon() {
		JOptionPane.showMessageDialog(myLogInPanel, myUser.getfName() + " " + myUser.getlName() + " " + myUser.getEmail());
	}

}
