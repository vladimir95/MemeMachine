import java.net.MalformedURLException;

public class CalcController {

	private CalcView view;
	private CalcModel model; 
	private GraphController graphController;
	
	/**
	 * Creates the calculator controller
	 * The controller mediates between the main view and the model
	 * @throws MalformedURLException
	 */
	public CalcController() throws MalformedURLException
	{
		model = new CalcModel();
		view = new CalcView(this);
		graphController = new GraphController(this, view);
		updateView();
	}	
	
	/**
	 * updates the CalcView with the information of CalcModel
	 * @post. view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
	private void updateView()
	{
		view.setHistoryText(model.getHistoryValue()); 
		view.setDisplayText(model.getInputValue());  
	}

	/**
	 * invokes the sum() method of CalcModel or clear() method if MATH ERROR has occurred
	 * updates CalcView with the result
	 * @post. model.sum() OR model.clear() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
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
	
	/**
	 * invokes the subtract() method of CalcModel or clear() method if MATH ERROR has occurred
	 * updates CalcView with the result
	 * @post. model.subtract() OR model.clear() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
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
	
	/**
	 * invokes the multiply() method of CalcModel or clear() method if MATH ERROR has occurred
	 * updates CalcView with the result
	 * @post. model.multiply() OR model.clear() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
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
	
	/**
	 * invokes the divide() method of CalcModel or clear() method if MATH ERROR has occurred
	 * updates CalcView with the result
	 * @post. model.divide() OR model.clear() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
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
	
	/**
	 * invokes the clear() method of CalcModel
	 * updates CalcView with the result
	 * @post. model.clear() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
	public void clear()
	{ 		
		model.clear();
		updateView();
	}
	
	/**
	 * invokes the undo() method of CalcModel
	 * updates CalcView with the result
	 * @post. model.undo() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
	public void undo()
	{ 		
		model.undo();
		updateView();
	}
	
	/**
	 * invokes the enter() method of CalcModel or clear() method if MATH ERROR has occurred
	 * updates CalcView with the result
	 * @post. model.enter() OR model.clear() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
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
	
	/**
	 * invokes the sine() method of CalcModel or clear() method if MATH ERROR has occurred
	 * updates CalcView with the result
	 * @post. model.sine() OR model.clear() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
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
	
	/**
	 * invokes the cosine() method of CalcModel or clear() method if MATH ERROR has occurred
	 * updates CalcView with the result
	 * @post. model.cosine() OR model.clear() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
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
	
	/**
	 * invokes the pi() method of CalcModel or clear() method if MATH ERROR has occurred
	 * updates CalcView with the result
	 * @post. model.pi() OR model.clear() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
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
	
	/**
	 * invokes the factorial() method of CalcModel or clear() method if MATH ERROR has occurred
	 * updates CalcView with the result
	 * @post. model.factorial() OR model.clear() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
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
	
	/**
	 * invokes the changeSign() method of CalcModel or clear() method if MATH ERROR has occurred
	 * updates CalcView with the result
	 * @post. model.changeSign() OR model.clear() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
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
	
	/**
	 * invokes the numericButton(String buttonName) method of CalcModel or clear() method if MATH ERROR has occurred
	 * updates CalcView with the result
	 * @param buttonName - The name of the button as seen in CalcView.
	 * @post. model.numericButton(String buttonName) OR model.clear() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
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
	
	/**
	 * demonstrates the calculator work by executing a sequence of operations of CaclModel.
	 * updates CalcView with the result
	 * @post. a sequence of CalcModel are executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
	public void sampleTest(){
		/*model.clear();
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
		model.sum();*/
		model.x();
		model.cosine();
		model.x();
		model.multiply();
		updateView();
	}
	
	/**
	 * Returns the string of the historyText in CalcView
	 * @return - the historyText string
	 */
	public String getHistoryText()
	{
		return view.historyDisplay.getText();
	}
	
	/**
	 * Returns the string of the displayText in CalcView
	 * @return - the displayText string
	 */
	public String getDisplayText()
	{
		return view.display.getText();
	}
	
	/**
	 * invokes the x() method of CalcModel or clear() method if MATH ERROR has occurred
	 * updates CalcView with the result
	 * @post. model.x() OR model.clear() is executed
	 * 		  view.historyText = model.historyValue
	 *        view.displayText = model.inputValue
	 */
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
	
	/**
	 * invokes the graph() method of GraphController or model.clear() if MATH ERROR has occurred
	 */
	public void graph()
	{ 		
		if(model.mathError())
			clear();
		else
		{
			graphController.graph();
		}
	}
	
	/**
	 * Helper method to allow passing the CalcModel to GraphController
	 * @return - returns the instance of the current CalcModel
	 */
	public CalcModel getModel()
	{
		return model;
	}
	
}
