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
import model.Star;
import model.User;

/**
 * 
 * @author Antonio Alvillar
 */
public class HomePage {
	private JFrame myFrame;
	//private Favorite myFavorite;
	private JButton myHome;
	private JButton mySearch;
	private JButton myFavoriteButton;
	private JButton myFavorites;
	private JButton myNewAccount;
	private JButton myLogOutButton;
	private JButton myEnter;
	private JPanel mySearchPanel;
	private JPanel myLogInPanel;
	private JPanel myLogOutPanel;
	private JPanel myUserPanel;
	private JPanel myHomePanel;
	private ResultsPanel myCenter;
	private JLabel myInvalid;
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
		myFrame.add(setUpLogInPanel(), BorderLayout.NORTH);
		myFrame.add(setUpHomePanel(), BorderLayout.SOUTH);
		myFrame.add(setUpSearchPanel(), BorderLayout.CENTER);
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
	}
	
	private JPanel setUpHomePanel() {
		myHome = createButton("Return to Star Search Home");
		myHome.setEnabled(false);
		myHomePanel = new JPanel();
		myHomePanel.add(myHome);
		//myFrame.add(myHomePanel, BorderLayout.SOUTH);
		return myHomePanel;
	}
	
	private JPanel setUpSearchPanel() {
		mySearch = createButton("Search");
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
		mySearchPanel.add(mySearchBox);
		mySearchPanel.add(mySearch);
		return mySearchPanel;
	}
	
	private JPanel setUpLogInPanel() {
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
		myNewAccount = createButton("New Account");
		myPassword.addActionListener(new LogOnListener());
		myEnter = new JButton("Enter");
		myEnter.addActionListener(new LogOnListener());
		myLogInPanel.add(myUserName);
		myLogInPanel.add(myPassword);
		myLogInPanel.add(myEnter);
		myLogInPanel.add(myNewAccount);
		myInvalid = new JLabel("");
		myInvalid.setForeground(Color.RED);
		myLogInPanel.add(myInvalid);
		return myLogInPanel;
	}
	
	private JPanel setUpLogOutPanel() {
		myLogOutButton = createButton("Log Out");
		myFavoriteButton = createButton("Add to Favorites");
		myFavoriteButton.setEnabled(false);
		myFavorites = createButton("Display Favorites");
		myFavorites.setEnabled(false);
		myLogOutPanel = new JPanel();
		myLogOutPanel.add(myLogOutButton);
		myLogOutPanel.add(myFavoriteButton);
		myLogOutPanel.add(myFavorites);
		return myLogOutPanel;
	}
	
	private JButton createButton(String string) {
		JButton button = new JButton(string);
		button.addActionListener(new HomeListener());
		return button;
	}
	
	private boolean verifyUser() {
		boolean result = false;//set this to false in order to actually verify.
		myUsers = DBAccess.getUsers();
		for (int i = 0; i < myUsers.length && result == false; i++) {
			if (myUsers[i].isUser(myUserString, myPassString)) {
				result = true;
				myUser = myUsers[i];
			} 
		} 
		return result;
	}
	
	private void logon() {
		myLogOn = true;
		myUserPanel = new JPanel();
		JTextPane theUser = new JTextPane();
		theUser.setEditable(false);
		theUser.setText("Welcome  " + myUser.getfName());
		myUserPanel.add(theUser);
		myFrame.getContentPane().removeAll();
		myFrame.setLayout(new BorderLayout());
		myFrame.add(myUserPanel, BorderLayout.NORTH);
		myFrame.add(setUpSearchPanel(), BorderLayout.CENTER);
		myFrame.add(setUpLogOutPanel(), BorderLayout.SOUTH);
		myFavorites.setEnabled(true);
		myFrame.getContentPane().revalidate();
		myFrame.getContentPane().repaint();
	}
	
	private void searchDataBase() {
		if (!myLogOn) {
			myFrame.getContentPane().removeAll();
			myFrame.setLayout(new BorderLayout());
			myCenter = new ResultsPanel(mySearchedStar);
			myFrame.add(setUpSearchPanel(), BorderLayout.NORTH);
			myFrame.add(setUpHomePanel(), BorderLayout.SOUTH);
			myHome.setEnabled(true);
			myFrame.add(myCenter, BorderLayout.CENTER);
			myFrame.getContentPane().revalidate();
			myFrame.getContentPane().repaint();
		} else {
			myFrame.getContentPane().removeAll();
			myFrame.setLayout(new BorderLayout());
			myCenter = new ResultsPanel(mySearchedStar);
			myFrame.add(setUpSearchPanel(), BorderLayout.NORTH);
			myFrame.add(myCenter, BorderLayout.CENTER);
			myFrame.add(setUpLogOutPanel(), BorderLayout.SOUTH);
			Star[] savedStar = DBAccess.searchStar(mySearchedStar);
			if (savedStar.length != 0) {
				myFavoriteButton.setEnabled(true);
				//myFavoriteStar = new Favorite(myUser.getUserID(), savedStar[0].getStarID(), 0, "Default");
			}
			myFavorites.setEnabled(true);
			myFrame.getContentPane().revalidate();
			myFrame.getContentPane().repaint();
		}
	}
	
	
	private class HomeListener implements ActionListener {
		public void actionPerformed(ActionEvent theEvent) {
			switch (theEvent.getActionCommand()) {
				case "New Account":
					NewUser.showRegisterDialog(myFrame);
					break;
				//case "Add to Favorites";
					//DBAccess.addFavorite(favoriteStar);
					//break;
				case "Search":
					searchDataBase();
					break;
				case "Return to Star Search Home":
					myFrame.getContentPane().removeAll();
					myFrame.setLayout(new BorderLayout());
					myFrame.add(setUpLogInPanel(), BorderLayout.NORTH);
					myFrame.add(setUpSearchPanel(), BorderLayout.CENTER);
					myFrame.add(setUpHomePanel(), BorderLayout.SOUTH);
					myFrame.getContentPane().revalidate();
					myFrame.getContentPane().repaint();
					break;
				case "Log Out":
					myFrame.getContentPane().removeAll();
					myFrame.setLayout(new BorderLayout());
					myFrame.add(setUpLogInPanel(), BorderLayout.NORTH);
					myFrame.add(setUpSearchPanel(), BorderLayout.CENTER);
					myFrame.add(setUpHomePanel(), BorderLayout.SOUTH);
					myFrame.getContentPane().revalidate();
					myFrame.getContentPane().repaint();
					break;
			}
		}
	}
	
	private class LogOnListener implements ActionListener {
		public void actionPerformed(ActionEvent theEvent) {
			if(verifyUser()) {
				logon();
			} else {
				myInvalid.setText("Invalid Login Information!");
			}
		}
	}
}
