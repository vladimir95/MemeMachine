public class CalcController {

	private CalcView view;
	private CalcModel model; 
	
	public CalcController()
	{
		//view = new CalcView(this); --> after we're done with View
		view = new CalcView(this);
		model = new CalcModel();
		model.clear();
		updateView();
		//view.setVisible(true); --> Option of launching the view from Controller. 
		// I guess we could do it this way, but only when the View is fully implemented. 
	}
	
	public void updateView()
	{
		view.setHistoryText(model.getHistoryValue()); 
		view.setDisplayText(model.getInputValue());  
		//System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue()); // temporary output to console for testing purposes
	}

	public void sum()
	{ 		
		model.sum();
		updateView();
	}
	
	public void subtract()
	{ 		
		model.subtract();
		updateView();
	}
	
	public void multiply()
	{ 		
		model.multiply();
		updateView();
	}
	
	public void divide()
	{ 		
		model.divide();
		updateView();
	}
	
	public void clear()
	{ 		
		model.clear();
		updateView();
	}
	
	public void undo()
	{ 		
		model.undo();
		updateView();
	}
	
	public void enter()
	{ 		
		model.enter();
		updateView();
	}
	
	public void sine()
	{ 		
		model.sine();
		updateView();
	}
	
	public void cosine()
	{ 		
		model.cosine();
		updateView();
	}
	
	public void pi()
	{ 		
		model.pi();
		updateView();
	}
	
	public void factorial()
	{ 		
		model.factorial();
		updateView();
	}
	
	public void changeSign()
	{ 		
		model.changeSign();
		updateView();
	}
	
	public void numericButton(String buttonName)
	{ 		
		model.numericButton(buttonName);
		updateView();
	}
	
	public void x()
	{ 		
		//Not specified yet
	}
	
	public void graph()
	{ 		
		//Not specified yet
	}
}
