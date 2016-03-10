package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePage {
	private JFrame myFrame;
	private JPanel mySearchPanel;
	
	public HomePage() {
		myFrame = new JFrame("Star Search Database");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setResizable(false);
		setImages();
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
		JButton search = createButton("Search");
		mySearchPanel.add(search);
		myFrame.add(mySearchPanel, BorderLayout.CENTER);
	}

	private JButton createButton(String string) {
		JButton button = new JButton(string);
		button.addActionListener(new HomeListener());
		return button;
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

}
