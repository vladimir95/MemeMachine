import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalcView extends JFrame implements ActionListener {
	JButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bdot,bclear,bmultiply,bdivide,badd,bsubtract,benter, bpi, 
									bfact, bundo, bsin, bcos, bx, bgraph;
	JTextArea display,historyDisplay;
	JPanel pad, commands, operators, operators2, mainpanel, buttonspanel, signs, graph;
	
	public CalcView () {
		super("pizdec :)");
	
		//setSize(480, 640);
		setSize(720, 720);
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
		bfact = new JButton("n!");
		bundo = new JButton("UNDO");
		bsin = new JButton("sin"); 
		bcos = new JButton("cos"); 
		bx = new JButton("x"); 
		bgraph = new JButton("GRAPH");
		display = new JTextArea("0");
		historyDisplay = new JTextArea("xyu");
		display.setSize(25,0);
		display.setEditable(false);
		historyDisplay.setSize(25,0);
		historyDisplay.setEditable(false);
		graph = new JPanel();
		pad = new JPanel();
		commands = new JPanel();
		operators = new JPanel();
		operators2 = new JPanel();
		buttonspanel = new JPanel();
	    mainpanel = new JPanel();
	    signs = new JPanel();
	  
	    pad.setLayout(new GridLayout(2,2,10,10));
	    pad.add(buttonspanel);
	    pad.add(operators);
	    pad.add(operators2);
	    pad.add(commands);
	    graph.add(bgraph);
	    BoxLayout axis =new BoxLayout(mainpanel, BoxLayout.Y_AXIS) ;
	    
	    mainpanel.setLayout(axis);
	    mainpanel.add(historyDisplay);
	    mainpanel.add(display);
	    mainpanel.add(pad);
	    mainpanel.add(graph);
	 
	   
	    buttonspanel.setLayout(new GridLayout(4,3,10,10));
	    buttonspanel.add(b1);
	    buttonspanel.add(b2);
	    buttonspanel.add(b3);
	    buttonspanel.add(b4);
	    buttonspanel.add(b5);
	    buttonspanel.add(b6);
	    buttonspanel.add(b7);
	    buttonspanel.add(b8);
	    buttonspanel.add(b9);
	    buttonspanel.add(bdot);
	    buttonspanel.add(b0);
	    buttonspanel.add(bpi);
	    
	    graph.setLayout(new GridLayout(2,2,10,10));
	   
	    operators.setLayout(new GridLayout(2,2,10,10));
	    operators2.add(bclear);
	    operators2.add(bundo);
	    operators.add(signs);
	    operators2.setLayout(new GridLayout(2,2,10,10));
	    signs.setLayout(new GridLayout(2,2,10,10));
	    signs.add(badd);
	    signs.add(bsubtract);
	    signs.add(bmultiply);
	    signs.add(bdivide);
	    operators.add(benter);
	    bclear.setMaximumSize(signs.getMaximumSize());
	    bclear.setMinimumSize(signs.getMaximumSize());
	    bundo.setMaximumSize(signs.getMaximumSize());
	    bundo.setMinimumSize(signs.getMaximumSize());
	    benter.setMaximumSize(signs.getMaximumSize());
	    benter.setMinimumSize(signs.getMaximumSize());
	    commands.setLayout(new GridLayout(2,2,10,10));
	  
	    commands.add(bsin);
	    commands.add(bcos);
	    commands.add(bfact);
	    commands.add(bx);
	    this.setResizable(false);
	    this.add(mainpanel);
	    this.setVisible(true);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalcView c = new CalcView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
