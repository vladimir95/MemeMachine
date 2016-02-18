import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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
	JPanel numbers, buttons, operators, panel, panel2, mainpanel, left, right, bottom;
	
	//Implement Controller first and then use it. 
	//public CalcView (CalcController theController) {
	
	public CalcView() {
		super("Blyat!");
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
		bdivide = new JButton("÷"); //**Andrew made a valid point that in the specs its a × and not a * and ÷ and not a /. Ball out Andrew!**
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
		historyDisplay = new JTextField("xyn",20);
		Dimension s = new Dimension(600,50);
		display.setPreferredSize(s); 
		
		//Panels 
		numbers = new JPanel();
		buttons = new JPanel();
		operators = new JPanel();
		panel = new JPanel();
		panel2 = new JPanel();
		mainpanel = new JPanel();
		left = new JPanel();
		right = new JPanel();
		bottom = new JPanel();
		
	    numbers.setLayout(new GridLayout(4,3,3,3));
	    operators.setLayout(new GridLayout(4,2,3,3));
	    buttons.setLayout(new GridLayout(4,1,3,3));
	    panel.setLayout(new GridLayout(3,1,10,10));
	    panel2.setLayout(new GridLayout(2,1,10,10));
	    mainpanel.setLayout(new BorderLayout(20,20));
	    left.setLayout(new GridLayout(1,1,0,0));
	    right.setLayout(new GridLayout(1,1,0,0));
	    bottom.setLayout(new GridLayout(1,1,0,0));
	    
	    numbers.add(b1);
	    numbers.add(b2);
	    numbers.add(b3);
	    numbers.add(b4);
	    numbers.add(b5);
	    numbers.add(b6);
	    numbers.add(b7);
	    numbers.add(b8);
	    numbers.add(b9);
	    numbers.add(bdot);
	    numbers.add(b0);
	    numbers.add(bpi);
	    
	    buttons.add(benter);
	    buttons.add(bclear);
	    buttons.add(bundo);
	    buttons.add(bgraph);
	    
	    operators.add(badd);
	    operators.add(bsubtract);
	    operators.add(bmultiply);
	    operators.add(bdivide);
	    operators.add(bsin);
	    operators.add(bcos);
	    operators.add(bfact);
	    operators.add(bx);
	    
	    panel2.add(display);
	    panel2.add(historyDisplay);
	    panel.add(numbers);
	    panel.add(operators);
	    panel.add(buttons);
	    mainpanel.add(panel2 , BorderLayout.NORTH);
	    mainpanel.add(panel, BorderLayout.CENTER);
	    mainpanel.add(left, BorderLayout.EAST);
	    mainpanel.add(right, BorderLayout.WEST);
	    mainpanel.add(bottom, BorderLayout.SOUTH);
	    
	    this.setResizable(false);
	    this.add(mainpanel);
	    //MyButtonAdapter(); //<-- Runs the Button Adapter thing.
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
	/*
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
	*/
	
	
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
