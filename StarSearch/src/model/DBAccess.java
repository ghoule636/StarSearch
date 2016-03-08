package model;

public class DBAccess {
	
	static DBConnection db = new DBConnection(""
			+ "jdbc:sqlserver://<alvillar.vergil.u.washington.edu:61985>;"
			+ "databaseName=Star_Search;"
			+ "user=user;"
			+ "password=<password>;"
			);
	
	public static String query(String theQuery) {
		String result = "";
		
		db.SelectQuery(theQuery);
		
		return result;
	}
}