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

public class DrawFunction extends JPanel
{
	private Color axisColor = new Color(97,107,116);
	private double[] yPoints;
	private CalcView view;
	
	//Graph's buttons
	JButton bback, bfavourites, baddToFavourites;
	
	/**
	 * Creates a function to be graphed
	 * @param data - the y-axis points of a function
	 */
	DrawFunction(double[] data, CalcView theView)
	{
		this.setBackground(Color.BLACK);
		
		yPoints = data;
		view = theView;
		
		
		//Graph's Panel buttons
		bback = new JButton("< Back");
		bfavourites = new JButton("Favourites");
		baddToFavourites = new JButton("Add to Favourites");
		addButtonsToGraph(this);
		
		
		
		add(new ButtonAdapter(bfavourites) {
		       public void pressed(){ openFavourites();}});
		
		new ButtonAdapter(bback) {
			public void pressed(){Back();}};
		
		view.frame.setContentPane(this);
		view.frame.revalidate();
	}
	
	
	protected void Back()
	{
		view.frame.setContentPane(view.mainpanel);
		view.frame.revalidate();
	}

	/**
	 * Sets the JPanel with the appropriate scale and plots the function graph.
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
			if(scale * yPoints[i] > h / 2.0)
				p.addPoint(i - offSetX, -Integer.MAX_VALUE);
			else
				p.addPoint(i - offSetX, (int)Math.round(h / 2.0 - (scale * yPoints[i])));
		}
		setYAxis(w, h, g);
		setXAxis(w, h, g);
		g.setColor(Color.blue);
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
	
	//Adding Buttons to the Panel with our graph
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
			back.setOpaque(false);
			fav.setOpaque(false);
			
			back.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
			fav.setLayout(new FlowLayout(FlowLayout.RIGHT,5,5));
			bback.setPreferredSize(new Dimension(200,40));
			bfavourites.setPreferredSize(new Dimension(200,40));
			back.add(bback);
			fav.add(bfavourites);
			b1.add(back);
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
			
		}
		
		public void openFavourites(){
			JPanel favourites = new JPanel();
			//favourites.setLayout(new GridLayout(1,10,5,5));
			favourites.setLayout(new BoxLayout(favourites,BoxLayout.Y_AXIS));
			favourites.setBackground(Color.BLACK);
			JPanel button = new JPanel();
			button.setOpaque(false);
			button.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
			JButton back = new JButton("< Back");
			back.setPreferredSize(new Dimension(200,40));
			back.setBackground(view.functionColor);
			back.setForeground(view.buttonTextColor);
			back.setFocusable(false);
			back.setBorder(null);
			back.setFont(bback.getFont().deriveFont(20f));
			
			button.add(back);
			
			
			JPanel example = new JPanel();
			example.setBackground(Color.BLUE);
			JButton delete = new JButton("Delete");
			JButton graph = new JButton("Graph");
			
			
			graph.setPreferredSize(new Dimension(150,40));
			graph.setBackground(view.functionColor);
			graph.setForeground(view.buttonTextColor);
			graph.setFocusable(false);
			graph.setBorder(null);
			graph.setFont(bback.getFont().deriveFont(20f));
			
			delete.setPreferredSize(new Dimension(150,40));
			delete.setBackground(view.functionColor);
			delete.setForeground(view.buttonTextColor);
			delete.setFocusable(false);
			delete.setBorder(null);
			delete.setFont(bback.getFont().deriveFont(20f));
			
			
			
			JTextField graphName = new JTextField("",30);
			example.setLayout(new GridLayout(1,3,5,0));
			graphName.setBackground(Color.BLACK);
			graphName.setForeground(Color.WHITE);
			graphName.setSize(new Dimension(320,40));
			graphName.setFont(new Font("Arial Rounded", Font.BOLD,18));
			
			example.add(graphName);
			example.add(graph);
			example.add(delete);
			
			favourites.add(button);
			//favourites.add(Box.createRigidArea(new Dimension(0,5)));
			favourites.add(example);
			favourites.add(Box.createRigidArea(new Dimension(0,600)));
			
			
			view.frame.setContentPane(favourites);
	    	view.frame.revalidate();
			/*for (int i = 0; i < 4; i++){
				favourites.add(example);
			}*/
		}
	
}