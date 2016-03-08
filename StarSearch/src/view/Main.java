package view;

import model.DBAccess;

public class Main {

	public static void main(String[] args) {

		DBAccess.query("SELECT *");
	}

}
