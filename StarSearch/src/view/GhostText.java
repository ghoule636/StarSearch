/*
 * TCSS 445 Database Star Search
 * Group 20
 */

package view;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Allows ghost text to appear in text boxes. Credit to StackOverflow user 
 * "Guillaume Polet" on page 
 * http://stackoverflow.com/questions/10506789/how-to-display-faint-gray-ghost-text-in-a-jtextfield
 * And to Andy Bleich.
 * 
 * @author Andy Bleich, Guillaume Polet
 *
 */
public class GhostText implements FocusListener, DocumentListener, PropertyChangeListener {
	 
	private JTextField myTextField;
	
	private boolean isEmpty;
	
	private Color myGhostColor;
	
	private Color myForegroundColor;
	
	private final String myGhostText;
	
	public GhostText (final JTextField theTextField, final String theGhostText) {
		super();
		myTextField = theTextField;
		myGhostText = theGhostText;
		myGhostColor = Color.GRAY;
		myTextField.addFocusListener(this);
		registerListeners();
		updateState();
		if (!myTextField.hasFocus()) {
			focusLost(null);
		}
		
	}
	
	private void registerListeners() {
		myTextField.getDocument().addDocumentListener(this);
		myTextField.addPropertyChangeListener("foreground", this);
	}
	
	private void unregisterListeners() {
		myTextField.getDocument().removeDocumentListener(this);
		myTextField.removePropertyChangeListener("foreground", this);
	}
	
	private void updateState() {
		isEmpty = myTextField.getText().length() == 0;
		myForegroundColor = myTextField.getForeground();
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if (isEmpty) {
			unregisterListeners();
			try {
				myTextField.setText("");
				myTextField.setForeground(myForegroundColor);
			} finally { 
				registerListeners();
			}
		}
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		if (isEmpty) {
			unregisterListeners();
			try {
				myTextField.setText(myGhostText);
				myTextField.setForeground(myGhostColor);
			} finally { 
				registerListeners();
			}
		}
	}
	
	@Override 
	public void propertyChange(PropertyChangeEvent e) {
		updateState();
	}
	
	@Override 
	public void changedUpdate(DocumentEvent e) {
		updateState();
	}
	
	@Override 
	public void insertUpdate(DocumentEvent e) {
		updateState();
	}
	
	@Override 
	public void removeUpdate(DocumentEvent e) {
		updateState();
	}
}
