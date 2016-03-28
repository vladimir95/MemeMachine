/* Definition comes from "Understanding Object Oriented Programming with
   Java", Timothy Budd, Addison-Wesley, 1998, p213.
   
   Use: A simple way -- less typing -- to associate a button with an action.  Avoids a case
   statement in actionPerformed testing which button was pressed at a cost of having many
   anonymous classes with one instance each.
   
   Example:
     westPanel.add(new ButtonAdapter("Exit") {
       public void pressed(){ mandel.stop(); dispose();}});
*/

/*
 * 
 * This Button Adapter has been adapted from Prof. Gunnar Gotshalks' Calculator System 3.
 * 
 */

import java.awt.event.*;
import javax.swing.JButton;

@SuppressWarnings("serial")
public abstract class ButtonAdapter extends JButton implements ActionListener {
	int buttonIndex;

/**
 * Adds an actionListener to passed JButton
 * @param button - the button to which an actionListener will be added
 */
  public ButtonAdapter (JButton button) {
    button.addActionListener(this);
  }
  
  /**
   * Adds an actionListener to passed JButton
   * Sets an index associated with this Jbutton
   * @param button - the button to which an actionListener will be added
   * @param index - index of the button in a list
   */
  public ButtonAdapter (JButton button, int index) { //Allows the button to store an index identifying the entry it corresponds to in a collection
	    button.addActionListener(this);
	    buttonIndex = index;
	  }
 
/**
 * Called when the actionListener detects an action performed on the JButton
 */
public void actionPerformed (ActionEvent e) { pressed(); }
  
/**
 * The method will be invoked when an action is performed on the JButton
 */
public abstract void pressed ();
}