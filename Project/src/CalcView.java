import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;




@SuppressWarnings("serial") 
public class CalcView extends JFrame {
	
	JButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bdot,bclear,bmultiply,bdivide,badd,bsubtract,benter,
									bpi,bfact, bundo, bsin, bcos, bx, bgraph, bsign, btest;
	
	JTextField display,historyDisplay;
	
	JPanel numbers, buttons, operators, panel, panel2, mainpanel, left, right, bottom;
	
	Color mainColor, numberColor, operatorColor, functionColor, enterColor, buttonTextColor;
	
	public CalcView(final CalcController theController)
			throws MalformedURLException {
		
		//Initialize the Frame
		final JFrame frame = new JFrame();
		String title = "PostFix Calculator";
		frame.setSize(640, 720);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//Put the name of the Frame in the middle of the NameField of the Frame. 
		frame.setFont(new Font("System", Font.PLAIN, 14));
		Font font = frame.getFont();
		FontMetrics fm = frame.getFontMetrics(font);
		int x = fm.stringWidth(title);
		int y = fm.stringWidth(" ");
		int z = frame.getWidth()/2 - (x/2);
		int w = z/y;
		String name ="";
		name = String.format("%"+w+"s", name);
		frame.setTitle(name+title);
		
		//Icon of the Frame
		
		//Link to Icon on Vlad's computer, just in case. We will use it in case if we dont have wifi or smth...
		String path = "C:\\Users\\Vlad\\Desktop\\Vova`s homework\\Uni\\EECS2311\\icon1.png";
		path.replace("\\", "/");
		
		//Link to the GitHub Icon
		String link = "https://github.com/vladimir95/MemeMachine/blob/master/icon1.png?raw=true";
		
		
		URL url = new URL(link);
		
		//Sets the icon to this frame from the URL above. 
		ImageIcon ic = new ImageIcon(url);
		frame.setIconImage(ic.getImage());
		
		
		
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
		bclear = new JButton("Clear");
		bmultiply = new JButton("×"); 
		bdivide = new JButton("÷"); 
		badd = new JButton("+");
		bsubtract = new JButton("-");
		benter = new JButton("Enter");
		bpi = new JButton("\u03C0");  
		bfact = new JButton("!"); 
		bundo = new JButton("Undo");
		bsin = new JButton("sin"); 
		bcos = new JButton("cos"); 
		bx = new JButton("X"); 
		bgraph = new JButton("GRAPH");
		bsign = new JButton("+/-"); 
		
		//Sample Test Button
		btest = new JButton("Sample Test");
		
		
		//Increase specific buttons' fonts
		badd.setFont(new Font("Dialog", Font.PLAIN, 17));
		bsubtract.setFont(new Font("Dialog", Font.PLAIN, 18));
		bdivide.setFont(new Font("Dialog", Font.PLAIN, 18));
		bmultiply.setFont(new Font("Dialog", Font.PLAIN, 18));
		bsign.setFont(new Font("Dialog", Font.PLAIN, 17));
		
		
		//TextFields 
		display = new JTextField("",30); 
		historyDisplay = new JTextField("",30);
		display.setEditable(false);
		historyDisplay.setEditable(false);
		Dimension s = new Dimension(600,50);
		display.setPreferredSize(s); 
		
		//TextFields Fonts
		display.setFont(new Font("Arial Rounded", Font.BOLD,18));
		historyDisplay.setFont(new Font("Arial Rounded", Font.BOLD,18));
		
		
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
		
		//Set panels' layouts
	    numbers.setLayout(new GridLayout(4,3,3,3));
	    operators.setLayout(new GridLayout(4,2,3,3));
	    buttons.setLayout(new GridLayout(4,1,3,3)); //Grid Layout was changed accordingly for the Test button
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
	    
	    //SWITCHED ALIGNMENT OF BUTTONS
	    buttons.add(benter);
	    buttons.add(bundo);
	    buttons.add(bclear);
	    buttons.add(btest); // Adding Test Button
	   
	    buttons.add(bx); 
	    buttons.add(bgraph);
	    
	    operators.add(badd);
	    operators.add(bsubtract);
	    operators.add(bmultiply);
	    operators.add(bdivide);
	    operators.add(bsin);
	    operators.add(bcos);
	    operators.add(bfact);
	    operators.add(bsign);
	    
	    panel2.add(historyDisplay);
	    panel2.add(display);
	    panel.add(numbers);
	    panel.add(operators);
	    panel.add(buttons);
	    mainpanel.add(panel2 , BorderLayout.NORTH);
	    mainpanel.add(panel, BorderLayout.CENTER);
	    mainpanel.add(left, BorderLayout.EAST);
	    mainpanel.add(right, BorderLayout.WEST);
	    mainpanel.add(bottom, BorderLayout.SOUTH);
	    
	    
	    //ButtonAdapters
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
		
		add(new ButtonAdapter(bgraph) { 
		       public void pressed(){ 
		    	   JPanel a = theController.graph();
		    	   a.setBackground(Color.BLACK);
		    	   frame.setContentPane(a);
		    	   frame.revalidate();}});
		
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
		
		add(new ButtonAdapter(bx) { 
		       public void pressed(){ theController.x();}});
		
		add(new ButtonAdapter(bdot) { 
		       public void pressed(){ theController.numericButton(bdot.getText());}});
		
		add(new ButtonAdapter(b0) {
		       public void pressed(){ theController.numericButton(b0.getText());}});
		
		add(new ButtonAdapter(b1) {
		       public void pressed(){ theController.numericButton(b1.getText());}});
		
		add(new ButtonAdapter(b2) {
		       public void pressed(){ theController.numericButton(b2.getText());}});
		
		add(new ButtonAdapter(b3) {
		       public void pressed(){ theController.numericButton(b3.getText());}});
		
		add(new ButtonAdapter(b4) {
		       public void pressed(){ theController.numericButton(b4.getText());}});
		
		add(new ButtonAdapter(b5) {
		       public void pressed(){ theController.numericButton(b5.getText());}});
		
		add(new ButtonAdapter(b6) {
		       public void pressed(){ theController.numericButton(b6.getText());}});
		
		add(new ButtonAdapter(b7) {
		       public void pressed(){ theController.numericButton(b7.getText());}});
		
		add(new ButtonAdapter(b8) {
		       public void pressed(){ theController.numericButton(b8.getText());}});
		
		add(new ButtonAdapter(b9) {
		       public void pressed(){ theController.numericButton(b9.getText());}});	    
	    
		//SampleTest Button ButtonAdapter  
		add(new ButtonAdapter(btest) {
		       public void pressed(){ theController.sampleTest();}});
	    
	    
	
	    frame.add(mainpanel);
	    betterViewDesign();
	    buttonColor();
	    frame.setResizable(false);
	    frame.setVisible(true);
		
	}
	
	//Modifies the inputDisplay
	public void setDisplayText(String inputValue) {
		display.setText(inputValue);
		
	}
	//Modifies the historyDisplay
	public void setHistoryText(String historyValue) {
		historyDisplay.setText(historyValue);
		
	}
	
	
	//Assigns the certain colours to the panels, textFields' backgrounds and their texts(foreGround)
	public void betterViewDesign(){
		mainColor = new Color(24,29,33); //Color of the panels with buttons and side spaces
		
		//UPPER PART WITH DISPLAYS
		panel2.setBackground(Color.BLACK);  //sets the gap between the textfields black.
		historyDisplay.setBackground(Color.BLACK); //sets the Background to black
		historyDisplay.setForeground(Color.WHITE); //sets font to white
		display.setBackground(Color.BLACK); //sets the Background to black
		display.setForeground(Color.WHITE);	//sets font to white	
		display.setBorder(null); //deletes the borders of the Fields
		historyDisplay.setBorder(null); //same for historyDisplay.
		
		
		//LOWER PART WITH BUTTONS
		
		//Set the panels to the color first
		mainpanel.setBackground(mainColor); //spaces between the buttons and the main window borders
		panel.setBackground(mainColor);
		numbers.setBackground(mainColor);
		buttons.setBackground(mainColor);
		operators.setBackground(mainColor);
		
		
		
	}
	
	//Assigns certain colours to the buttons based on their functionality 
	public void buttonColor(){
		
		numberColor = new Color(97,107,116);
		buttonTextColor = new Color(250,251,255);
		operatorColor = new Color(57,61,70);
		functionColor = new Color(55,107,155);
		enterColor = new Color(218,100,2);
		
		//DIGITS COLOR AND FORMATTING
		ArrayList<JButton> digits = new ArrayList<JButton>();
		digits.add(b0);
		digits.add(b1);
		digits.add(b2);
		digits.add(b3);
		digits.add(b4);
		digits.add(b5);
		digits.add(b6);
		digits.add(b7);
		digits.add(b8);
		digits.add(b9);
		digits.add(bdot);
		digits.add(bpi);
		
		//for all numbers:
		for (JButton b: digits){
			b.setBackground(numberColor);
			b.setForeground(buttonTextColor);
			b.setFocusable(false); // The text of the button is not surrounded with a box
			b.setBorder(null); // no surrounding border of the button
			b.setFont(b.getFont().deriveFont(20f)); //Increase the font of the text of the buttons
		}
		
		
		//SIGNS COLOR AND FORMATTING -- BIGGER FONT THAN OTHERS BY 2 UNITS
		ArrayList<JButton> signs = new ArrayList<JButton>();
		signs.add(badd);
		signs.add(bsubtract);
		signs.add(bmultiply);
		signs.add(bdivide);
		
		
		//for all signs:
		for (JButton b: signs){
			b.setBackground(operatorColor);
			b.setForeground(buttonTextColor);
			b.setFocusable(false); // The text of the button is not surrounded with a box
			b.setBorder(null); // no surrounding border of the button
			b.setFont(b.getFont().deriveFont(22f)); //Increase the font of the text of the buttons
			
		}
		
		
		//FUNCTIONS COLOR AND FORMATTING
		ArrayList<JButton> functions = new ArrayList<JButton>();
		functions.add(bclear);
		functions.add(bundo);
		functions.add(bsin);
		functions.add(bcos);
		functions.add(bfact);
		functions.add(bsign);
		functions.add(btest);
		functions.add(bgraph); //Will be same color as other function buttons
		functions.add(bx); //Will be same color as other function buttons
				
				
		//for all functions:
		for (JButton b: functions){
			b.setBackground(functionColor);
			b.setForeground(buttonTextColor);
			b.setFocusable(false); // The text of the button is not surrounded with a box
			b.setBorder(null); // no surrounding border of the button
			b.setFont(b.getFont().deriveFont(20f)); //Increase the font of the text of the buttons
	    }		
		
		
		//ENTER BUTTON AND FORMATTING
		benter.setBackground(enterColor);
		benter.setForeground(buttonTextColor);
		benter.setFocusable(false);
		benter.setBorder(null);
		benter.setFont(benter.getFont().deriveFont(20f));
	}
}
