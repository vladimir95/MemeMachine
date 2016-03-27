import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Polygon;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DrawFunction extends JPanel
{
	private Color axisColor = new Color(97,107,116);
	
	private double[] yPoints;
	private CalcView view;
	private String equation;
	private GraphController controller;
	private boolean continousFunction = true;
	
	//Graph's buttons
	JButton bback, bfavourites, baddToFavourites;
	//Graph's equation TextField
	JTextField equationName;
	
	DrawFunction(double[] data, String functioName, CalcView theView, GraphController theController)
	{
		this.setBackground(Color.BLACK);
		
		yPoints = data;
		view = theView;
		equation = functioName;
		controller = theController;
		
		
		//Graph's Panel buttons
		bback = new JButton("< Back");
		bfavourites = new JButton("Favourites");
		baddToFavourites = new JButton("Add to Favourites");
		equationName = new JTextField(equation);
		
		addButtonsToGraph(this);
		
		
		
		new ButtonAdapter(bfavourites) {		//Sets the button to switch to the favorites view
		       public void pressed(){ openFavourites();}};
		
		new ButtonAdapter(bback) {				//Sets the button to switch back to the calculator view
			public void pressed(){Back();}};
			
		new ButtonAdapter(baddToFavourites) {	//Sets the button to add the current graph to the favorites and switch to the favorites view
				public void pressed(){addToFavourites();;}};
		
		view.frame.setContentPane(this);		//Sets the current view to this graph view
		view.frame.revalidate();
	}
	
	/**
	 * Sets the display to the calculator view
	 */
	protected void Back()
	{
		view.frame.setContentPane(view.mainpanel);
		view.frame.revalidate();
	}

	/**
	 * Sets this JPanel with the appropriate scale and plots the function graph.
	 */
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();
		
		g.setColor(axisColor);
		g.drawLine(0, h / 2, w, h / 2); //draws the horizontal x - axis
		g.drawLine(w / 2, 0, w / 2, h); //draws the vertical y - axis
		
		Polygon p = new Polygon();
		double scale = 10; 				//scale the y - axis up
		int offSetX = (MathValue.NUMBER_OF_POINTS - w) / 2; //sets the pixel offset from the left side of the graph based on the width of the JPanel
		for(int i = offSetX; i < MathValue.NUMBER_OF_POINTS - offSetX; i++) //adds the points to the polygon
		{
			if(Double.isNaN(yPoints[i])) //checks if there are discontinuities in the graph
			{
				continousFunction = false;
				break;
			}
			if(scale * yPoints[i] > h / 2.0)
				p.addPoint(i - offSetX, -Integer.MAX_VALUE);
			else
				p.addPoint(i - offSetX, (int)Math.round(h / 2.0 - (scale * yPoints[i])));
		}
		setYAxis(w, h, g);
		setXAxis(w, h, g);
		g.setColor(Color.blue);
		
		if(!continousFunction)
			for(int i = offSetX; i < MathValue.NUMBER_OF_POINTS - offSetX; i++) //draws discontinuous lines in case of a discontinuous function
			{
				if(!Double.isNaN(yPoints[i]))
					if(scale * yPoints[i] > h / 2.0)
						g.drawLine(i - offSetX, h / 2, i - offSetX, -Integer.MAX_VALUE);
					else
						g.drawLine(i - offSetX, h / 2, i - offSetX, (int)Math.round(h / 2.0 - (scale * yPoints[i])));
			}
		else
			g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
	}

	/**
	 * Sets the y-axis of the graph with the correct scale and intervals.
	 * @param w - width of the JPanel
	 * @param h - height of the JPanel
	 * @param g - the Graphics object use to draw
	 */
	private void setYAxis(int w, int h, Graphics g)
	{
		int factor = 50;
		
		for(int i = 1; i < 7; i++)
		{
			g.setColor(Color.WHITE);
			g.drawString("" + i * 5, w / 2 + 5, (h / 2) - (factor * i) + 4); //draws the interval numbers
			g.drawLine(w / 2 - 5, h / 2 - factor * i, w / 2 + 5, h / 2 - factor * i); //draws the interval lines
		}
		
		for(int i = 1; i < 7; i++)
		{
			g.setColor(Color.WHITE);
			g.drawString("-" + i * 5, w / 2 + 8, (h / 2) + (factor * i) + 4); //draws the interval numbers
			g.drawLine(w / 2 - 5, h / 2 + factor * i, w / 2 + 5, h / 2 + factor * i); //draws the interval lines
		}
		
	}

	/**
	 * Sets the x-axis of the graph with the correct scale and intervals.
	 * @param w - width of the JPanel
	 * @param h - height of the JPanel
	 * @param g - the Graphics object use to draw
	 */
	private void setXAxis(int w, int h, Graphics g)
	{
		int factor = 50;
		
		g.setColor(Color.WHITE);
		g.drawString("-5", (w / 2) - (factor * 1) - 5, h / 2 + 18); //draws the interval numbers
		g.drawLine(w / 2 - factor * 1, h / 2 - 5, w / 2 - factor * 1, h / 2 + 5); //draws the interval lines
		for(int i = 2; i < 8; i++)
		{
			g.setColor(Color.WHITE);
			g.drawString("-" + i * 5, (w / 2) - (factor * i) - 10, h / 2 + 18); //draws the interval numbers
			g.drawLine(w / 2 - factor * i, h / 2 - 5, w / 2 - factor * i, h / 2 + 5); //draws the interval lines
		}
		
		g.setColor(Color.WHITE);
		g.drawString("5", (w / 2) + (factor * 1) - 3, h / 2 + 18); //draws the interval numbers
		g.drawLine(w / 2 + factor * 1, h / 2 - 5, w / 2 + factor * 1, h / 2 + 5); //draws the interval lines
		for(int i = 2; i < 8; i++)
		{
			g.setColor(Color.WHITE);
			g.drawString("" + i * 5, (w / 2) + (factor * i) - 7, h / 2 + 18); //draws the interval numbers
			g.drawLine(w / 2 + factor * i, h / 2 - 5, w / 2 + factor * i, h / 2 + 5); //draws the interval lines
		}
	}
	
	/**
	 * Adding Buttons to the Panel with our graph
	 * @param graph - this graph panel
	 */
	public void addButtonsToGraph(JPanel graph){
		graph.setBackground(Color.BLACK);
		//graph.setLayout(new FlowLayout(FlowLayout.LEFT,30,5));
		graph.setLayout(new BoxLayout(graph, BoxLayout.Y_AXIS));
		//graph.add(Box.createRigidArea(new Dimension(0,3)));
		graphPanelButtonColor();
		//graph.add(Box.createHorizontalStrut(10));
		/*
		graph.add(bback);
		graph.add(baddToFavourites);
		graph.add(bfavourites);
		*/
		/*
		//Box b1 = new Box(BoxLayout.X_AXIS);
		Box b1 = Box.createHorizontalBox();
		
		b1.add(bback);
		b1.add(baddToFavourites);
		b1.add(bfavourites);
		*/
		
		
		//Version 1
		/*JPanel b1 = new JPanel();
		b1.setBackground(Color.BLACK);
		b1.setLayout(new GridLayout(1,3,50,10));
		//b1.setLayout(new BoxLayout(b1, BoxLayout.X_AXIS));
		//bback.setPreferredSize(new Dimension(70,40));
		
		b1.add(bback);
		//graph.add(Box.createRigidArea(new Dimension(10,0)));
		b1.add(baddToFavourites);
		b1.add(bfavourites);
		b1.setOpaque(false);
		graph.add(b1);
		graph.add(Box.createRigidArea(new Dimension(0,653)));
		*/
		
		//Version 2
		//graph.add(Box.createVerticalStrut(10));
		JPanel b1 = new JPanel();
		b1.setBackground(Color.WHITE);
		//b1.setLayout(new GridLayout(1,2,380,0));
		b1.setLayout(new BoxLayout(b1, BoxLayout.X_AXIS));
		//bback.setPreferredSize(new Dimension(70,40));
		JPanel back = new JPanel();
		JPanel fav = new JPanel();
		JPanel functName = new JPanel();
		back.setOpaque(false);
		fav.setOpaque(false);
		functName.setOpaque(false);
		
		back.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		functName.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		fav.setLayout(new FlowLayout(FlowLayout.RIGHT,5,5));
		bback.setPreferredSize(new Dimension(200,40));
		equationName.setPreferredSize(new Dimension(200, 40));
		equationName.setEditable(false);
		equationName.setHorizontalAlignment(SwingConstants.CENTER);
		bfavourites.setPreferredSize(new Dimension(200,40));
		back.add(bback);
		fav.add(bfavourites);
		functName.add(equationName);
		b1.add(back);
		b1.add(functName);
		//graph.add(Box.createRigidArea(new Dimension(10,0)));
		//b1.add(baddToFavourites);
		b1.add(fav);
		b1.setOpaque(false);
		
		JPanel b2 = new JPanel();
		b2.setBackground(Color.WHITE);
		b2.setLayout(new FlowLayout(FlowLayout.RIGHT,5,0));
		baddToFavourites.setPreferredSize(new Dimension(200,40));
		b2.add(baddToFavourites);
		b2.setOpaque(false);
		
		//graph.add(Box.createRigidArea(new Dimension(5,0)));
		graph.add(b1);
		graph.add(Box.createRigidArea(new Dimension(0,592)));
		//graph.add(Box.createVerticalStrut(590));
		graph.add(b2);
		//graph.add(Box.createRigidArea(new Dimension(10,5)));
			
	}
	
	/**
	 * Setting the color of the buttons and textfield in this panel
	 */
	public void graphPanelButtonColor(){
		bback.setBackground(view.functionColor);
		bback.setForeground(view.buttonTextColor);
		bback.setFocusable(false);
		bback.setBorder(null);
		bback.setFont(bback.getFont().deriveFont(20f));
		
		baddToFavourites.setBackground(view.enterColor);
		baddToFavourites.setForeground(view.buttonTextColor);
		baddToFavourites.setFocusable(false);
		baddToFavourites.setBorder(null);
		baddToFavourites.setFont(baddToFavourites.getFont().deriveFont(20f));
		
		bfavourites.setBackground(view.functionColor);
		bfavourites.setForeground(view.buttonTextColor);
		bfavourites.setFocusable(false);
		bfavourites.setBorder(null);
		bfavourites.setFont(bfavourites.getFont().deriveFont(20f));
		
		equationName.setOpaque(false);
		equationName.setBackground(Color.BLACK);
		equationName.setForeground(view.buttonTextColor);
		equationName.setFocusable(false);
		equationName.setBorder(null);
		equationName.setFont(new Font("Arial Rounded", Font.BOLD,18));
		}
		
	/**
	 * Changes the view to display the favorites panel
	 */
	public void openFavourites()
	{
		controller.openFavorites(this);
	}

	/**
	 * Adds the data points and equation name of this graph to the favorites
	 * Changes the view to display the favorites panel
	 */
	public void addToFavourites()
	{
		controller.addToFavourites(this, equation, yPoints);
	}
	
	/**
	 * Helper method for the favorites view. Allows to get an instance of the graph controller stored in this panel
	 * @return - the instance of the graph controller
	 */
	public GraphController getGraphController()
	{
		return controller;
	}
}