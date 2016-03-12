package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import model.DBAccess;
import model.Star;

@SuppressWarnings("serial")
public class ResultsPanel extends JPanel {
	
	JLabel myLabel;
	Star[] myStar;
	JTextPane myResults = new JTextPane();

	public ResultsPanel(String mySearchedStar) {
		setLayout(new GridBagLayout());
		myResults.setPreferredSize(new Dimension(250, 250));
		//myResults.setWrapStyleWord(true);
		myStar = DBAccess.searchStar(mySearchedStar);
		myResults.setText(myStar[0].getDescription());
		//myLabel = new JLabel(myStar[0].getDescription());
		//myLabel = new JLabel(mySearchedStar);
		add(myResults);
	}

}
