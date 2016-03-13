/*
 * TCSS 445 Database Star Search
 * Group 20
 */

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.DBAccess;
import model.User;

/**
 * 
 * @author Antonio Alvillar
 */
public class HomePage {
	private JFrame myFrame;
	private JButton myHome;
	private JButton myLogOutButton;
	private JPanel mySearchPanel;
	private JPanel myLogInPanel;
	private JPanel myLogOutPanel;
	private JPanel myUserPanel;
	private JPanel myHomePanel;
	private ResultsPanel myCenter;
	private JLabel invalid;
	private JTextField mySearchBox;
	private String mySearchedStar;
	private JTextField myUserName;
	private JTextField myPassword;
	private String myUserString;
	private String myPassString;
	private User[] myUsers;
	private User myUser;
	private boolean myLogOn = false;
	
	//maybe delete these fields
	private String newUserName;
	private String newPass;
	
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
		myHomePanel = new JPanel();
		myHomePanel.add(myHome);
		myFrame.add(myHomePanel, BorderLayout.SOUTH);
	}
	
	private void setUpLogOut() {
		myLogOutButton = createButton("Log Out");
		myLogOutPanel = new JPanel();
		myLogOutPanel.add(myLogOutButton);
		myFrame.add(myLogOutPanel, BorderLayout.SOUTH);
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
		JButton newAccount = new JButton("New Account");
		newAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				NewUser.showRegisterDialog(myFrame);
			}
		});
		
		
		myPassword.addActionListener(new LogOnListener());
		JButton enter = new JButton("Enter");
		enter.addActionListener(new LogOnListener());

		myLogInPanel.add(myUserName);
		myLogInPanel.add(myPassword);
		myLogInPanel.add(enter);
		myLogInPanel.add(newAccount);
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
					break;
				case "Log Out": 
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
		} 
//		else if (myLogOn) {
//			setUpLogOut();
//			myFrame.getContentPane().remove(mySearchPanel);
//			myFrame.getContentPane().remove(myLogInPanel);
//			myFrame.getContentPane().remove(myUserPanel);
//			myFrame.getContentPane().remove(myHomePanel);
//			myCenter = null;
//			myCenter = new ResultsPanel(mySearchedStar);
//			myFrame.getContentPane().add(myCenter, BorderLayout.CENTER);
//			myFrame.getContentPane().add(mySearchPanel, BorderLayout.NORTH);
//			myFrame.getContentPane().add(myLogOutPanel, BorderLayout.SOUTH);
//			myFrame.revalidate();
//		} 
		else {
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
				//logon();
				myLogOn = true;
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
		myUserPanel = new JPanel();
		JTextPane theUser = new JTextPane();
		theUser.setEditable(false);
		theUser.setText("Welcome " + myUser.getfName());
		myUserPanel.add(theUser);
		myFrame.getContentPane().remove(myLogInPanel);
		myFrame.getContentPane().add(myUserPanel, BorderLayout.NORTH);
		myFrame.revalidate();
	}

}
