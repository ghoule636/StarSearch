/*
 * TCSS 445 Database Star Search
 * Group 20
 */

package model;

/**
 * 
 * 
 * @author Gabriel Houle
 */
public class Favorite {
	
	private int favoriteID;
	
	private int userID;
	
	private int starID;
	
	private int rating;
	
	private String userComment;
	
	public Favorite() {
		favoriteID = 0;
		userID = 0;
		starID = 0;
		rating = 0;
		userComment = "";
	}
	
	public Favorite(int theUserID, int theStarID, int theRating, String theComment) {
		userID = theUserID;
		starID = theStarID;
		rating = theRating;
		userComment = theComment;
	}

	public int getFavoriteID() {
		return favoriteID;
	}

	public void setFavoriteID(int favoriteID) {
		this.favoriteID = favoriteID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getStarID() {
		return starID;
	}

	public void setStarID(int starID) {
		this.starID = starID;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}
}
