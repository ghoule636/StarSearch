package model;

import java.sql.*;


public class DBConnection {
	
		private Connection Conn ; 
		// Initialize connection 
		public DBConnection(String theUrl) {
			// try connect 
		      try {
		         Class.forName("com.mysql.jdbc.Driver");
		         Conn = DriverManager.getConnection(theUrl);
		         System.out.println("connected");	       
		      } catch (Exception e) {
		         System.out.println(e);
		      }
		}

		public String SelectQuery(String query){
		    Statement statement;
		    String result = "";
			try {
				statement = Conn.createStatement();
		        String queryString; 
		        if (query == ""){
		        	queryString = "select * from sysobjects where type='u'";
		        } else {
		        	queryString = query;
		        }
		        ResultSet rset = statement.executeQuery(queryString);
		        while (rset.next()) {
		           result = rset.getString(1);
		        }
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
}
