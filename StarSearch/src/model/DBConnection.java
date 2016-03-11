package model;

import java.sql.*;
import java.util.Vector;


public class DBConnection {
	
		private Connection Conn ; 
		// Initialize connection 
		public DBConnection(String[] theUrl) {
			// try connect 
		      try {
		         Class.forName("com.mysql.jdbc.Driver");
		         Conn = DriverManager.getConnection(theUrl[0], theUrl[1], theUrl[2]);
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
		
		/**
		 * Searches the database for a star of the given search name.
		 * 
		 * @param searchStr Name of star being searched for.
		 * @return List of all stars with given name.
		 * @throws SQLException Bad Database connection.
		 */
		public Star[] searchStars(String searchStr) throws SQLException{
			Star starArr[];
			Vector<Star> vStar = new Vector<Star>();

			String queryString;
			Statement statement = Conn.createStatement();
			ResultSet rset ;
			queryString = "select *" +
						  " from Star " +
						  "where name = '%"+ searchStr +"%');";
			rset = statement.executeQuery(queryString);
			while(rset.next()){
				Star s = new Star();
				s.setStarID(rset.getInt("starID"));
				s.setName(rset.getString("name"));
				s.setTemperature(rset.getInt("temperature"));
				s.setType(rset.getString("type"));
				s.setMass(rset.getInt("mass"));
				s.setDiameter(rset.getInt("diameter"));
				s.setDistance(rset.getInt("distance"));
				s.setDescription(rset.getString("description"));
				vStar.add(s);
			}
			starArr = new Star[vStar.size()];
			vStar.toArray(starArr);
			return  starArr;
		}
}