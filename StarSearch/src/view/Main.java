package view;

import java.awt.EventQueue;

import model.DBAccess;

public class Main {
	
    /**
     * Private constructor to prevent instantiation of this class.
     */
    private Main() {
        throw new IllegalStateException();
    }

    /**
     * @param theArgs : The command line arguments.
     */
    public static void main(final String[] theArgs) {
	System.out.println(DBAccess.query("SELECT starName from Star"));
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePage();
            }
        });
    }
}