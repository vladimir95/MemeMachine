import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GraphView extends JFrame
{
	DrawFunction functionPlotter;
	
	public GraphView(final GraphController graphController)
	{
		setLayout(new BorderLayout());
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(graphController.graph()); 		//need to add a way to get the function from the model through the controller
		f.setSize(640, 720);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setResizable(false);
	}
	
	/*public static void main (String args[]){
		GraphController gc = new GraphController(c);
		GraphView g = new GraphView(gc);
	}*/
}
