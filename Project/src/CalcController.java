import java.net.MalformedURLException;

public class CalcController {

	private CalcView view;
	private CalcModel model; 
	
	
	public CalcController() throws MalformedURLException
	{
		view = new CalcView(this);
		model = new CalcModel();
		model.clear();
		updateView();
	}	
	
	private void updateView()
	{
		view.setHistoryText(model.getHistoryValue()); 
		view.setDisplayText(model.getInputValue());  
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
	
	
	public void sampleTest(){
		model.numericButton("5");
		model.numericButton(".");
		model.numericButton("2");
		model.numericButton("5");
		model.changeSign();
		model.enter();
		model.numericButton("6");
		model.numericButton(".");
		model.numericButton("4");
		model.numericButton("7");
		model.enter();
		model.sum();
		updateView();
	}
	
	public String getHistoryText()
	{
		return view.historyDisplay.getText();
	}
	
	public String getDisplayText()
	{
		return view.display.getText();
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
