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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.DBAccess;
import model.Favorite;
import model.Star;
import model.User;

/**
 * This class will construct and maintain all of the needed GUI that will be displayed 
 * during the use of our database project. This is accomplished by the use of panels 
 * swapping around the JFrame. 
 * 
 * @author Antonio Alvillar
 */
public class HomePage {
	
	/**
	 * Field to maintain the JFrame. 
	 */
	private JFrame myFrame;
	
	/**
	 * Stores a favorite object (a star).
	 */
	private Favorite myFavoriteStar;
	
	/**
	 * Panel to display the users favorites. 
	 */
	private FavoritesPanel myFavoritesPanel;
	
	/**
	 * Stores the home button.
	 */
	private JButton myHome;
	
	/**
	 * stores the search button.
	 */
	private JButton mySearch;
	
	/**
	 * stores the favorite button. 
	 */
	private JButton myFavoriteButton;
	
	/**
	 * stores the favorites button.
	 */
	private JButton myFavorites;
	
	/**
	 * stores the new account button.
	 */
	private JButton myNewAccount;
	
	/**
	 * stores the log out button.
	 */
	private JButton myLogOutButton;
	
	/**
	 * stores the enter button.
	 */
	private JButton myEnter;
	
	/**
	 * Search panel to contain all search functionality.
	 */
	private JPanel mySearchPanel;
	
	/**
	 * Log in panel to contain all the log in functionality.
	 */
	private JPanel myLogInPanel;
	
	/**
	 * Log out panel to contain all the log out functionality.
	 */
	private JPanel myLogOutPanel;
	
	/**
	 * User panel to display the users name and welcome. 
	 */
	private JPanel myUserPanel;
	
	/**
	 * Home panel to contain the return to home button.
	 */
	private JPanel myHomePanel;
	
	/**
	 * Results Panel to display the search results. 
	 */
	private ResultsPanel myCenter;
	
	/**
	 * Label to show that the password of user name was invalid.
	 */
	private JLabel myInvalid;
	
	/**
	 * search box used to store the searched star.
	 */
	private JTextField mySearchBox;
	
	/**
	 * store the searched star string to be used. 
	 */
	private String mySearchedStar;
	
	/**
	 * Text field to retrieve a username for log in.
	 */
	private JTextField myUserName;
	
	/**
	 * Text field to retrieve a password for log in.
	 */
	private JTextField myPassword;
	
	/**
	 * store the username as a string. 
	 */
	private String myUserString;
	
	/**
	 * store the password as a string.
	 */
	private String myPassString;
	
	/**
	 * an array of users to review a single user. 
	 */
	private User[] myUsers;
	
	/**
	 * a single user to use while logged in.
	 */
	private User myUser;
	
	/**
	 * boolean check to see if someone is logged in.
	 */
	private boolean myLogOn = false;
	
	/**
	 * Constructor for the entire GUI. Will create the frame and
	 * switch all the components around for the initial searching. 
	 */
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
	
	/**
	 * Will create a panel that contains a return to home 
	 * button. Set to not enabled at first. 
	 * @return A JPanel to be applied to the JFrame.
	 */
	private JPanel setUpHomePanel() {
		myHome = createButton("Return to Star Search Home");
		myHome.setEnabled(false);
		myHomePanel = new JPanel();
		myHomePanel.add(myHome);
		return myHomePanel;
	}
	
	/**
	 * Will create a panel that contains a needed search button
	 * and text field to be used. 
	 * @return A JPanel that can be applied to the JFrame.
	 */
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
	
	/**
	 * Will create a panel that contains the needed text fields
	 * and buttons for someone to log into the project. Listeners will 
	 * also be added. 
	 * @return A Panel that can be applied to the JFrame.
	 */
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
	
	/**
	 * Will create a log out panel that contains. Log out buttons and 
	 * as well as display favorites or add to favorites.
	 * @return A panel that can be applied to the JFrame.
	 */
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
	
	/**
	 * Create a button with a passed in string to be put 
	 * as the button name. This will also add a listener. 
	 * @param The name of the button.
	 * @return A button that can be added to a panel.
	 */
	private JButton createButton(String string) {
		JButton button = new JButton(string);
		button.addActionListener(new HomeListener());
		return button;
	}
	
	/**
	 * Will check if someone logging in is already a user. 
	 * @return True if someone is a user already.
	 */
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
	
	/**
	 * Will swap around panels to display a view appropriate for an
	 * established user. Added some functionality such as favorites. 
	 */
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
	
	/**
	 * Will search the database for a particular star and display the 
	 * results or a message if no star is found. This will run differently
	 * if the person searching is logged in or not. Also if logged in then 
	 * the person may be able to add a star as a favorite. 
	 */
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
				myFavoriteStar = new Favorite(myUser.getUserID(), savedStar[0].getStarID(), 0, "Default");
			}
			myFavorites.setEnabled(true);
			myFrame.getContentPane().revalidate();
			myFrame.getContentPane().repaint();
		}
	}
	
	/**
	 * Private inner listener class for many of the buttons on the 
	 * panels as well as JFrames. A switch statement handles the different
	 * functions needed depending on the button event. 
	 * @author Antonio V. Alvillar
	 */
	private class HomeListener implements ActionListener {
		public void actionPerformed(ActionEvent theEvent) {
			switch (theEvent.getActionCommand()) {
				case "New Account":
					NewUser.showRegisterDialog(myFrame);
					break;
				case "Display Favorites":
					myFrame.getContentPane().removeAll();
					myFrame.setLayout(new BorderLayout());
					myFavoritesPanel = new FavoritesPanel(myUser);
					myFrame.add(setUpLogOutPanel(), BorderLayout.SOUTH);
					myFavoriteButton.setEnabled(false);
					myFavorites.setEnabled(false);
					myFrame.add(myFavoritesPanel, BorderLayout.CENTER);
					myFrame.getContentPane().revalidate();
					myFrame.getContentPane().repaint();
					break;
				case "Add to Favorites":
					DBAccess.addFavorite(myFavoriteStar);
					JOptionPane.showMessageDialog(null, "Star added to your favorites: Display Favorites to view");
					break;
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
					myLogOn = false;
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
	
	/**
	 * Private inner listener class for a user logging on. 
	 * @author Gabriel Houle
	 */
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
