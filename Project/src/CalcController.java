import java.net.MalformedURLException;
import java.util.EmptyStackException;

public class CalcController {

	private CalcView view;
	private CalcModel model; 
	
	
	public CalcController() throws MalformedURLException
	{
		view = new CalcView(this);
		model = new CalcModel();
		updateView();
	}	
	
	private void updateView()
	{
		view.setHistoryText(model.getHistoryValue()); 
		view.setDisplayText(model.getInputValue());  
	}

	public void sum()
	{ 		
		if(model.mathError())
			clear();
		else
		{
			model.sum();
			updateView();
		}
	}
	
	public void subtract()
	{ 		
		if(model.mathError())
			clear();
		else
		{
			model.subtract();
			updateView();
		}
	}
	
	public void multiply()
	{ 		
		if(model.mathError())
			clear();
		else
		{
			model.multiply();
			updateView();
		}
	}
	
	public void divide()
	{ 		
		if(model.mathError())
			clear();
		else
		{
			model.divide();
			updateView();
		}
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
		if(model.mathError())
			clear();
		else
		{
			model.enter();
			updateView();
		}
	}
	
	public void sine()
	{ 		
		if(model.mathError())
			clear();
		else
		{
			model.sine();
			updateView();
		}
	}
	
	public void cosine()
	{ 		
		if(model.mathError())
			clear();
		else
		{
			model.cosine();
			updateView();
		}
	}
	
	public void pi()
	{ 		
		if(model.mathError())
			clear();
		else
		{
			model.pi();
			updateView();
		}
	}
	
	public void factorial()
	{ 		
		if(model.mathError())
			clear();
		else
		{
			model.factorial();
			updateView();
		}
	}
	
	public void changeSign()
	{ 		
		if(model.mathError())
			clear();
		else
		{
			model.changeSign();
			updateView();
		}
	}
	
	public void numericButton(String buttonName)
	{ 		
		if(model.mathError())
			clear();
		else
		{
			model.numericButton(buttonName);
			updateView();
		}
	}
	
	
	public void sampleTest(){
		model.clear();
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
		if(model.mathError())
			clear();
		else
		{
			model.x();
			updateView();
		}
	}
	
	public void graph()
	{ 		
		//Not specified yet
	}
}
