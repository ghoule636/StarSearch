/*
 * TCSS 445 Database Star Search
 * Group 20
 */

package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import model.DBAccess;
import model.Star;

/**
 * Panel class only used to check for and display results of a search.
 * @author Antonio V. Alvillar
 */
@SuppressWarnings("serial")
public class ResultsPanel extends JPanel {
	
	/**
	 * Label to store a message if no star is found. 
	 */
	private JLabel myLabel;
	
	/**
	 * Array of star objects used to find a particular star.
	 */
	private Star[] myStars;
	
	/**
	 * store a particular star returned from the search.
	 */
	private Star myStar;
	
	/**
	 * Text pane used to display information of a star that was found.
	 */
	private JTextPane myResults = new JTextPane();

	/**
	 * Panel that can be added to a JFrame and display information about
	 * a particular star plus check if no star is found. 
	 * @param A string of a star name to be searched. 
	 */
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

	/**
	 * Search the database for the star then display all the information 
	 * about the star that is needed. 
	 * @param Star name that was found from the previous method. 
	 */
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
