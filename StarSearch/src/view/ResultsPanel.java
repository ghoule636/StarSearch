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
	Star[] myStars;
	Star myStar;
	JTextPane myResults = new JTextPane();

	public ResultsPanel(String mySearchedStar) {
		setLayout(new GridBagLayout());
		if (DBAccess.searchStar(mySearchedStar).length == 0) {
			myLabel = new JLabel("No Results found for that search!");
			add(myLabel);
		} else {
			myResults.setPreferredSize(new Dimension(400, 325));
			setUpDisplay(mySearchedStar);
		}

	}

	private void setUpDisplay(String theStar) {
		myResults.setEditable(false);
		myStars = DBAccess.searchStar(theStar);
		myStar = myStars[0];
		myResults.setText("Information about: " + myStar.getName() + " \n\n" +  myStar.getDescription() + " \n\nSolar Masses: "
				          + myStar.getMass() + " \n\nStar Type: " + myStar.getType() + " \n\nConstellationID: " + myStar.getConstellationID() 
				          + " \n\nSolar Diameter:" + myStar.getDiameter() + " \n\nDistance from Sol (in Light Years): " + myStar.getDistance());
		add(myResults);
	}

}
