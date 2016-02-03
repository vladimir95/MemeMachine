import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalcView extends JFrame implements ActionListener {
	JButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bdot,bclear,bmultiply,bdivide,badd,bsubtract,benter, bpi, bfact, bundo, bsin, bcos, bx;
	JTextArea display,historyDisplay;
	JPanel pad, commands, operators, mainpanel, buttonspanel ;
	
	public CalcView () {
		super();
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		b0 = new JButton("0");
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		bdot = new JButton(".");
		bclear = new JButton("CLEAR");
		bmultiply = new JButton("x");
		bdivide = new JButton("/");
		badd = new JButton("+");
		bsubtract = new JButton("-");
		benter = new JButton("ENTER");
		bpi = new JButton("\u03C0");  
		bfact = new JButton("!");
		bundo = new JButton("UNDO");
		bsin = new JButton("sin"); 
		bcos = new JButton("cos"); 
		bx = new JButton("x"); 
		display = new JTextArea("0");
		historyDisplay = new JTextArea("");
		display.setSize(25,0);
		display.setEditable(false);
		historyDisplay.setSize(25,0);
		historyDisplay.setEditable(false);
		pad = new JPanel();
		commands = new JPanel();
		operators = new JPanel();
		buttonspanel = new JPanel();
	    mainpanel = new JPanel();
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
