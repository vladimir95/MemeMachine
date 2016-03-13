import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GraphView extends JFrame
{
	DrawFunction functionPlotter;
	
	public GraphView()
	{
		setLayout(new BorderLayout());
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.add(new DrawFunction(model.getFunction())); 		need to add a way to get the function from the model throught the controller
		f.setSize(640, 720);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setResizable(false);
	}
}
