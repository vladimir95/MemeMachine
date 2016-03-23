import javax.swing.*;

public class GraphController {
	
	private CalcController controller;
	
	public GraphController(CalcController calcController){
		controller =  calcController;

		//controller = calcController;
		//graphView = new GraphView(this);
	}
	
	public JPanel graph(){
		JPanel a = new DrawFunction(controller.getModel().getFunction());
		return a;
	}
}
