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
  public ButtonAdapter (JButton button) {
    //super (button.getName());
    button.addActionListener(this);
  }
 

public void actionPerformed (ActionEvent e) { pressed(); }
  
  public abstract void pressed ();
}