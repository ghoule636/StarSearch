/*
 * TCSS 445 Database Star Search
 * Group 20
 */

package model;

import java.sql.SQLException;

public class DBAccess {

	private static String[] url = {"jdbc:mysql://vergil.u.washington.edu:61985/Star_Search", "root", "myUW2015"};
	
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