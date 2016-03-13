/*
 * TCSS 445 Database Star Search
 * Group 20
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.DBAccess;
import model.User;

/**
 * This class will display the register new user dialog box.
 * 
 * @author Gabriel Houle
 */
public class NewUser extends JDialog {
	

	/**
	 * Generated serial number.
	 */
	private static final long serialVersionUID = 263629641612746637L;

	/**
	 * Stores the user input username.
	 */
	private String myUser = "";
	
	/**
	 * Stores the user input password.
	 */
	private String myPass = "";
	
	/**
	 * Stores the user input first name.
	 */
	private String fName = "";
	
	/**
	 * Stores the user input last name.
	 */
	private String lName = "";
	
	/**
	 * Stores the user input email.
	 */
	private String email = "";
	
	/**
	 * Stores the JTextfield for first name.
	 */
	private JTextField nameInput;
	
	/**
	 * Stores the JTextfield for last name.
	 */
	private JTextField lastNameInput;
	
	/**
	 * Stores the JTextfield for email.
	 */
	private JTextField emailInput;
	
	/**
	 * Stores the JTextfield for userName.
	 */
	private JTextField userInput;
	
	/**
	 * Stores the JTextfield for password.
	 */
	private JTextField passInput;
	
	/**
	 * Stores the list of current users retrieved from the database.
	 */
	private User[] myUsers;
	
	/**
	 * Displays the general error message.
	 */
	private JLabel errorMessage;
	
	/**
	 * Displays invalid email information.
	 */
	private JLabel invalidEmail;
	
	private NewUser(JFrame parent) {
		super(parent, "New User Sign Up", true);
		setSize(new Dimension(750, 200));
		setResizable(false);
		setLocationRelativeTo(null);
		setup();
        setVisible(true);
	}
	
	private void setup() {
		this.setLayout(new BorderLayout());
		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel(new GridBagLayout());
		JPanel southPanel = new JPanel();
		
		setupTextInput();
		
		JButton enter = new JButton("Enter");
		enter.addActionListener(new registerListener());
		
		northPanel.add(nameInput);
		northPanel.add(lastNameInput);
		centerPanel.add(emailInput);
		centerPanel.add(invalidEmail);
		southPanel.add(userInput);
		southPanel.add(passInput);
		southPanel.add(enter);
		southPanel.add(errorMessage);
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);


	}
	
	@SuppressWarnings("unused")
	private void setupTextInput() {
		nameInput = new JTextField(10);
		lastNameInput = new JTextField(10);
		GhostText firstName = new GhostText(nameInput, "First Name");
		GhostText lastName = new GhostText(lastNameInput, "Last Name");
		
		errorMessage = new JLabel("");
		invalidEmail = new JLabel("Invalid email!");
		
		errorMessage.setForeground(Color.RED);
		invalidEmail.setForeground(Color.RED);
		invalidEmail.setVisible(false);
		errorMessage.setVisible(true);
		
		emailInput = new JTextField(20);
		GhostText emailGhost = new GhostText(emailInput, "Email");
		
		userInput = new JTextField(15);
		passInput = new JTextField(15);
		GhostText userGhost = new GhostText(userInput, "User Name");
		GhostText passGhost = new GhostText(passInput, "Password");
		
		nameInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent theEvent) {
				if (theEvent.getKeyCode() != KeyEvent.VK_ENTER) {
					fName = nameInput.getText();
				}
			} 
		});
		lastNameInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent theEvent) {
				if (theEvent.getKeyCode() != KeyEvent.VK_ENTER) {
					lName = lastNameInput.getText();
				}
			} 
		});
		emailInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent theEvent) {
				if (theEvent.getKeyCode() != KeyEvent.VK_ENTER) {
					email = emailInput.getText();
				}
			} 
		});
		userInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent theEvent) {
				if (theEvent.getKeyCode() != KeyEvent.VK_ENTER) {
					myUser = userInput.getText();
				}
			} 
		});
		passInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent theEvent) {
				if (theEvent.getKeyCode() != KeyEvent.VK_ENTER) {
					myPass = passInput.getText();
				}
			} 
		});
	}
	
	/**
	 * Perform a check on the email to make sure it is actually an email.
	 * Only checks for the '@' symbol.
	 * 
	 * @return True if string contains '@', false otherwise.
	 */
	private boolean verifyEmail() {
		boolean result = false;
				
		if (email.contains("@")) {
				result = true;
		}
		return result;
	}
	
	/**
	 * Checks to see if the info the user has input is already in the database.
	 * 
	 * @param theUser The new user being registered.
	 * @return True if the user is not in the system, false otherwise.
	 */
	private boolean verifyNewUser(User theUser) {
		boolean result = true;
				
		myUsers = DBAccess.getUsers();
		
		for (int i = 0; i < myUsers.length; i++) {
			if (theUser.equals(myUsers[i])) {
				result = false;
			}
		}
		
		return result;
	}
	
	/**
	 * Checks to make sure that all text fields are not blank.
	 * Attempts to stop SQL injection by blocking any fields that 
	 * contain a single quote.
	 * 
	 * @return True if there are no blanks and false otherwise.
	 */
	private boolean verifyBlanks() {
		
		boolean result  = true;
		
		if (fName.isEmpty() || fName.contains("'") || fName.length() > 24) {
			result = false;
		} else if (lName.isEmpty() || lName.contains("'") || lName.length() > 24) {
			result = false;
		} else if (myPass.isEmpty() || myPass.contains("'") || myPass.length() > 24) {
			result = false;
		} else if (myUser.isEmpty() || myUser.contains("'") || myUser.length() > 24) {
			result = false;
		} else if (email.contains("'") || email.length() > 29) {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * Displays the register dialog.
	 * 
	 * @param parent The JFrame that is creating this dialog.
	 */
	public static void showRegisterDialog(JFrame parent) {
		new NewUser(parent);
	}
	
	private class registerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent theEvent) {
			User newUser = new User(fName, lName, myPass, false, email, myUser);
			invalidEmail.setVisible(false);
			errorMessage.setVisible(false);
			if (verifyEmail()) { // checks if email has the @ symbol inside it.
				if (verifyBlanks()) { // checks to make sure the other fields are not blank.
					if (verifyNewUser(newUser)) { //checks to see if the user already exists.
						DBAccess.registerUser(newUser);
						JOptionPane.showMessageDialog(null, "New User created successfully!");
						dispose();
					} else {
						errorMessage.setText("UserName or email already exists in System!");
						errorMessage.setVisible(true);
					}
				} else {
					errorMessage.setText("No blank inputs or a field is too long!");
					errorMessage.setVisible(true);
				}
			} else {
				invalidEmail.setVisible(true);
			}
		}
	}
}