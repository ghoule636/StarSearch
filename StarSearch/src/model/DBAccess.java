package model;

import java.sql.SQLException;

public class DBAccess {

	private static String[] url = {"jdbc:mysql://vergil.u.washington.edu:61985/Star_Search", "root", "myUW2015"};
	
	static DBConnection db = new DBConnection(url);
	
	public static String query(String theQuery) {
		String result = "";
		
		db.SelectQuery(theQuery);
		
		return result;
	}
	
	/**
	 * Searches the database for a star of the given search name.
	 * Returns null if SQLException thrown. Returns empty if no stars are found.
	 * 
	 * @param searchStr Name of star being searched for.
	 * @return List of all stars with given name.
	 * @throws SQLException Bad Database connection.
	 */
	public static Star[] searchStar(String searchStr) throws SQLException{
		
		try {
			return db.searchStars(searchStr);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
