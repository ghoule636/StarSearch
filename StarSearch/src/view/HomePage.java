package view;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HomePage {
	private JFrame myFrame;
	private JPanel mySearchPanel;
	private JPanel myLogInPanel;
	private JTextField myUserName;
	private JTextField myPassword;
	private String myUserString;
	private String myPassString;
	
	public HomePage() {
		myFrame = new JFrame("Star Search Database");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setMinimumSize(new Dimension(500, 500));
		myFrame.setResizable(false);
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
		JButton search = createButton("Search");
		mySearchPanel.add(search);
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
		myUserName = new JTextField(8);
		myUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent theEvent) {
				if (theEvent.getKeyCode() != KeyEvent.VK_ENTER) {
					myUserString = myUserName.getText() + theEvent.getKeyChar();
				}
			}
		});
		myUserName.addActionListener(new LogOnListener());
		myPassword = new JTextField(8);
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
		
		for (int i = 0; i < listOfJudges.size(); i++) {
			if (listOfJudges.get(i).logOn(userString, passString)) {
				result = true;
				myJudge = listOfJudges.get(i);
			} else {
				result &= true;
			}
		}
		return result;
	}
	
	private List<Judge> loadJudges() {
		ArrayList<Judge> result = new ArrayList<>();
		
		Judge bob = new Judge("bob1", "1234");
		
		result.add(bob);
		
		return result;
	}

}
