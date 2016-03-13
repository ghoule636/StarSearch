/*
 * TCSS 445 Database Star Search
 * Group 20
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.DBAccess;
import model.Favorite;
import model.Star;
import model.User;

/**
 * This panel will display the favorite stars of the given user.
 * 
 * @author Gabriel Houle
 */
public class FavoritesPanel extends JPanel {
	
	/**
	 * serial number.
	 */
	private static final long serialVersionUID = 8723913478541732241L;

	/**
	 * Stores the currently logged in user.
	 */
	private User myUser;
	
	/**
	 * Stores the users favorites stars.
	 */
	private Star[] myStars;
	
	/**
	 * Stores the currently displayed star.
	 */
	private Star myCurrentStar;
	
	/**
	 * Stores the current comment and rating.
	 */
	private Favorite myCurrentFavorite;
	
	/**
	 * Stores the results panel where text is displayed.
	 */
	JTextPane myResults = new JTextPane();
	
	/**
	 * Stores the current index being displayed in the list of stars.
	 */
	private int myCurrentIndex;
	
	/**
	 * Stores the error message label that will display if there are no favorites.
	 */
	private JLabel myErrorMessage;
	
	/**
	 * Stores the north panel used.
	 */
	private JPanel myNorth;
	
	/**
	 * Stores the center panel.
	 */
	private JPanel myCenter;
	
	/**
	 * Stores the south panel.
	 */
	private JPanel mySouth;
	
	/**
	 * Stores the next button.
	 */
	private JButton myNext;
	
	/**
	 * Stores the delete button.
	 */
	private JButton myDelete;
	
	/**
	 * Displays the user comment for this star.
	 */
	private JTextField myComment;
	
	/**
	 * Displays the users rating for this star.
	 */
	private JTextField myRating;

	public FavoritesPanel(User theUser) {
		super();
		myUser = theUser;
		myStars = DBAccess.getFavoriteStars(myUser);
		myCurrentIndex = 0;
		setup();
	}
	
	/**
	 * Sets up the display favorites panel.
	 * Will display a error message if there are no favorites for the given user.
	 */
	private void setup() {
		setLayout(new BorderLayout());
		myCenter = new JPanel();
		mySouth = new JPanel();
		myNorth = new JPanel();
		myCenter.setLayout(new GridBagLayout());
		if (myStars.length == 0) {
			myErrorMessage = new JLabel("No favorites found!");
			myCenter.add(myErrorMessage);
		} else {
			myComment = new JTextField(15);
			myRating = new JTextField(2);
			
			myComment.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(final KeyEvent theEvent) {
					if (theEvent.getKeyCode() != KeyEvent.VK_ENTER) {
						myCurrentFavorite.setUserComment(myComment.getText());
					}
				} 
			});
			myComment.addActionListener(new UpdateListener());
			myRating.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(final KeyEvent theEvent) {
					if (theEvent.getKeyCode() != KeyEvent.VK_ENTER) {
						myCurrentFavorite.setRating(getValue(myRating.getText()));
					}
				} 
			});
			myRating.addActionListener(new UpdateListener());
			
			myResults.setEditable(false);
			myResults.setPreferredSize(new Dimension(400, 325));
			displayNext();
			myCenter.add(myResults);
			myNext = new JButton("Next");
			myNext.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent theEvent) {
					displayNext();
				}
			});
			
			myDelete = new JButton("Remove from Favorites");
			myDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent theEvent) {
					DBAccess.deleteFavorite(myCurrentFavorite);
					displayNext();
				}
			});
			
			JButton updateComment = new JButton("Update Comment/rating");
			updateComment.addActionListener(new UpdateListener());
			
			myNorth.add(myNext);
			myNorth.add(myDelete);
			mySouth.add(myComment);
			mySouth.add(myRating);
			mySouth.add(updateComment);
		}
		
		add(myNorth, BorderLayout.NORTH);
		add(myCenter, BorderLayout.CENTER);
		add(mySouth, BorderLayout.SOUTH);
	}
	
	private int getValue(String theStr) {
		if (theStr.length() > 0) {
			return Integer.parseInt(theStr);
		} else {
			return 0;
		}
	}
	/**
	 * Displays the next star in the given users favorites list.
	 */
	private void displayNext() {
		myStars = DBAccess.getFavoriteStars(myUser);

		if (myStars.length == 0) {
			myErrorMessage = new JLabel("No favorites found!");
			myCenter.removeAll();
			mySouth.removeAll();
			myCenter.add(myErrorMessage);
			revalidate();
			myNext.setEnabled(false);
			myDelete.setEnabled(false);
			
		} else if (myCurrentIndex < myStars.length) {
			myCurrentStar = myStars[myCurrentIndex];
			
			myCurrentFavorite = DBAccess.getComment(myUser, myCurrentStar);
			
			myResults.setText("Information about: " + myCurrentStar.getName() + " \n\n" +
							myCurrentStar.getDescription() + " \n\nSolar Masses: " + myCurrentStar.getMass() +
							" \n\nStar Type: " + myCurrentStar.getType() + " \n\nConstellationID: " +
							myCurrentStar.getConstellationID() + " \n\nSolar Diameter:" + myCurrentStar.getDiameter() +
							" \n\nDistance from Sol (in Light Years): " + myCurrentStar.getDistance());
			
			myRating.setText(myCurrentFavorite.getRating() + "");
			myComment.setText(myCurrentFavorite.getUserComment());
			
			myCurrentIndex++;
		} else {
			myCurrentIndex = 0;
			displayNext();
		}
	}
	
	private class UpdateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent theEvent) {
			DBAccess.updateComment(myCurrentFavorite);
		}
	}
}
