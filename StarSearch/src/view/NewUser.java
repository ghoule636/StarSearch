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
	
	private String myUser = "";
	private String myPass = "";
	private String fName = "";
	private String lName = "";
	private String email = "";
	private JTextField nameInput;
	private JTextField lastNameInput;
	private JTextField emailInput;
	private JTextField userInput;
	private JTextField passInput;
	private User[] myUsers;
	private JLabel errorMessage;
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
	
	private boolean verifyEmail() {
		boolean result = false;
				
		if (email.contains("@")) {
				result = true;
		}
		return result;
	}
	
	private boolean verifyNewUser(User theUser) {
		boolean result = true;
				
		myUsers = DBAccess.getUsers();
		
		for (int i = 0; i < myUsers.length; i++) {
			if (theUser.equals(myUsers[i])) {
				System.out.println("Already in system" + myUsers[i].getEmail());
				result = false;
			}
		}
		
		return result;
	}
	
	private boolean verifyBlanks() {
		
		boolean result  = true;
		
		if (fName.isEmpty()) {
			result = false;
		} else if (lName.isEmpty()) {
			result = false;
		} else if (myPass.isEmpty()) {
			result = false;
		} else if (myUser.isEmpty()) {
			result = false;
		}
		
		return result;
	}
	
	public static void showRegisterDialog(JFrame parent) {
		new NewUser(parent);
	}
	
	private class registerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent theEvent) {
			User newUser = new User(fName, lName, myPass, false, email, myUser);
			invalidEmail.setVisible(false);
			errorMessage.setVisible(false);
			if (verifyEmail()) {
				if (verifyBlanks()) {
					if (verifyNewUser(newUser)) {
						System.out.println("added User");
						DBAccess.registerUser(newUser);
					} else {
						errorMessage.setText("UserName or email already exists in System!");
						errorMessage.setVisible(true);
					}
				} else {
					errorMessage.setText("No blank inputs allowed!");
					errorMessage.setVisible(true);
				}
			} else {
				invalidEmail.setVisible(true);
			}
		}
	}
}
