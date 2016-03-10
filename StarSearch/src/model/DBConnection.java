package model;

import java.sql.*;


public class DBConnection {
	
		private Connection Conn ; 
		// Initialize connection 
		public DBConnection(String[] theUrl) {
			// try connect 
		      try {
		         Class.forName("com.mysql.jdbc.Driver");
		         Conn = DriverManager.getConnection(theUrl[0], theUrl[1], theUrl[2]);
//		         System.out.println("connected");	       
		      } catch (Exception e) {
		         System.out.println(e);
		      }
		}

		public String SelectQuery(String query){
		    Statement statement;
		    String result = "";
			try {
				statement = Conn.createStatement();		        
		        ResultSet rset = statement.executeQuery(query);
		        while (rset.next()) {
		        	System.out.println(rset.getString(1));
		           result += rset.getString(1);
		        }
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
}
