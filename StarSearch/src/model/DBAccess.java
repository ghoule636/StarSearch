/*
 * TCSS 445 Database Star Search
 * Group 20
 */

package model;

import java.sql.SQLException;

public class DBAccess {

	private static String[] url = {"jdbc:mysql://10.0.0.2:3306/StarSearch", "starSearch", "42101010ag"};
	
	static DBConnection db = new DBConnection(url);
	
	public static String query(String theQuery) {
		String result = "";
		
		db.selectQuery(theQuery);
		
		return result;
	}
	
	/**
	 * Searches the database for a star of the given search name.
	 * Returns null if SQLException thrown. Returns empty if no stars are found.
	 * 
	 * @param searchStr Name of star being searched for.
	 * @return List of all stars with given name.
	 * @throws SQLException
	 */
	public static Star[] searchStar(String searchStr) {
		
		try {
			return db.searchStars(searchStr);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This function will return all users as an array.
	 * 
	 * @return User array containing all users.
	 */
	public static User[] getUsers() {
		try {
			return db.getUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This function will return all the stars that the given user has added to their favorites.
	 * 
	 * @param theUser
	 * @return
	 */
	public static Star[] getFavoriteStars(User theUser) {
		try {
			return db.getFavoriteStars(theUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This function will add the given favorite object to the favorites table.
	 * 
	 * @param newFavorite Favorite object being added.
	 */
	public static void addFavorite(Favorite newFavorite) {
		String query = "INSERT INTO Favorites VALUES (null, "  + newFavorite.getUserID() + ", " +
						newFavorite.getStarID() + ", " + newFavorite.getRating() + ", '" +
						newFavorite.getUserComment() + "');";
		db.performQuery(query);
	}
	
	/**
	 * Returns a comment on a given star for a given user.
	 * 
	 * @param theUser The current user.
	 * @param theStar The currently viewed star.
	 * @return The comment and rating as a favorite object.
	 */
	public static Favorite getComment(User theUser, Star theStar) {
		
		try {
			return db.getComment(theUser, theStar);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * This method will perform an update on the database, so that we can
	 * update comments.
	 * 
	 * @param theFavorite The new rating and comment.
	 */
	public static void updateComment(Favorite theFavorite) {
		String query = "UPDATE Favorites SET rating = " + theFavorite.getRating() +
						" WHERE favoriteID = " + theFavorite.getFavoriteID() + ";";
		db.updateQuery(query);
		
		query = "UPDATE Favorites SET userComment = '" + theFavorite.getUserComment() +
				"' WHERE favoriteID = " + theFavorite.getFavoriteID() + ";";

		db.updateQuery(query);
	}
	
	/**
	 * This function will delete the given favorite from the database.
	 * 
	 * @param theFavorite The favorite being deleted.
	 */
	public static void deleteFavorite(Favorite theFavorite) {
		String query = "DELETE FROM Favorites WHERE favoriteID = " + theFavorite.getFavoriteID();
		
		db.performQuery(query);
	}
	
	/**
	 * This function will perform an insert query on the database and add
	 * the new user to the database. This functions assumes that the given user
	 * is unique.
	 * 
	 * @param newUser User being added to the database.
	 */
	public static void registerUser(User newUser) {
		String query = "INSERT INTO Users VALUES (null, '" + newUser.getfName() +
						"', '" + newUser.getlName() + "', '" + newUser.getPassword() +
						"', " + newUser.isMod() + ", '" + newUser.getEmail() +
						"', '" + newUser.getUser() + "');";
		db.performQuery(query);
	}
}