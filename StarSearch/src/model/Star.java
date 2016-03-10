package model;

public class Star {

	private int starID;
	
	private int constellationID;
	
	private String name;
	
	private int temperature;
	
	private String type;
	
	private int mass;
	
	private double diameter;
	
	private int distance;
	
	private String description;
	
	public Star(int theStarID, int theConstID, String theName, int theTemp, String theType,
				int theMass, double theDiameter, int theDistance, String theDescrip) {
		
		starID = theStarID;
		temperature = theTemp;
		description = theDescrip;
		distance = theDistance;
		diameter = theDiameter;
		mass = theMass;
		type = theType;
		name = theName;
		constellationID = theConstID;
	}
	
	public Star() {
		starID = 0;
		temperature = 0;
		description = "";
		distance = 0;
		diameter = 0;
		mass = 0;
		type = "";
		name = "";
		constellationID = 0;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int theTemp) {
		temperature = theTemp;
	}

	public int getStarID() {
		return starID;
	}

	public void setStarID(int theStarID) {
		starID = theStarID;
	}

	public int getConstellationID() {
		return constellationID;
	}

	public void setConstellationID(int theConstID) {
		constellationID = theConstID;
	}

	public String getName() {
		return name;
	}

	public void setName(String theName) {
		name = theName;
	}

	public String getType() {
		return type;
	}

	public void setType(String theType) {
		type = theType;
	}

	public int getMass() {
		return mass;
	}

	public void setMass(int theMass) {
		mass = theMass;
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double theDiameter) {
		diameter = theDiameter;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int theDistance) {
		distance = theDistance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String theDescrip) {
		description = theDescrip;
	}
}