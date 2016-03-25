import javax.swing.*;

public class GraphController {
	
	private CalcController controller;
	
	
	public GraphController(CalcController calcController){
		controller =  calcController;
	}
	
	public void graph(CalcView view){
		new DrawFunction(controller.getModel().getFunction(), view);
	}
}
