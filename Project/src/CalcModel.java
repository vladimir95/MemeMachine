import java.util.Stack;

public class CalcModel 
{
	private StringBuilder inputValue;	//value displayed as user pressing numeric and decimal buttons
	private StringBuilder historyValue; //history displayed when user completes input or performs arithmetic operation
	private Stack<Double> calcStack;	//user input is stored in a stack data type
	
	private final String INITIAL_DISPLAYED_VALUE = "0";
	private final String INITIAL_DISPLAYED_HISTORY = "start new calculation";
	
	private boolean valueResetFlag;		//true if inputValue needs to be reset, false if inputValue needs to be appended.
	private boolean historyResetFlag; 	//true if historyValue needs to be reset, false if historyValue needs to be appended.
	
	private int commaPos; 				//tracks the location of the right most comma in the historyValue.
	
	/**
	 * Creates a new model with initial user and history value,
	 * and initializes a new empty stack.
	 */
	CalcModel()
	{
		inputValue = new StringBuilder(INITIAL_DISPLAYED_VALUE);
		historyValue = new StringBuilder(INITIAL_DISPLAYED_HISTORY);
		calcStack = new Stack<Double>();
		calcStack.push(Double.parseDouble(INITIAL_DISPLAYED_VALUE));
		valueResetFlag = true;
		historyResetFlag = true;
	}
	
	/**
	 * Reads the user input as the user when the user presses numeric or decimal buttons.
	 * @param buttonName - The name of the button as seen in the View.
	 */
	public void numericButton(String buttonName)
	{
		if(valueResetFlag)
		{
				inputValue = new StringBuilder(buttonName);
				checkEqualSign();
				if(!buttonName.equals(INITIAL_DISPLAYED_VALUE))
					valueResetFlag = false;
		}
		else
			inputValue.append(buttonName);
		
		if (historyResetFlag)
		{
			historyValue = new StringBuilder();
		}
	}
	
	/**
	 * Adds the 2 top values of the stack and pushes the result back to the top of the stack.
	 */
	public void sum()
	{
		double top, secondTop;
		
		if(!valueResetFlag)
			enter();
		checkEqualSign();
		if(enoughOperands())
		{	
			top = calcStack.pop();
			secondTop = calcStack.pop();
			calcStack.push(secondTop + top);
			historyValue.deleteCharAt(commaPos);
			commaPos = historyValue.indexOf(",");
			historyValue.append(" + =");
			inputValue = new StringBuilder(calcStack.peek().toString());
		}
	}
	
	/**
	 * Pushes the user value to the top of the stack.
	 */
	public void enter()
	{
		calcStack.push(Double.parseDouble(inputValue.toString()));
		valueResetFlag = true;
		if(historyResetFlag)
		{
			historyValue = new StringBuilder(getInputValue());
			historyResetFlag = false;
		}
		else
		{
			commaPos = historyValue.length();
			historyValue.append(", " + getInputValue());
		}
	}
	
	/**
	 * Returns the inputValue.
	 * @return - String representation of the inputValue.
	 */
	public String getInputValue()
	{
		return inputValue.toString();
	}
	
	/**
	 * Returns the historyValue.
	 * @return - String representation of the historyValue.
	 */
	public String getHistoryValue()
	{
		return historyValue.toString();
	}
	
	/**
	 * Checks to see if the historyValue has a "=" sign in it and deletes it. 
	 */
	private void checkEqualSign()
	{
		if(historyValue.indexOf(" =") != -1)
		{
			historyValue.delete(historyValue.indexOf(" ="), historyValue.length());
		}
	}
	
	private boolean enoughOperands()
	{
		double secondTop, top;
		boolean enough = true;
		
		top = calcStack.pop();
		if(calcStack.empty())
		{
			calcStack.push(top);
			enough = false;
		}
		else
		{
			secondTop = calcStack.pop();
			if(calcStack.empty())
			{
				calcStack.push(secondTop);
				calcStack.push(top);
				enough = false;
			}
			else
			{
				calcStack.push(secondTop);
				calcStack.push(top);
			}
		}
		return enough;
	}
}
