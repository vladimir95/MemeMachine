import javax.swing.*;

public class GraphController {
	
	private GraphView graphView;
	private CalcModel model;
	private CalcController controller;
	
	public GraphController(CalcController calcController){
		model = new CalcModel();

		controller = calcController;
		graphView = new GraphView(this);
	}
	
	public JPanel graph(){
		model.x();
		model.cosine();
		model.x();
		model.multiply();
		controller.updateView();
		JPanel a = new JPanel();
		a = new DrawFunction(model.getFunction());
		return a;
	}
	

}
