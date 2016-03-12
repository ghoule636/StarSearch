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

		public String selectQuery(String query){
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
		 * This will run a given update Query.
		 * 
		 * @param query Update query as a string in SQL.
		 */
		public void updateQuery(String query) {
			Statement stm;
			try {
				stm = Conn.createStatement();
				stm.executeUpdate(query); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Function runs a given query.
		 * 
		 * @param query SQL query as a string.
		 */
		public void performQuery(String query) {
			Statement stm;
			try {
				stm = Conn.createStatement();
				stm.executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Searches the database for a star of the given search name.
		 * 
		 * @param searchStr Name of star being searched for.
		 * @return List of all stars with given name.
		 * @throws SQLException
		 */
		public Star[] searchStars(String searchStr) throws SQLException {
			Star starArr[];
			Vector<Star> vStar = new Vector<Star>();

			String queryString;
			Statement statement = Conn.createStatement();
			ResultSet rset ;
			queryString = "SELECT *" +
						  " FROM Star " +
						  "WHERE starName = '"+ searchStr +"';";
			rset = statement.executeQuery(queryString);
			while(rset.next()) {
				Star s = new Star();
				s.setStarID(rset.getInt("starID"));
				s.setName(rset.getString("starName"));
				s.setTemperature(rset.getInt("temperature"));
				s.setType(rset.getString("starType"));
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

		/**
		 * This function will return all users in the Users table of the database.
		 * 
		 * @return Array of all users.
		 * @throws SQLException
		 */
		public User[] getUsers() throws SQLException {
			User userArr[];
			Vector<User> vUser = new Vector<User>();

			String queryString;
			Statement statement = Conn.createStatement();
			ResultSet rset ;
			queryString = "SELECT *" +
						  " FROM Users;"; 
			rset = statement.executeQuery(queryString);
			while(rset.next()) {
				User u = new User();
				u.setUserID(rset.getInt("userID"));
				u.setfName(rset.getString("fname"));
				u.setlName(rset.getString("lname"));
				u.setEmail(rset.getString("email"));
				u.setPassword(rset.getString("password"));
				u.setUser(rset.getString("userName"));
				u.setMod(rset.getBoolean("moderator"));
				vUser.add(u);
			}
			userArr = new User[vUser.size()];
			vUser.toArray(userArr);
			return  userArr;
		}
}