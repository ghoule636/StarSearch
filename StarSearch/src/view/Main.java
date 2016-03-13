/*
 * TCSS 445 Database Star Search
 * Group 20
 */

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
    	DBAccess.getUsers();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePage();
            }
        });
    }
}