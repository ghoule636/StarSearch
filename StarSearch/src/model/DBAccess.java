package model;

public class DBAccess {

	private static String[] url = {"jdbc:mysql://vergil.u.washington.edu:61985/Star_Search", "root", "myUW2015"};
	
	static DBConnection db = new DBConnection(url);
	
	public static String query(String theQuery) {
		String result = "";
		
		db.SelectQuery(theQuery);
		
		return result;
	}
}
