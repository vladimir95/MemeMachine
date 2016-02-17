import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CalcView extends JFrame {
	JButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bdot,bclear,bmultiply,bdivide,badd,bsubtract,benter,
									bpi,bfact, bundo, bsin, bcos, bx, bgraph, bsign;
	JTextField display,historyDisplay;
	//JTextField historyDisplay; <-- Should we use JTextArea, which allows multiple lines of
												//text for history??
	JPanel pad, commands, operators, operators2, mainpanel, buttonspanel, signs, graph;
	
	
	//Implement Controller first and then use it. 
	//public CalcView (CalcController theController) {
	
	public CalcView() {
		super("pizdec :)");
		//setSize(480, 640);
		setSize(720, 720);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Buttons
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
		bmultiply = new JButton("×"); //**The new × is not actually an x, its a different character**
		bdivide = new JButton("÷"); //**Andrew made a valid point that in the specs its a × and not a * and ÷ and not a /**
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
		bsign = new JButton("+/-"); //<-- ChangeSign Button!!!
		
		//TextAreas 
		display = new JTextField("0",20); 
		historyDisplay = new JTextField("xyu",20);
		display.setSize(25,0);
		//display.setEditable(false);    
		historyDisplay.setSize(25,0);
		//historyDisplay.setEditable(false); 
		
		//Panels 
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
	    MyButtonAdapter(); //<-- Runs the Button Adapter thing.
	    this.setVisible(true);
		
	}
	/**
	 * We will not need this method eventually, as the View will be launched from Controller
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalcView c = new CalcView();
	}

	/*
	 * @Suppress warnings shit allows us to not to create some final long ID for 
	 * every single buttonAdapter
	 */
	@SuppressWarnings("serial") 
	public void MyButtonAdapter() {
		
		add(new ButtonAdapter(badd) {
		       public void pressed(){ theController.sum();}});
		
		add(new ButtonAdapter(bsubtract) {
		       public void pressed(){ theController.subtract();}});
		
		add(new ButtonAdapter(bmultiply) {
		       public void pressed(){ theController.multiply();}});
		
		add(new ButtonAdapter(bdivide) {
		       public void pressed(){ theController.divide();}});
		
		add(new ButtonAdapter(bclear) {
		       public void pressed(){ theController.clear();}});
		
		add(new ButtonAdapter(bundo) {
		       public void pressed(){ theController.undo();}});
		
		add(new ButtonAdapter(benter) {
		       public void pressed(){ theController.enter();}});
		
		//Leave it as blank method in Controller/Model?
		add(new ButtonAdapter(bgraph) { 
		       public void pressed(){ theController.graph();}});
		
		add(new ButtonAdapter(bsin) {
		       public void pressed(){ theController.sine();}});
		
		add(new ButtonAdapter(bcos) {
		       public void pressed(){ theController.cosine();}});
		
		add(new ButtonAdapter(bpi) {
		       public void pressed(){ theController.pi();}});
		
		add(new ButtonAdapter(bfact) {
		       public void pressed(){ theController.factorial();}});
		
		add(new ButtonAdapter(bsign) {
		       public void pressed(){ theController.changeSign();}});
		
		//Leave it as blank method in Controller/Model?
		add(new ButtonAdapter(bx) { 
		       public void pressed(){ theController.x();}});
		
		add(new ButtonAdapter(bdot) { 
		       public void pressed(){ theController.numericButton(bdot.getName());}});
		
		add(new ButtonAdapter(b0) {
		       public void pressed(){ theController.numericButton(b0.getName());}});
		
		add(new ButtonAdapter(b1) {
		       public void pressed(){ theController.numericButton(b1.getName());}});
		
		add(new ButtonAdapter(b2) {
		       public void pressed(){ theController.numericButton(b2.getName());}});
		
		add(new ButtonAdapter(b3) {
		       public void pressed(){ theController.numericButton(b3.getName());}});
		
		add(new ButtonAdapter(b4) {
		       public void pressed(){ theController.numericButton(b4.getName());}});
		
		add(new ButtonAdapter(b5) {
		       public void pressed(){ theController.numericButton(b5.getName());}});
		
		add(new ButtonAdapter(b6) {
		       public void pressed(){ theController.numericButton(b6.getName());}});
		
		add(new ButtonAdapter(b7) {
		       public void pressed(){ theController.numericButton(b7.getName());}});
		
		add(new ButtonAdapter(b8) {
		       public void pressed(){ theController.numericButton(b8.getName());}});
		
		add(new ButtonAdapter(b9) {
		       public void pressed(){ theController.numericButton(b9.getName());}});
		
	}
	
	/*
	 * Check if these work properly!
	 * @param inputValue
	 */
	public void setDisplayText(String inputValue) {
		display.setText(inputValue);
		
	}
	
	public void setHistoryText(String historyValue) {
		historyDisplay.setText(historyValue);
		
	}
}
