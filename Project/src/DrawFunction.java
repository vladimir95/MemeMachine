import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawFunction extends JPanel
{
	private double[] yPoints;
	
	DrawFunction(double[] data)
	{
		yPoints = data;
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();
		
		g.drawLine(0, h / 2, w, h / 2);
		g.drawLine(w / 2, 0, w / 2, h);
		
		Polygon p = new Polygon();
		double scale = 10;
		int offSetX = (MathValue.NUMBER_OF_POINTS - w) / 2;
		for(int i = offSetX; i <MathValue.NUMBER_OF_POINTS - offSetX; i++)
		{
				p.addPoint(i - offSetX, (h / 2 - (int)(scale * yPoints[i])));
		}
		setYAxis(w, h, g);
		setXAxis(w, h, g);
		g.setColor(Color.blue);
		g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
	}

	private void setYAxis(int w, int h, Graphics g)
	{
		int factor = 48;
		
		for(int i = 1; i < 8; i++)
		{
			g.drawString("" + i * 5, w / 2 + 5, (h / 2) - (factor * i)); 
			g.drawLine(w / 2 - 5, h / 2 - factor * i - 4, w / 2 + 5, h / 2 - factor * i - 4);
		}
		
		for(int i = 1; i < 8; i++)
		{
			g.drawString("-" + i * 5, w / 2 + 8, (h / 2) + (factor * i)); 
			g.drawLine(w / 2 - 5, h / 2 + factor * i - 4, w / 2 + 5, h / 2 + factor * i - 4);
		}
	}

	private void setXAxis(int w, int h, Graphics g)
	{
		int factor = 50;
		
		g.drawString("-5", (w / 2) - (factor * 1) - 5, h / 2 + 18); 
		g.drawLine(w / 2 - factor * 1, h / 2 - 5, w / 2 - factor * 1, h / 2 + 5);
		for(int i = 2; i < 8; i++)
		{
			g.drawString("-" + i * 5, (w / 2) - (factor * i) - 10, h / 2 + 18); 
			g.drawLine(w / 2 - factor * i, h / 2 - 5, w / 2 - factor * i, h / 2 + 5);
		}
		
		g.drawString("5", (w / 2) + (factor * 1) - 3, h / 2 + 18);
		g.drawLine(w / 2 + factor * 1, h / 2 - 5, w / 2 + factor * 1, h / 2 + 5);
		for(int i = 2; i < 8; i++)
		{
			g.drawString("" + i * 5, (w / 2) + (factor * i) - 7, h / 2 + 18); 
			g.drawLine(w / 2 + factor * i, h / 2 - 5, w / 2 + factor * i, h / 2 + 5);
		}
	}
	
	public static void main(String[] args) //will be removed once this class (JPanel) is properly put into the GraphView
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
	}
}