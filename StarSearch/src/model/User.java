package model;

public class User {
	
	private int userID;
	
	private String fName;
	
	private String lName;
	
	private String password;
	
	private String user;
	
	private boolean mod;
	
	private String email;

	public User(int theID, String theFName, String theLName, String thePass,
				boolean theMod, String theEmail, String theUser) {
		
		email = theEmail;
		user = theUser;
		mod = theMod;
		password = thePass;
		lName = theLName;
		fName = theFName;
		userID = theID;
	}
	
	public User() {
		
		userID = 0;
		fName = "";
		lName = "";
		user = "";
		password = "";
		mod = false;
		email = "";
		
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int theID) {
		userID = theID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String theFName) {
		fName = theFName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String theLName) {
		lName = theLName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String thePass) {
		password = thePass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String theUser) {
		user = theUser;
	}

	public boolean isMod() {
		return mod;
	}

	public void setMod(boolean theMod) {
		mod = theMod;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String theEmail) {
		email = theEmail;
	}
}
