import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawFunction extends JPanel
{
	private Color axisColor = new Color(97,107,116);
	private double[] yPoints;
	
	/**
	 * Creates a function to be graphed
	 * @param data - the y-axis points of a function
	 */
	DrawFunction(double[] data)
	{
		yPoints = data;
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
	
	/*public static void main(String[] args) //will be removed once this class (JPanel) is properly put into the GraphView
	{
		CalcModel model = new CalcModel();
		boolean trig = true;
		if(trig)
		{
			model.x();
			model.cosine();
			model.x();
			model.multiply();
		}
		if(!trig)
		{
			model.x();
			model.x();
			model.multiply();
		}
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new DrawFunction(model.getFunction()));
		f.setSize(640, 720);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setResizable(false);
	}*/
}